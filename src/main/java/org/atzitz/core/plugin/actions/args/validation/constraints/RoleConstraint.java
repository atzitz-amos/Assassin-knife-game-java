package org.atzitz.core.plugin.actions.args.validation.constraints;

import org.atzitz.core.game.player.Player;
import org.atzitz.core.plugin.roles.AbstractRole;

public class RoleConstraint extends AbstractConstraint<Player> {

    private final Class<? extends AbstractRole> role;

    public RoleConstraint(Class<? extends AbstractRole> role) {
        super();
        this.role = role;
    }

    @Override
    public boolean validate(Player value) {
        return value.getRole().is(role);
    }
}
