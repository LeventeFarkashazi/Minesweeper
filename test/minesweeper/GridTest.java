package minesweeper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GridTest {
    Grid grid;

    @Before
    public void setUp() {
        grid = Grid.getInstance();
        grid.resetGrid();
    }

    @Test
    public void testGetInstance() {
        Grid g1 = Grid.getInstance();
        Grid g2 = Grid.getInstance();
        assertSame(g1, g2);
        assertNotSame(g1, grid);
    }

    @Test
    public void testPutBombs() {
        for (int i = 0; i < 2; i++) {
            grid.putBombs(GameLogic.getInstance().getHeight() * GameLogic.getInstance().getWidth() - GameLogic.getInstance().getBombs());
            assertTrue(grid.isTileBomb(0, 0));
            grid = Grid.getInstance();
        }
    }

    @Test
    public void testFlag() {
        assertFalse(grid.isTileFlagged(0, 0));
        assertFalse(grid.isTileMarked(0, 0));

        grid.flag(0, 0);
        assertTrue(grid.isTileFlagged(0, 0));
        assertFalse(grid.isTileMarked(0, 0));

        grid.flag(0, 0);
        assertFalse(grid.isTileFlagged(0, 0));
        assertTrue(grid.isTileMarked(0, 0));

        grid.flag(0, 0);
        assertFalse(grid.isTileFlagged(0, 0));
        assertFalse(grid.isTileMarked(0, 0));
    }

    @Test
    public void testReveal() {
        grid.putBombs(GameLogic.getInstance().getHeight() * GameLogic.getInstance().getWidth());
        grid.reveal(0, 0);
        assertTrue(grid.isExploded());
    }

    @Test
    public void testInGrid() {
        assertTrue(grid.inGrid(0, 0));
        assertFalse(grid.inGrid(GameLogic.getInstance().getWidth(), GameLogic.getInstance().getHeight()));
    }
}
