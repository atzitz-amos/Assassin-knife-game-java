package org.atzitz.core.plugin.actions.args.pipeline.constraints;

import org.atzitz.core.exceptions.ArgumentConstraintFailure;
import org.atzitz.core.game.player.Player;
import org.atzitz.core.plugin.context.InGameContext;
import org.atzitz.core.plugin.roles.AbstractRole;

public class RoleConstraint implements IConstraint<Player> {

    private final Class<? extends AbstractRole> role;

    public RoleConstraint(Class<? extends AbstractRole> role) {
        this.role = role;
    }
    
    @Override
    public void validate(InGameContext ctx, Player value) throws ArgumentConstraintFailure {
        if (value.getRole().is(role)) {
            return;
        }
        throw new ArgumentConstraintFailure(this);
    }
}
