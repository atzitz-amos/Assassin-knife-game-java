package org.atzitz.core.plugin.actions.args.validation;

import org.atzitz.core.plugin.actions.args.validation.constraints.RoleConstraint;
import org.atzitz.core.plugin.roles.AbstractRole;

public final class Constraints {
    public static RoleConstraint isRole(Class<? extends AbstractRole> role) {
        return new RoleConstraint(role);
    }
}
