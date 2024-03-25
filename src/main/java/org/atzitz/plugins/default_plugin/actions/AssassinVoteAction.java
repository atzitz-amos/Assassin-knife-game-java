package org.atzitz.plugins.default_plugin.actions;

import org.atzitz.core.plugin.actions.IAction;
import org.atzitz.core.plugin.actions.args.Arg;
import org.atzitz.core.plugin.actions.args.ArgBuilder;
import org.atzitz.core.plugin.actions.args.validation.Constraints;
import org.atzitz.core.plugin.actions.args.validation.Transformers;
import org.atzitz.plugins.default_plugin.roles.AssassinRole;

public class AssassinVoteAction implements IAction {

    @Override
    public Arg[] requiredArgs() {
        return new Arg[] {
            new ArgBuilder().name("player").transform(Transformers.toPlayer()).ensure(Constraints.isRole(AssassinRole.class)).build()
        };
    }
}
