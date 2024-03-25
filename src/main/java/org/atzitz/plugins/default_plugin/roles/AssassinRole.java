package org.atzitz.plugins.default_plugin.roles;

import org.atzitz.core.plugin.ActionHandler;
import org.atzitz.core.plugin.roles.AbstractRole;
import org.atzitz.core.plugin.teams.ITeam;
import org.atzitz.plugins.default_plugin.actions.AssassinVoteAction;
import org.atzitz.plugins.default_plugin.teams.AssassinTeam;

public class AssassinRole extends AbstractRole {
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

    @ActionHandler(AssassinVoteAction.class)
    public void actionVote() {
    }
}
