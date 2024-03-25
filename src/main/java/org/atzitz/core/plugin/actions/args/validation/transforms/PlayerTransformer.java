package org.atzitz.core.plugin.actions.args.validation.transforms;

import org.atzitz.core.exceptions.ArgumentTransformerFailure;
import org.atzitz.core.game.Game;
import org.atzitz.core.game.player.Player;
import org.atzitz.datatypes.users.User;

import java.util.Objects;

public class PlayerTransformer extends AbstractTransformer<Player> {
    @Override
    public Player transform(Game g, Object o) throws ArgumentTransformerFailure {
        if (o instanceof String s) {
            return g.getPlayers().stream().filter(p -> Objects.equals(p.getUser().id(), s)).findFirst().orElseThrow(ArgumentTransformerFailure::new);
        } else if(o instanceof User u) {
            return g.getPlayers().stream().filter(p -> Objects.equals(p.getUser().id(), u.id())).findFirst().orElseThrow(ArgumentTransformerFailure::new);
        }
        throw new ArgumentTransformerFailure();
    }
}
