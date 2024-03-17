package org.atzitz.core.game;

import lombok.Getter;
import lombok.Setter;
import org.atzitz.core.exceptions.MalformedPluginData;
import org.atzitz.core.game.internal.GameTimer;
import org.atzitz.core.game.player.Player;
import org.atzitz.core.plugin.PluginLoader;
import org.atzitz.datatypes.constants.GamePhase;
import org.atzitz.datatypes.users.User;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
public class Game {
    private final String name;
    private final PluginLoader plugins;

    private final Player author;
    private final Collection<Player> players;
    private final GameConfig gc;

    private GamePhase phase = GamePhase.PHASE_BEGIN;

    private GameTimer timer;

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
        if (phase != GamePhase.PHASE_BEGIN) return null;
        if (players.contains(player)) return player;
        if (players.size() == gc.getMaxPlayers() - 1) phase = GamePhase.PHASE_AWAITING_READY;

        player.setGame(this);
        players.add(player);

        return player;
    }

    public boolean setReady(Player player) {
        if (phase != GamePhase.PHASE_AWAITING_READY) return false;
        if (player.isAuthor() || !players.contains(player)) return false;

        player.setReady(true);
        if (players.stream().filter(Player::isReady).count() == gc.getMaxPlayers() - 1) {
            phase = GamePhase.PHASE_AWAITING_START;
        }
        return true;
    }


    // Game Functioning
    public boolean start() throws MalformedPluginData {
        if (phase != GamePhase.PHASE_AWAITING_START) return false;

        timer = new GameTimer(plugins.getPhases());

        phase = GamePhase.PHASE_STARTED;
        return true;
    }
}
