package org.atzitz.plugins.default_plugin.roles;

import org.atzitz.core.plugin.roles.IRole;
import org.atzitz.core.plugin.teams.ITeam;
import org.atzitz.plugins.default_plugin.teams.AssassinTeam;

public class AssassinRole implements IRole {
    @Override
    public String getId() {
        return "roles.default.assassin";
    }

    @Override
    public String getKeyPath() {
        return "roles.assassin";
    }

    @Override
    public Class<? extends ITeam> getTeam() {
        return AssassinTeam.class;
    }
}
