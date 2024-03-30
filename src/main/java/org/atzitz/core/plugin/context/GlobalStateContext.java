package org.atzitz.core.plugin.context;

import org.atzitz.core.game.Game;
import org.atzitz.core.game.internal.*;
import org.atzitz.core.game.internal.messages.GameMessages;
import org.atzitz.core.plugin.phases.IPhase;
import org.atzitz.datatypes.constants.GameState;

public class GlobalStateContext {
    public final Game game;

    public final GameVotes votes;
    public final GameTimer timer;
    public final GameData data;
    public final GameMessages messages;

    public final PlayerPool players;

    public final IPhase currentPhase;
    public final GameState gameState;

    public GlobalStateContext(Game game) {
        this.game = game;

        this.votes = game.getVotes();
        this.timer = game.getTimer();
        this.data = game.getData();
        this.messages = game.getMessages();
        this.players = game.getPlayers();

        this.currentPhase = timer.getPhase(this);
        this.gameState = game.getGameState();
    }
}
