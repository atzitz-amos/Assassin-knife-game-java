package org.atzitz.core.game.player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.atzitz.core.game.Game;
import org.atzitz.core.plugin.phases.IPhase;
import org.atzitz.core.plugin.roles.AbstractRole;
import org.atzitz.datatypes.users.User;

@RequiredArgsConstructor(staticName = "of")
@Getter
public class Player implements IPlayer {
    private final User user;
    private final Game game;
    private @Setter AbstractRole role = null;

    private final boolean isAuthor;
    private @Setter boolean isReady;

    public static Player of(User user, Game game){
        return new Player(user, game, false);
    }
}
