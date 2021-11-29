package minesweeper;

import javax.swing.*;
import java.awt.*;

/**
 * A játékban található idő számláló panel.
 * Csak egy példánya lehet, azaz singleton osztály.
 */
class Timer extends JPanel {
    private static Timer instance;
    private final Runnable counter;
    int seconds;
    SevenSegmentDisplay display;
    private boolean running;

    /**
     * Konstruktor.
     * Példányosítja a játékban található idő számláló panelt.
     * Beállítja a számláló értékét 0-ra. Létrehozza a counter Runnable-t.
     */
    private Timer() {
        seconds = 0;
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        display = new SevenSegmentDisplay(this, 0);
        display.displayInactive();

        //runnable
        counter = () -> {
            while (running) {
                //tick
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
     * Visszadja az idő számláló panel egyetlen létező példányát.
     * Ha az osztálynak még nem létezik példánya, létrehoz egyet.
     *
     * @return a számláló példánya
     */
    public static Timer getInstance() {
        if (instance == null) {
            instance = new Timer();
        }
        return instance;
    }

    /**
     * Frissíti az időt kirajzoló kijelzőt az aktuális seconds értékkel.
     */
    private void drawTimer() {
        display.drawDigits(seconds);
    }

    /**
     * Elindítja a számlálót. Létrehoz egy új Thread-et aminek átadja a countert, majd elindítja.
     */
    public void start() {
        running = true;
        new Thread(counter).start();
    }

    /**
     * Megállítja a számlálót.
     */
    public void stop() {
        running = false;
        drawTimer();
    }

    /**
     * Újraindítja a számlálót. Gyakorlatilag készít belőle egy új példányt.
     */
    public void resetTimer() {
        instance = new Timer();
        drawTimer();
    }

    /**
     * Visszaadja sz indítás óta eltelt másodpercek számát.
     *
     * @return eltelt másodpercek
     */
    public int getSeconds() {
        return seconds;
    }

}