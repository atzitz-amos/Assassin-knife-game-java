package org.atzitz.core.game;

import lombok.Getter;
import org.atzitz.core.exceptions.MalformedPluginData;
import org.atzitz.core.game.internal.GameData;
import org.atzitz.core.game.internal.GameMessages;
import org.atzitz.core.game.internal.GameTimer;
import org.atzitz.core.game.internal.GameVotes;
import org.atzitz.core.game.player.Player;
import org.atzitz.core.plugin.PluginLoader;
import org.atzitz.core.plugin.actions.IAction;
import org.atzitz.core.plugin.context.InGameContext;
import org.atzitz.datatypes.constants.ActionResult;
import org.atzitz.datatypes.constants.GameState;
import org.atzitz.datatypes.constants.GameTimerEventType;
import org.atzitz.datatypes.users.User;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class Game {
    private final String name;
    private final PluginLoader plugins;

    private final Player author;
    private final Collection<Player> players;
    private final GameConfig gc;

    private GameState gameState = GameState.PHASE_BEGIN;

    private GameTimer timer;
    private GameMessages messages;
    private GameVotes votes;
    private GameData data;

    // Game Init

    public Game(String name, User author, GameConfig gc, PluginLoader plugins) {
        this.name = name;
        this.author = Player.of(author);
        this.gc = gc;
        this.plugins = plugins;

        this.author.setAuthor(true);

        this.players = new ArrayList<>();

    }

    public Player join(User user) {
        return players.stream().filter(player -> player.getUser() == user).findFirst().orElseGet(() -> join(Player.of(user)));
    }

    public Player join(Player player) {
        if (gameState != GameState.PHASE_BEGIN) return null;
        if (players.contains(player)) return player;
        if (players.size() == gc.getMaxPlayers() - 1) gameState = GameState.PHASE_AWAITING_READY;

        player.setGame(this);
        players.add(player);

        return player;
    }

    public boolean setReady(Player player) {
        if (gameState != GameState.PHASE_AWAITING_READY) return false;
        if (player.isAuthor() || !players.contains(player)) return false;

        player.setReady(true);
        if (players.stream().filter(Player::isReady).count() == gc.getMaxPlayers() - 1) {
            gameState = GameState.PHASE_AWAITING_START;
        }
        return true;
    }


    // Game Functioning
    public boolean start() throws MalformedPluginData {
        if (gameState != GameState.PHASE_AWAITING_START) return false;

        timer = new GameTimer(plugins.getPhases());
        messages = new GameMessages();
        votes = new GameVotes();
        data = new GameData();

        setupTimer();

        gameState = GameState.PHASE_STARTED;

        timer.start();
        return true;
    }

    private void setupTimer() {
        timer.setupPhases();
        timer.addPhaseHook(GameTimerEventType.PHASE_START, null, gameTimer -> handleNewPhase());
    }

    // Game Logic
    private void handleNewPhase() {

    }

    public ActionResult play(IAction action, Player player) {

        return ActionResult.SUCCESS;
    }

    private InGameContext createContext(Player player) {
        return new InGameContext(this, player);
    }
}
