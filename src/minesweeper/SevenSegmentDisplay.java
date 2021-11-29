package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The type Seven segment display.
 */
public class SevenSegmentDisplay {
    /**
     * The Digits.
     */
    BufferedImage[] digits;
    /**
     * The Digit label 1.
     */
    JLabel digitLabel1, /**
     * The Digit label 2.
     */
    digitLabel2, /**
     * The Digit label 3.
     */
    digitLabel3;
    /**
     * The Timer panel.
     */
    JPanel timerPanel;

    /**
     * Instantiates a new Seven segment display.
     *
     * @param timerPanel       the timer panel
     * @param initDisplayValue the init display value
     */
    SevenSegmentDisplay(JPanel timerPanel, int initDisplayValue) {
        timerPanel.setBorder(BorderFactory.createMatteBorder(12, 12, 12, 12, Color.LIGHT_GRAY));
        this.timerPanel = timerPanel;
        digits = ImageReader.getInstance().getDigits();

        String digitString = String.format("%03d", Math.min(initDisplayValue, 999));
        digitLabel1 = new JLabel(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(0))]));
        timerPanel.add(digitLabel1);
        digitLabel2 = new JLabel(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(1))]));
        timerPanel.add(digitLabel2);
        digitLabel3 = new JLabel(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(2))]));
        timerPanel.add(digitLabel3);
    }

    /**
     * Draw digits.
     *
     * @param displayValue the display value
     */
    public void drawDigits(int displayValue) {
        String digitString = String.format("%03d", Math.min(displayValue, 999));
        digitLabel1.setIcon(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(0))]));
        digitLabel2.setIcon(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(1))]));
        digitLabel3.setIcon(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(2))]));
    }

    /**
     * Display inactive.
     */
    public void displayInactive() {
        digitLabel1.setIcon(new ImageIcon(digits[10]));
        digitLabel2.setIcon(new ImageIcon(digits[10]));
        digitLabel3.setIcon(new ImageIcon(digits[10]));
    }
}
