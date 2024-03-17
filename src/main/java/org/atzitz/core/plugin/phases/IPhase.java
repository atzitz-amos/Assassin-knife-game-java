package org.atzitz.core.plugin.phases;

import org.atzitz.core.plugin.context.AbstractContext;
import org.atzitz.core.plugin.phases.position.PhasePosition;

public interface IPhase {
    String getId();

    String getKeyPath();

    PhasePosition getPhasePosition();

    default boolean isExclusive() {
        return false;
    }

    default boolean shouldShow(AbstractContext context) {
        return true;
    }
}
