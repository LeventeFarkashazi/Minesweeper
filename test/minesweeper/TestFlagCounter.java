package minesweeper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestFlagCounter {
    FlagCounter flagCounter;

    @Before
    public void setUp() {
        flagCounter = FlagCounter.getInstance();
        flagCounter.resetFlags();
    }

    @Test
    public void testGetInstance() {
        FlagCounter fc1 = FlagCounter.getInstance();
        FlagCounter fc2 = FlagCounter.getInstance();
        assertSame(fc1, fc2);
        assertNotSame(fc1, flagCounter);
    }

    @Test
    public void testIncrement() {
        flagCounter.increment();
        assertEquals(GameLogic.getInstance().getBombs() + 1, flagCounter.getRemainingFlags());
    }

    @Test
    public void testDecrement() {
        flagCounter.decrement();
        assertEquals(GameLogic.getInstance().getBombs() - 1, flagCounter.getRemainingFlags());
    }
}