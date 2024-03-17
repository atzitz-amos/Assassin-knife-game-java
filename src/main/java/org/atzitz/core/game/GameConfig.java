package org.atzitz.core.game;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameConfig {
    private int maxPlayers;

    public GameConfig(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
}
