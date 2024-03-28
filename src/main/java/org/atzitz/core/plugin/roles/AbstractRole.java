package org.atzitz.core.plugin.roles;

import org.atzitz.core.plugin.teams.ITeam;

public abstract class AbstractRole {
    public abstract String getId();

    public abstract String getKeyPath();

    public abstract Class<? extends ITeam> getTeam();

    public boolean is(Class<? extends AbstractRole> role) {
        return role.isInstance(this);
    }
}
