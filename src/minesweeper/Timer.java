package minesweeper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


class Timer extends JPanel {
    JPanel panel;
    BufferedImage[] digits;
    private boolean running;
    private final Runnable counter;
    int seconds = 0;

    Timer() {
        setBorder(BorderFactory.createMatteBorder(-1, -1, -1, -1, Color.red));
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        readPictures();
        for(int i=0;i<3;i++) {
            JLabel digitLabel = new JLabel(new ImageIcon(digits[10]));
            add(digitLabel);
        }

        counter = () -> {
            while (running) {
                removeAll();
                String digitString = String.format("%03d", seconds++);
                JLabel digitLabel1 = new JLabel(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(0))]));
                add(digitLabel1);
                JLabel digitLabel2 = new JLabel(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(1))]));
                add(digitLabel2);
                JLabel digitLabel3 = new JLabel(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(2))]));
                add(digitLabel3);
                javax.swing.SwingUtilities.updateComponentTreeUI(panel);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void addPanel(JPanel panel){
        this.panel=panel;
    }

    public void readPictures() {
        digits = new BufferedImage[11];
        for (int i = 0; i < 11; i++) {
            try {
                digits[i] = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\digits\\" + i + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isKilled() {
        return !running;
    }

    public void start() {
        running = true;
        new Thread(counter).start();
    }

    public void stop() {
        running = false;
    }
}