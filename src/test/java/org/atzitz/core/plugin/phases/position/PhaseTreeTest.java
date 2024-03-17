package org.atzitz.core.plugin.phases.position;

import org.assertj.core.api.Assertions;
import org.atzitz.core.exceptions.MalformedPluginData;
import org.atzitz.core.plugin.phases.IPhase;
import org.atzitz.plugins.default_plugin.phases.DuskPhase;
import org.atzitz.plugins.default_plugin.phases.NightPhase;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

class PhaseTreeTest {

    @Test
    void myTest() throws MalformedPluginData {
        DuskPhase duskPhase = new DuskPhase();
        Collection<IPhase> phases = List.of(
                duskPhase, new NightPhase()
        );
        Assertions.assertThat(PhaseTree.of(phases).getHead().getPhase()).isEqualTo(duskPhase);
    }
}