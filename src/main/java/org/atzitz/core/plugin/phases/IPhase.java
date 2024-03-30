package org.atzitz.core.plugin.phases;

import org.atzitz.core.plugin.context.GlobalStateContext;
import org.atzitz.core.plugin.context.InGameContext;
import org.atzitz.core.plugin.phases.position.PhasePosition;

public interface IPhase {
    String getId();

    String getKeyPath();

    PhasePosition getPhasePosition();


    default boolean is(Class<? extends IPhase> other) {
        return other.isInstance(this);
    }

    default boolean isExclusive() {
        return false;
    }

    default boolean shouldShow(InGameContext context) {
        return true;
    }

    int getDuration();

    default void phaseHookBegin(GlobalStateContext ctx) {
    }

    default void phaseHookEnd(GlobalStateContext ctx) {
    }

    default void phaseHookTick(GlobalStateContext ctx) {
    }
}
