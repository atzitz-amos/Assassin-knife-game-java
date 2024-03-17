package org.atzitz.core.plugin.roles;

import org.atzitz.core.plugin.teams.ITeam;

public interface IRole {
    String getId();

    String getKeyPath();

    Class<? extends ITeam> getTeam();
}
