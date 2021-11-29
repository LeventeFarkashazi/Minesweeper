package minesweeper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameLogicTest {
    GameLogic gameLogic;

    @Before
    public void setUp() {
        gameLogic = GameLogic.getInstance();
    }

    @Test
    public void testInitGame() {
        gameLogic.initGame();
        assertNotNull(gameLogic.getFrame());
        assertNotNull(gameLogic.getGrid());
    }

    @Test
    public void testSetDifficulty() {
        gameLogic.setDifficulty(Difficulty.OVERKILL);
        assertEquals(16, gameLogic.getHeight());
        assertEquals(30, gameLogic.getWidth());
        assertEquals(99, gameLogic.getBombs());
    }

    @Test
    public void testGetInstance() {
        GameLogic gl1 = GameLogic.getInstance();
        GameLogic gl2 = GameLogic.getInstance();
        assertSame(gl1, gl2);
        assertSame(gl1, gameLogic);
    }

    @Test
    public void testFirstTileLeftClick() {
        for (int i = 0; i < 10; i++) {
            gameLogic.initGame();
            gameLogic.tileLeftClick(0, 0);

            assertTrue(gameLogic.isGameStarted());
            assertFalse(gameLogic.getGrid().isExploded());
        }

        gameLogic.setDifficulty(Difficulty.SZOFTTECH);

        for (int i = 0; i < 10; i++) {
            gameLogic.initGame();
            gameLogic.tileLeftClick(0, 0);

            assertTrue(gameLogic.isGameStarted());
            assertTrue(gameLogic.getGrid().isExploded());
        }
    }

    @After
    public void reset() {
        gameLogic.setDifficulty(Difficulty.INTERMEDIATE);
    }
}
