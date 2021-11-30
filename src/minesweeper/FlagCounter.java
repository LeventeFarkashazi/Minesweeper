package minesweeper;

import javax.swing.*;
import java.awt.*;

public class FlagCounter extends JPanel {

    private static FlagCounter instance;
    SevenSegmentDisplay display;
    private int remainingFlags;

    private FlagCounter() {
        remainingFlags = GameLogic.getInstance().getBombs();
        setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        display = new SevenSegmentDisplay(this, remainingFlags);
    }

    public static FlagCounter getInstance() {
        if (instance == null) {
            instance = new FlagCounter();
        }
        return instance;
    }

    public void increment() {
        remainingFlags++;
        draw();
    }

    public void decrement() {
        remainingFlags--;
        draw();
    }

    public void resetFlags() {
        instance = new FlagCounter();
    }

    public void draw() {
        display.drawDigits(remainingFlags);
    }

    public int getRemainingFlags() {
        return remainingFlags;
    }
}
