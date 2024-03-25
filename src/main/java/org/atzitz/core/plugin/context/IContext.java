package org.atzitz.core.plugin.context;

import org.atzitz.core.game.Game;
import org.atzitz.core.game.player.Player;
import org.atzitz.datatypes.constants.GameState;

public interface IContext {
    Game game();
    Player player();
    GameState gameState();
}
