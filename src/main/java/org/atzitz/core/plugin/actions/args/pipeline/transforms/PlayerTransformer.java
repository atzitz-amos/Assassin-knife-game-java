package org.atzitz.core.plugin.actions.args.pipeline.transforms;

import org.atzitz.core.exceptions.ArgumentTransformerFailure;
import org.atzitz.core.game.player.Player;
import org.atzitz.core.plugin.context.InGameContext;
import org.jetbrains.annotations.NotNull;

public class PlayerTransformer implements ITransformer<String, Player> {
    @Override
    public @NotNull Player transform(InGameContext ctx, String value) throws ArgumentTransformerFailure {
        return ctx.players.fetch(value).orElseThrow(ArgumentTransformerFailure::new);
    }
}
