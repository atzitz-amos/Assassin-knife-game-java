package org.atzitz.core.plugin.phases.position;

import org.atzitz.core.plugin.phases.IPhase;

public record PhasePosition(int priority, boolean isFirst, Class<? extends IPhase> after,
                            Class<? extends IPhase> during, boolean firstTurnOnly, boolean firstNightOnly) {

}
