package org.atzitz.core.plugin.roles;

import org.atzitz.core.plugin.actions.ActionsHandler;
import org.atzitz.core.plugin.teams.ITeam;

public abstract class AbstractRole extends ActionsHandler {
    public abstract String getId();

    public abstract String getKeyPath();

    public abstract Class<? extends ITeam> getTeam();
}
