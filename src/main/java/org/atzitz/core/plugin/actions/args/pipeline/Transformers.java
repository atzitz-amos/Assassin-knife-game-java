package org.atzitz.core.plugin.actions.args.pipeline;

import org.atzitz.core.game.player.Player;
import org.atzitz.core.plugin.actions.args.pipeline.transforms.ITransformer;
import org.atzitz.core.plugin.actions.args.pipeline.transforms.PlayerTransformer;

public final class Transformers {
    public static ITransformer<String, Player> toPlayer() {
        return new PlayerTransformer();
    }
}
