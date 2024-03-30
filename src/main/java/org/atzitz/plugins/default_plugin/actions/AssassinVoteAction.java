package org.atzitz.plugins.default_plugin.actions;

import org.atzitz.core.exceptions.ArgumentConstraintFailure;
import org.atzitz.core.exceptions.ArgumentTransformerFailure;
import org.atzitz.core.exceptions.MissingRequiredArgumentException;
import org.atzitz.core.game.player.Player;
import org.atzitz.core.plugin.actions.AbstractAction;
import org.atzitz.core.plugin.actions.args.ArgsMap;
import org.atzitz.core.plugin.actions.args.pipeline.Constraints;
import org.atzitz.core.plugin.actions.args.pipeline.Transformers;
import org.atzitz.core.plugin.context.InGameContext;
import org.atzitz.datatypes.constants.ActionResult;
import org.atzitz.plugins.default_plugin.roles.AssassinRole;
import org.springframework.stereotype.Component;

@Component
public class AssassinVoteAction extends AbstractAction {

    @Override
    public ActionResult execute(InGameContext ctx, ArgsMap args) throws
            MissingRequiredArgumentException,
            ArgumentConstraintFailure,
            ArgumentTransformerFailure {

        Player player = args.require("player")
                .pipe()
                .transform(Transformers.toPlayer())
                .ensure(Constraints.isRole(AssassinRole.class))
                .collect();
        System.out.println("Found player " + player.getUser().name());
        return ActionResult.SUCCESS;
    }

}
