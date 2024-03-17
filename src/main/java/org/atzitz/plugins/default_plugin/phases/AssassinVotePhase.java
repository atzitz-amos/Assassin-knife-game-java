package org.atzitz.plugins.default_plugin.phases;

import org.atzitz.core.plugin.context.AbstractContext;
import org.atzitz.core.plugin.phases.IPhase;
import org.atzitz.core.plugin.phases.position.PhasePosition;
import org.atzitz.core.plugin.phases.position.PhasePositionBuilder;
import org.springframework.stereotype.Component;

@Component
public class AssassinVotePhase implements IPhase {
    private final PhasePosition position = new PhasePositionBuilder()
            .during(NightPhase.class)
            .setAsFirst()
            .build();

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getKeyPath() {
        return null;
    }

    @Override
    public PhasePosition getPhasePosition() {
        return position;
    }

    @Override
    public boolean shouldShow(AbstractContext context) {
        return IPhase.super.shouldShow(context);
    }
}
