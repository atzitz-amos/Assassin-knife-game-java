package org.atzitz.core.game.internal;

import lombok.Getter;
import lombok.Setter;
import org.atzitz.core.exceptions.MalformedPluginData;
import org.atzitz.core.plugin.context.GlobalStateContext;
import org.atzitz.core.plugin.context.InGameContext;
import org.atzitz.core.plugin.phases.IPhase;
import org.atzitz.core.plugin.phases.position.PhaseTree;
import org.atzitz.core.plugin.phases.position.PhaseTreeNode;
import org.atzitz.datatypes.constants.GameTimerEventType;
import org.atzitz.plugins.default_plugin.phases.NightPhase;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Getter
public class GameTimer {
    private final PhaseTree phases;
    private final Map<GameTimerEventType, Map<Class<? extends IPhase>, Collection<Consumer<GameTimer>>>> phaseHooks = new HashMap<>();
    private PhaseTreeNode current;
    private boolean firstNight = true;
    private boolean firstTurn = true;

    private @Setter int timer = 0;
    private final Runnable tickTimer = () -> {
        if (timer == 0) {
            next();
        }
        timer--;
        if (phaseHooks.containsKey(GameTimerEventType.TICK)) {
            phaseHooks.get(GameTimerEventType.TICK).forEach((phase, hook) -> {
                if (phase == null || phase.isInstance(current.getPhase())) hook.forEach(h -> h.accept(this));
            });
        }
    };

    public GameTimer(Collection<IPhase> phases) throws MalformedPluginData {
        this.phases = PhaseTree.of(phases);
        this.current = this.phases.getHead();
    }

    public void start() {
        timer = current.getPhase().getDuration();

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(tickTimer, 0, 1, TimeUnit.SECONDS);
    }

    public void next() {
        if (current.getPhase().is(NightPhase.class)) firstNight = false;
        if (phaseHooks.containsKey(GameTimerEventType.PHASE_END)) {
            phaseHooks.get(GameTimerEventType.PHASE_END)
                    .get(current.getPhase().getClass())
                    .forEach(h -> h.accept(this));
        }

        current = current.getNext();
        timer = current.getPhase().getDuration();

        if (current.getNext() == null) {
            firstTurn = false;
            if (phaseHooks.containsKey(GameTimerEventType.TURN_BEGIN)) {
                phaseHooks.get(GameTimerEventType.TURN_BEGIN).get(null).forEach(h -> h.accept(this));
            }
        }
        if (current.getPhasePosition().firstNightOnly() && !firstNight || current.getPhasePosition()
                .firstTurnOnly() && !firstTurn)
            next();

        if (phaseHooks.containsKey(GameTimerEventType.PHASE_START))
            phaseHooks.get(GameTimerEventType.PHASE_START)
                    .get(current.getPhase().getClass())
                    .forEach(h -> h.accept(this));

    }

    public void addPhaseHook(GameTimerEventType eventType, Class<? extends IPhase> phase, Consumer<GameTimer> hook) {
        if (!phaseHooks.containsKey(eventType)) {
            Map<Class<? extends IPhase>, Collection<Consumer<GameTimer>>> hooks = new HashMap<>();
            phaseHooks.put(eventType, hooks);
        }
        if (phaseHooks.get(eventType).containsKey(phase)) {
            phaseHooks.get(eventType).get(phase).add(hook);
        } else {
            phaseHooks.get(eventType).put(phase, List.of(hook));
        }
    }

    public void addTickHook(Consumer<GameTimer> hook) {
        addPhaseHook(GameTimerEventType.TICK, null, hook);
    }


    public IPhase getPhase(InGameContext ctx) {
        if (current.isMultiple()) {
            return dispatch(current, ctx);
        }
        return current.getPhase();
    }

    public IPhase getPhase(GlobalStateContext ctx) {
        return current.getPhase();
    }


    public int getCurrentTimer(InGameContext ctx) {
        if (current.isMultiple()) {
            return relativeTimer(current, ctx);
        }
        return timer;
    }


    private IPhase dispatch(PhaseTreeNode current, InGameContext ctx) {
        for (PhaseTreeNode node : current.getSideNodes()) {
            if (node.getPhase().shouldShow(ctx)) {
                return relativePhase(current, node);
            }
        }
        return current.getPhase();
    }

    private IPhase relativePhase(PhaseTreeNode current, PhaseTreeNode node) {
        int time = current.getPhase().getDuration() - timer;
        while (time >= 0) {
            if (time <= node.getPhase().getDuration()) {
                return node.getPhase();
            }
            time -= node.getPhase().getDuration();
            node = node.getNext();
        }
        return current.getPhase();
    }

    private int relativeTimer(PhaseTreeNode current, InGameContext ctx) {
        for (PhaseTreeNode node : current.getSideNodes()) {
            if (node.getPhase().shouldShow(ctx)) {
                IPhase currentPhase = relativePhase(current, node);
                int time = current.getPhase().getDuration() - timer;
                while (node != null && currentPhase != node.getPhase()) {
                    time -= node.getPhase().getDuration();
                    node = node.getNext();
                }
                return currentPhase.getDuration() - time;
            }
        }
        return timer;
    }

    public void setupPhases(Supplier<GlobalStateContext> ctxSupplier) {
        PhaseTreeNode current = phases.getHead();
        while (current != null) {
            PhaseTreeNode finalCurrent = current;
            addPhaseHook(GameTimerEventType.PHASE_START, current.getPhase()
                    .getClass(), gameTimer -> finalCurrent.getPhase().phaseHookBegin(ctxSupplier.get()));
            addPhaseHook(GameTimerEventType.PHASE_END, current.getPhase()
                    .getClass(), gameTimer -> finalCurrent.getPhase().phaseHookEnd(ctxSupplier.get()));
            addPhaseHook(GameTimerEventType.TICK, current.getPhase().getClass(), gameTimer -> finalCurrent.getPhase()
                    .phaseHookTick(ctxSupplier.get()));

            current = current.getNext();
        }
    }
}


