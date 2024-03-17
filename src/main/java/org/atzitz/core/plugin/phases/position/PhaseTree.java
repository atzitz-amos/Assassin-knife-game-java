package org.atzitz.core.plugin.phases.position;

import lombok.Getter;
import org.atzitz.core.exceptions.MalformedPluginData;
import org.atzitz.core.plugin.context.AbstractContext;
import org.atzitz.core.plugin.phases.IPhase;

import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Getter
public class PhaseTree {
    private final PhaseTreeNode head;
    private PhaseTreeNode current;

    private PhaseTree(PhaseTreeNode head) {
        this.head = head;
        this.current = head;
    }


    private static PhaseTreeNode parse(Collection<IPhase> phases) throws MalformedPluginData {
        PhaseTreeNode current = new PhaseTreeNode(null, null, null);
        Map<Class<? extends IPhase>, IPhase> classMap = phases.stream().collect(Collectors.toMap(IPhase::getClass, phase -> phase));
        Map<Class<? extends IPhase>, PhaseTreeNode> nodeMap = phases.stream().collect(Collectors.toMap(IPhase::getClass, phase -> build(phase, classMap)));

        phases.forEach(phase -> {
            PhasePosition position = phase.getPhasePosition();
            if (position.isFirst()) {
                delegate(current, nodeMap.get(phase.getClass()), position, p -> p.getPhasePosition().isFirst());
            } else if (position.getAfter() != null) {
                delegate(nodeMap.get(position.getAfter()), nodeMap.get(phase.getClass()), position, p -> p.getPhasePosition().getAfter() == position.getAfter());
            }
        });

        return current.getNext();
    }

    private static PhaseTreeNode build(IPhase phase, Map<Class<? extends IPhase>, IPhase> classMap) {
        Predicate<AbstractContext> predicate = ctx -> {
            if (!phase.isExclusive()) return true;

            return (!phase.getPhasePosition().isFirstNight() || ctx.isFirstNight())
                    && (!phase.getPhasePosition().isFirstTurn() || ctx.isFirstTurn())
                    && phase.shouldShow(ctx);
        };
        return phase.getPhasePosition().getDuring() != null
                ? new PhaseTreeNode(phase, classMap.get(phase.getPhasePosition().getDuring()), predicate)
                : new PhaseTreeNode(phase, null, predicate);
    }

    private static void delegate(PhaseTreeNode root, PhaseTreeNode node, PhasePosition position, Predicate<IPhase> predicate) {
        PhaseTreeNode current = root;
        while (current.getNext() != null && predicate.test(current.getNext().getPhase()) && current.getNext().getPhase().getPhasePosition().getPriority() >= position.getPriority()) {
            current = current.getNext();
        }
        PhaseTreeNode item = node;
        while (item.getNext() != null) item = item.getNext();

        item.setNext(current.getNext());
        current.setNext(node);
    }

    public static PhaseTree of(Collection<IPhase> phases) throws MalformedPluginData {
        return new PhaseTree(parse(phases));
    }
}
