package org.atzitz.core.plugin.context;

import org.atzitz.core.game.Game;
import org.atzitz.core.game.internal.GameData;
import org.atzitz.core.game.internal.GameMessages;
import org.atzitz.core.game.internal.GameTimer;
import org.atzitz.core.game.internal.GameVotes;
import org.atzitz.core.game.player.Player;
import org.atzitz.core.plugin.phases.IPhase;
import org.atzitz.datatypes.constants.GameState;

public class InGameContext implements IContext {
    private final Game _game;
    private final Player _player;

    public InGameContext(Game game, Player player) {
        this._game = game;
        this._player = player;
    }

    public Game game() {
        return _game;
    }

    public GameVotes votes() {
        return _game.getVotes();
    }

    public GameMessages messages() {
        return _game.getMessages();
    }

    public GameTimer timer() {
        return _game.getTimer();
    }

    public GameData data() {
        return _game.getData();
    }

    public IPhase phase() {
        return _game.getTimer().getPhase(this);
    }

    public Player player() {
        return _player;
    }

    public GameState gameState() {
        return _game.getGameState();
    }
}
