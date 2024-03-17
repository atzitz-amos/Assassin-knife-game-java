package org.atzitz.plugins.default_plugin.teams;

import org.atzitz.core.plugin.teams.ITeam;

public class AssassinTeam implements ITeam {
    @Override
    public String getId() {
        return "teams.default.assassin";
    }

    @Override
    public String getKeyPath() {
        return "teams.assassin";
    }

    @Override
    public boolean checkVictory() {
        return false;
    }
}
