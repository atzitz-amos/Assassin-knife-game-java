package org.atzitz.core.plugin.context;

import org.atzitz.core.game.Game;
import org.atzitz.core.game.internal.*;
import org.atzitz.core.game.player.Player;
import org.atzitz.core.plugin.phases.IPhase;
import org.atzitz.datatypes.constants.GameState;

public class InGameContext {
    public final Game game;
    public final Player player;

    public final GameVotes votes;
    public final GameTimer timer;
    public final GameData data;
    public final GameMessages messages;

    public final PlayerPool players;

    public final IPhase currentPhase;
    public final GameState gameState;


    public InGameContext(Game game, Player player) {
        this.game = game;
        this.player = player;

        this.players = game.getPlayers();

        this.votes = game.getVotes();
        this.timer = game.getTimer();
        this.data = game.getData();
        this.messages = game.getMessages();

        this.currentPhase = timer.getPhase(this);
        this.gameState = game.getGameState();
    }
}
