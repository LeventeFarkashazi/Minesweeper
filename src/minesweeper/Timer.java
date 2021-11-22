package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class Timer extends JPanel {
    private static Timer instance;

    BufferedImage[] digits;
    private boolean running;
    private final Runnable counter;
    int seconds = 0;

    public static Timer getInstance(){
        if(instance == null){
            instance = new Timer();
        }
        return instance;
    }

    private Timer() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        digits = ImageReader.getInstance().getDigits();
        for (int i = 0; i < 3; i++) {
            JLabel digitLabel = new JLabel(new ImageIcon(digits[10]));
            add(digitLabel);
        }

        counter = () -> {
            while (running) {
                removeAll();
                String digitString = String.format("%03d", Math.min(seconds++, 999));
                JLabel digitLabel1 = new JLabel(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(0))]));
                add(digitLabel1);
                JLabel digitLabel2 = new JLabel(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(1))]));
                add(digitLabel2);
                JLabel digitLabel3 = new JLabel(new ImageIcon(digits[Character.getNumericValue(digitString.charAt(2))]));
                add(digitLabel3);
                javax.swing.SwingUtilities.updateComponentTreeUI(this);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
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

    public int getSeconds() {
        return seconds;
    }
}