package org.atzitz.core.plugin.phases.position;

import lombok.Getter;
import org.atzitz.core.plugin.phases.IPhase;

@Getter
public class PhasePosition {
    private final int priority;
    private final boolean isFirst;
    private final Class<? extends IPhase> after;
    private final Class<? extends IPhase> during;
    private final boolean firstTurnOnly;
    private final boolean firstNightOnly;

    public PhasePosition(int priority, boolean isFirst, Class<? extends IPhase> after, Class<? extends IPhase> during, boolean firstTurnOnly, boolean firstNightOnly) {
        this.priority = priority;
        this.isFirst = isFirst;
        this.after = after;
        this.during = during;
        this.firstTurnOnly = firstTurnOnly;
        this.firstNightOnly = firstNightOnly;
    }

}
