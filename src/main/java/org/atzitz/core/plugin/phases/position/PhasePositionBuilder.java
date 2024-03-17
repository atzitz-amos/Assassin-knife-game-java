package org.atzitz.core.plugin.phases.position;

import org.atzitz.core.plugin.phases.IPhase;

public class PhasePositionBuilder {
    private int priority = 1;
    private boolean isFirst = false;
    private Class<? extends IPhase> after = null;
    private Class<? extends IPhase> during = null;
    private boolean firstTurn = false;
    private boolean firstNight = false;

    public PhasePositionBuilder priority(int priority) {
        this.priority = priority;
        return this;
    }

    public PhasePositionBuilder setAsFirst() {
        isFirst = true;
        return this;
    }

    public PhasePositionBuilder after(Class<? extends IPhase> after) {
        this.after = after;
        return this;
    }

    public PhasePositionBuilder during(Class<? extends IPhase> during) {
        this.during = during;
        return this;
    }

    public PhasePositionBuilder firstTurn() {
        firstTurn = true;
        return this;
    }

    public PhasePositionBuilder firstNight() {
        firstNight = true;
        return this;
    }


    public PhasePosition build() {
        return new PhasePosition(priority, isFirst, after, during, firstTurn, firstNight);
    }

}