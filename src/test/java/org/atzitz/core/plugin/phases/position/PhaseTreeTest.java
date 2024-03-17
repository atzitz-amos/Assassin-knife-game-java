package org.atzitz.core.plugin.phases.position;

import org.assertj.core.api.Assertions;
import org.atzitz.core.exceptions.MalformedPluginData;
import org.atzitz.core.plugin.phases.IPhase;
import org.atzitz.plugins.default_plugin.phases.*;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

class PhaseTreeTest {

    @Test
    void myTest() throws MalformedPluginData {
        IPhase assassinChooseVictimPhase = new AssassinChooseVictimPhase();
        IPhase assassinVotePhase = new AssassinVotePhase();
        IPhase dayDefendPhase = new DayDefendPhase();
        IPhase dayJuryVotePhase = new DayJuryVotePhase();
        IPhase dayVotePhase = new DayVotePhase();
        IPhase duskPhase = new DuskPhase();
        IPhase nightPhase = new NightPhase();
        Collection<IPhase> phases = List.of(assassinVotePhase, dayDefendPhase, duskPhase, dayVotePhase, dayJuryVotePhase, nightPhase, assassinChooseVictimPhase);

        PhaseTreeNode head = PhaseTree.of(phases).getHead();

        Assertions.assertThat(head.getPhase()).isEqualTo(duskPhase);
        Assertions.assertThat(head.getNext().getPhase()).isEqualTo(nightPhase);
        Assertions.assertThat(head.getNext().getNext().getPhase()).isEqualTo(dayVotePhase);
        Assertions.assertThat(head.getNext().getNext().getNext().getPhase()).isEqualTo(dayDefendPhase);
        Assertions.assertThat(head.getNext().getNext().getNext().getNext().getPhase()).isEqualTo(dayJuryVotePhase);

        Assertions.assertThat(head.getNext().getSideNodes().get(0).getPhase()).isEqualTo(assassinVotePhase);
        Assertions.assertThat(head.getNext().getSideNodes().get(0).getNext().getPhase()).isEqualTo(assassinChooseVictimPhase);
    }
}