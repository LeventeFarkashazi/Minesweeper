package minesweeper;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class SevenSegmentDisplay {
    BufferedImage[] digits;
    JLabel digitLabel1, digitLabel2, digitLabel3;
    JPanel timerPanel;

    SevenSegmentDisplay(JPanel timerPanel,int initDisplayValue){
        this.timerPanel=timerPanel;
        digits = ImageReader.getInstance().getDigits();

        String digitString = String.format("%03d", Math.min(initDisplayValue, 999));
        digitLabel1 = new JLabel(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(0))]));
        timerPanel.add(digitLabel1);
        digitLabel2 = new JLabel(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(1))]));
        timerPanel.add(digitLabel2);
        digitLabel3 = new JLabel(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(2))]));
        timerPanel.add(digitLabel3);
    }

    public void drawDigits(int displayValue) {
        String digitString = String.format("%03d", Math.min(displayValue, 999));
        digitLabel1.setIcon(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(0))]));
        digitLabel2.setIcon(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(1))]));
        digitLabel3.setIcon(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(2))]));
    }

    public void displayInactive() {
        digitLabel1.setIcon(new ImageIcon(digits[10]));
        digitLabel2.setIcon(new ImageIcon(digits[10]));
        digitLabel3.setIcon(new ImageIcon(digits[10]));
    }
}
