package minesweeper;

import javax.swing.*;
import java.awt.*;

/**
 * The type Timer.
 */
class Timer extends JPanel {
    private static Timer instance;
    private final Runnable counter;
    /**
     * The Seconds.
     */
    int seconds;
    /**
     * The Display.
     */
    SevenSegmentDisplay display;
    private boolean running;

    private Timer() {
        seconds = 0;
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        display = new SevenSegmentDisplay(this, 0);
        display.displayInactive();

        counter = () -> {
            while (running) {
                seconds++;
                drawTimer();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Timer getInstance() {
        if (instance == null) {
            instance = new Timer();
        }
        return instance;
    }

    private void drawTimer() {
        display.drawDigits(seconds);
    }

    /**
     * Is killed boolean.
     *
     * @return the boolean
     */
    public boolean isKilled() {
        return !running;
    }

    /**
     * Start.
     */
    public void start() {
        running = true;
        new Thread(counter).start();
    }

    /**
     * Stop.
     */
    public void stop() {
        running = false;
        drawTimer();
    }

    /**
     * Reset timer.
     */
    public void resetTimer() {
        instance = new Timer();
        drawTimer();
    }

    /**
     * Gets seconds.
     *
     * @return the seconds
     */
    public int getSeconds() {
        return seconds;
    }

}