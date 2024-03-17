package org.atzitz.core.game.player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.atzitz.core.game.Game;
import org.atzitz.datatypes.users.User;

@RequiredArgsConstructor(staticName = "of")
@Setter
@Getter
public class Player implements IPlayer {
    private final User user;
    private Game game;

    private boolean isAuthor;
    private boolean isReady;
}
