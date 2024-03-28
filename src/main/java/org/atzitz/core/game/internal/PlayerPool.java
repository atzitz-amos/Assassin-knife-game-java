package org.atzitz.core.game.internal;

import lombok.Getter;
import org.atzitz.core.game.player.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Getter
public class PlayerPool {
    private final Map<String, Player> players;

    public PlayerPool() {
        this.players = new HashMap<>();
    }

    public boolean contains(Player player) {
        return players.containsKey(player.getId());
    }

    public int size() {
        return players.size();
    }

    public Optional<Player> fetch(String id) {
        return Optional.of(players.get(id));
    }

    public void add(Player player) {
        players.put(player.getId(), player);
    }

    public Collection<Player> filter(Predicate<Player> predicate) {
        return players.values().stream().filter(predicate).collect(Collectors.toList());
    }
}