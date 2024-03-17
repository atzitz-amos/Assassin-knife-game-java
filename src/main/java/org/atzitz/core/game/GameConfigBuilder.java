package org.atzitz.core.game;

public class GameConfigBuilder {
    private int maxPlayers;

    public GameConfigBuilder setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
        return this;
    }

    public GameConfig build() {
        return new GameConfig(maxPlayers);
    }
}