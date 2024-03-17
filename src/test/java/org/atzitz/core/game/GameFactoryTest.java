package org.atzitz.core.game;

import org.atzitz.Main;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(Main.class)
class GameFactoryTest {
    @Autowired
    private GameFactory gameFactory;

    @Test
    void myTest() {
        Game game = gameFactory.newGame("");
    }
}