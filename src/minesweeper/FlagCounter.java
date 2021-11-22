package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FlagCounter extends JPanel {
    private static FlagCounter instance;

    private int remainingFlags;
    private final BufferedImage[] digits;

    public static FlagCounter getInstance() {
        if (instance == null) {
            instance = new FlagCounter();
        }
        return instance;
    }

    private FlagCounter() {
        remainingFlags = GameLogic.getInstance().getBombs();
        digits = ImageReader.getInstance().getDigits();
        setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        draw();
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
        removeAll();
        String digitString = String.format("%03d", Math.min(remainingFlags, 999));
        JLabel digitLabel1 = new JLabel(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(0))]));
        add(digitLabel1);
        JLabel digitLabel2 = new JLabel(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(1))]));
        add(digitLabel2);
        JLabel digitLabel3 = new JLabel(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(2))]));
        add(digitLabel3);
    }

    public int getRemainingFlags() {
        return remainingFlags;
    }
}
