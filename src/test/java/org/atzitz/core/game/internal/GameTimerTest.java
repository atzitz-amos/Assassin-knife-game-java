package org.atzitz.core.game.internal;

import org.assertj.core.api.Assertions;
import org.atzitz.core.exceptions.MalformedPluginData;
import org.atzitz.core.plugin.phases.IPhase;
import org.atzitz.datatypes.constants.GameTimerEventType;
import org.atzitz.plugins.default_plugin.phases.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class GameTimerTest {
    private final IPhase assassinChooseVictimPhase = new AssassinChooseVictimPhase();
    private final IPhase assassinVotePhase = new AssassinVotePhase();
    private final IPhase dayDefendPhase = new DayDefendPhase();
    private final IPhase dayJuryVotePhase = new DayJuryVotePhase();
    private final IPhase dayVotePhase = new DayVotePhase();
    private final IPhase duskPhase = new DuskPhase();
    private final IPhase nightPhase = new NightPhase();

    private final List<IPhase> phases = List.of(assassinVotePhase, dayDefendPhase, duskPhase, dayVotePhase, dayJuryVotePhase, nightPhase, assassinChooseVictimPhase);
    private GameTimer gameTimer;

    @BeforeEach
    void setUp() throws MalformedPluginData {
        gameTimer = new GameTimer(phases);
    }

    @Test
    void phaseFlowTest() {
        Assertions.assertThat(gameTimer.getPhase(() -> true)).isEqualTo(duskPhase);
        gameTimer.next();
        Assertions.assertThat(gameTimer.getPhase(() -> true)).isEqualTo(assassinVotePhase);
        gameTimer.next();
        Assertions.assertThat(gameTimer.getPhase(() -> true)).isEqualTo(dayVotePhase);
        gameTimer.next();
        Assertions.assertThat(gameTimer.getPhase(() -> true)).isEqualTo(dayDefendPhase);
        gameTimer.next();
        Assertions.assertThat(gameTimer.getPhase(() -> true)).isEqualTo(dayJuryVotePhase);
    }

    @Test
    void phaseCircleTest() {
        Assertions.assertThat(gameTimer.getPhase(() -> true)).isEqualTo(duskPhase);
        gameTimer.next();
        Assertions.assertThat(gameTimer.getPhase(() -> true)).isEqualTo(assassinVotePhase);
        gameTimer.next();
        Assertions.assertThat(gameTimer.getPhase(() -> true)).isEqualTo(dayVotePhase);
        gameTimer.next();
        Assertions.assertThat(gameTimer.getPhase(() -> true)).isEqualTo(dayDefendPhase);
        gameTimer.next();
        Assertions.assertThat(gameTimer.getPhase(() -> true)).isEqualTo(dayJuryVotePhase);
        gameTimer.next();
        Assertions.assertThat(gameTimer.getPhase(() -> true)).isEqualTo(duskPhase);
        gameTimer.next();
        Assertions.assertThat(gameTimer.getPhase(() -> true)).isEqualTo(assassinVotePhase);
        gameTimer.next();
        Assertions.assertThat(gameTimer.getPhase(() -> true)).isEqualTo(dayVotePhase);
        gameTimer.next();
        Assertions.assertThat(gameTimer.getPhase(() -> true)).isEqualTo(dayDefendPhase);
        gameTimer.next();
        Assertions.assertThat(gameTimer.getPhase(() -> true)).isEqualTo(dayJuryVotePhase);
    }

    @Test
    void phaseFlowAssassinsPhasesTest() {
        gameTimer.next();
        Assertions.assertThat(gameTimer.getPhase(() -> true)).isEqualTo(assassinVotePhase);
        gameTimer.setTimer(nightPhase.getDuration() - assassinVotePhase.getDuration() - 1);
        Assertions.assertThat(gameTimer.getPhase(() -> true)).isEqualTo(assassinChooseVictimPhase);
        gameTimer.next();
        Assertions.assertThat(gameTimer.getPhase(() -> true)).isEqualTo(dayVotePhase);
    }

    @Test
    void phaseFlowNotAssassinTest() {
        gameTimer.next();
        Assertions.assertThat(gameTimer.getPhase(() -> false)).isEqualTo(nightPhase);
        gameTimer.setTimer(nightPhase.getDuration() - assassinVotePhase.getDuration() - 1);
        Assertions.assertThat(gameTimer.getPhase(() -> false)).isEqualTo(nightPhase);
        gameTimer.next();
        Assertions.assertThat(gameTimer.getPhase(() -> false)).isEqualTo(dayVotePhase);
        gameTimer.next();
        Assertions.assertThat(gameTimer.getPhase(() -> false)).isEqualTo(dayDefendPhase);
        gameTimer.next();
        Assertions.assertThat(gameTimer.getPhase(() -> false)).isEqualTo(dayJuryVotePhase);
    }

    @Test
    void timerNoAssassinTest() {
        gameTimer.next();
        Assertions.assertThat(gameTimer.getCurrentTimer(() -> false)).isEqualTo(nightPhase.getDuration());
        gameTimer.setTimer(nightPhase.getDuration() - assassinVotePhase.getDuration() - 1);
        Assertions.assertThat(gameTimer.getCurrentTimer(() -> false)).isEqualTo(nightPhase.getDuration() - assassinVotePhase.getDuration() - 1);
        gameTimer.next();
        Assertions.assertThat(gameTimer.getCurrentTimer(() -> false)).isEqualTo(dayVotePhase.getDuration());
        gameTimer.next();
        Assertions.assertThat(gameTimer.getCurrentTimer(() -> false)).isEqualTo(dayDefendPhase.getDuration());
        gameTimer.next();
        Assertions.assertThat(gameTimer.getCurrentTimer(() -> false)).isEqualTo(dayJuryVotePhase.getDuration());
        gameTimer.next();
        Assertions.assertThat(gameTimer.getCurrentTimer(() -> false)).isEqualTo(duskPhase.getDuration());
    }

    @Test
    void timerAssassinTest() {
        gameTimer.next();
        Assertions.assertThat(gameTimer.getCurrentTimer(() -> true)).isEqualTo(assassinVotePhase.getDuration());
        gameTimer.setTimer(nightPhase.getDuration() - assassinVotePhase.getDuration() - 1);
        Assertions.assertThat(gameTimer.getCurrentTimer(() -> true)).isEqualTo(assassinChooseVictimPhase.getDuration() - 1);
        gameTimer.next();
        Assertions.assertThat(gameTimer.getCurrentTimer(() -> true)).isEqualTo(dayVotePhase.getDuration());
        gameTimer.next();
        Assertions.assertThat(gameTimer.getCurrentTimer(() -> true)).isEqualTo(dayDefendPhase.getDuration());
        gameTimer.next();
        Assertions.assertThat(gameTimer.getCurrentTimer(() -> true)).isEqualTo(dayJuryVotePhase.getDuration());
        gameTimer.next();
        Assertions.assertThat(gameTimer.getCurrentTimer(() -> true)).isEqualTo(duskPhase.getDuration());
    }

    @Test
    void timerTickTest() throws InterruptedException {
        gameTimer.addTickHook(gameTimer -> {
            System.out.println("Tick: " + gameTimer.getCurrentTimer(() -> true) + " [" + gameTimer.getPhase(() -> true).getClass().getSimpleName() + "]");
        });

        gameTimer.addPhaseHook(GameTimerEventType.PHASE_START, DuskPhase.class, gameTimer -> {
            System.out.println("->Dusk");
        });
        gameTimer.addPhaseHook(GameTimerEventType.PHASE_START, NightPhase.class, gameTimer -> {
            System.out.println("->Night");
        });
        gameTimer.addPhaseHook(GameTimerEventType.PHASE_START, AssassinVotePhase.class, gameTimer -> {
            System.out.println("->AssassinVote");
        });
        gameTimer.addPhaseHook(GameTimerEventType.PHASE_START, AssassinChooseVictimPhase.class, gameTimer -> {
            System.out.println("->AssassinChooseVictim");
        });
        gameTimer.addPhaseHook(GameTimerEventType.PHASE_START, DayVotePhase.class, gameTimer -> {
            System.out.println("->DayVote");
        });
        gameTimer.addPhaseHook(GameTimerEventType.PHASE_START, DayDefendPhase.class, gameTimer -> {
            System.out.println("->DayDefend");
        });
        gameTimer.addPhaseHook(GameTimerEventType.PHASE_START, DayJuryVotePhase.class, gameTimer -> {
            System.out.println("->DayJuryVote");
        });

        gameTimer.start();

        Thread.sleep(40000);
    }
}