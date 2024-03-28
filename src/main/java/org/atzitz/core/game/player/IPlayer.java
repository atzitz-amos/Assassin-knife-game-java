package org.atzitz.core.game.player;

import org.atzitz.core.plugin.roles.AbstractRole;

public interface IPlayer {
    AbstractRole getRole();

    String getId();
}
