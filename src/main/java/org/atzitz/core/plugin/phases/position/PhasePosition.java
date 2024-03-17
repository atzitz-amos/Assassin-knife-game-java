package org.atzitz.core.plugin.phases.position;

import lombok.Getter;
import org.atzitz.core.plugin.phases.IPhase;

@Getter
public class PhasePosition {
    private final int priority;
    private final boolean isFirst;
    private final Class<? extends IPhase> after;
    private final Class<? extends IPhase> before;
    private final Class<? extends IPhase> during;
    private final boolean firstTurn;
    private final boolean firstNight;

    public PhasePosition(int priority, boolean isFirst, Class<? extends IPhase> after, Class<? extends IPhase> before, Class<? extends IPhase> during, boolean firstTurn, boolean firstNight) {
        this.priority = priority;
        this.isFirst = isFirst;
        this.after = after;
        this.before = before;
        this.during = during;
        this.firstTurn = firstTurn;
        this.firstNight = firstNight;
    }

}
