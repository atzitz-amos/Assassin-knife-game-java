package org.atzitz.core.game;

import lombok.RequiredArgsConstructor;
import org.atzitz.core.plugin.PluginLoader;
import org.atzitz.datatypes.users.User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameFactory {
    private final PluginLoader plugins;

    public Game newGame(String name) {
        return new Game(
                name,
                new User("me", "Me"),
                new GameConfigBuilder().setMaxPlayers(4).build(),
                plugins
        );
    }
}
