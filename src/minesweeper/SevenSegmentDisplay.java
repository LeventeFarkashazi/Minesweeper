package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A játékban használt 3 számjegyes 7 szegmenses kijelzőt megvalósító osztály.
 */
public class SevenSegmentDisplay {
    BufferedImage[] digits;
    JLabel digitLabel1,
            digitLabel2,
            digitLabel3;
    JPanel timerPanel;

    /**
     * Konstruktor.
     * Példányosítj a kijelzőt.
     * Beállítja a példány attribútumait és inicializálja a kijelzőt a megadott értéknek megfelelően.
     *
     * @param timerPanel       a panel amin meg kell jeleníteni a kijelzőt
     * @param initDisplayValue a kijelző kezdeti értéke
     */
    SevenSegmentDisplay(JPanel timerPanel, int initDisplayValue) {
        timerPanel.setBorder(BorderFactory.createMatteBorder(12, 12, 12, 12, Color.LIGHT_GRAY));
        this.timerPanel = timerPanel;
        //get the digit images
        digits = ImageReader.getInstance().getDigits();

        //init with initDisplayValue
        String digitString = String.format("%03d", Math.min(initDisplayValue, 999));
        digitLabel1 = new JLabel(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(0))]));
        timerPanel.add(digitLabel1);
        digitLabel2 = new JLabel(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(1))]));
        timerPanel.add(digitLabel2);
        digitLabel3 = new JLabel(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(2))]));
        timerPanel.add(digitLabel3);
    }

    /**
     * A megadott displayValue-nek megfelelően állítja be a kijelző számjegyeit, azaz frissíti a kijelző számjegyeit.
     *
     * @param displayValue a kijelzőnek beállítandó érték
     */
    public void drawDigits(int displayValue) {
        //max 999
        String digitString = String.format("%03d", Math.min(displayValue, 999));
        digitLabel1.setIcon(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(0))]));
        digitLabel2.setIcon(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(1))]));
        digitLabel3.setIcon(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(2))]));
    }

    /**
     * Beállítja a kijelző minden számjegyét az inaktív állapotnak megfelelőre, azaz 3db (-) karakterre.
     */
    public void displayInactive() {
        digitLabel1.setIcon(new ImageIcon(digits[10]));
        digitLabel2.setIcon(new ImageIcon(digits[10]));
        digitLabel3.setIcon(new ImageIcon(digits[10]));
    }
}
