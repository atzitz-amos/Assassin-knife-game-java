package org.atzitz.core.game.player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.atzitz.core.game.Game;
import org.atzitz.core.plugin.roles.AbstractRole;
import org.atzitz.datatypes.users.User;

@RequiredArgsConstructor(staticName = "of")
@Getter
public class Player implements IPlayer {
    private final User user;
    private final Game game;
    private final boolean isAuthor;

    private @Setter AbstractRole role = null;
    private @Setter boolean isReady;

    private @Setter boolean alive = true;

    public static Player of(User user, Game game) {
        return new Player(user, game, false);
    }

    @Override
    public String getId() {
        return user.id();
    }

}
