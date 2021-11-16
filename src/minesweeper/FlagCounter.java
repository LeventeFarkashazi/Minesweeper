package minesweeper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FlagCounter extends JPanel {
    int remainingFlags;
    BufferedImage[] digits;

    FlagCounter(int bombs){
        remainingFlags = bombs;
        setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        readPictures();
        draw();
    }

    public void increment(){
        remainingFlags++;
        draw();
    }
    public void decrement(){
        remainingFlags--;
        draw();
    }

    public void draw(){
        removeAll();
        String digitString = String.format("%03d", remainingFlags);
        JLabel digitLabel1 = new JLabel(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(0))]));
        add(digitLabel1);
        JLabel digitLabel2 = new JLabel(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(1))]));
        add(digitLabel2);
        JLabel digitLabel3 = new JLabel(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(2))]));
        add(digitLabel3);
    }

    public void readPictures(){
        digits = new BufferedImage[11];
        for (int i = 0; i < 11; i++) {
            try {
                digits[i] = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\digits\\" + i + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int getRemainingFlags() {
        return remainingFlags;
    }
}
