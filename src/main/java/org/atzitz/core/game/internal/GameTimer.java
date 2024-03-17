package org.atzitz.core.game.internal;

import lombok.Getter;
import org.atzitz.core.exceptions.MalformedPluginData;
import org.atzitz.core.plugin.phases.IPhase;
import org.atzitz.core.plugin.phases.position.PhaseTree;

import java.util.Collection;

@Getter
public class GameTimer {
    private final PhaseTree phases;

    public GameTimer(Collection<IPhase> phases) throws MalformedPluginData {
        this.phases = PhaseTree.of(phases);
    }
}
