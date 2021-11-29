package minesweeper;

import javax.swing.*;
import java.awt.*;

/**
 * A játékban található zászló számláló panel.
 * Csak egy példánya lehet, azaz singleton osztály.
 */
public class FlagCounter extends JPanel {

    private static FlagCounter instance;
    SevenSegmentDisplay display;
    private int remainingFlags;

    /**
     * Konstruktor.
     * Példányosítja a játékban található zászló számláló panelt.
     * Beállítja a számláló kezdő értékét és létrehozza a megjelenítő osztály egy példányát.
     */
    private FlagCounter() {
        remainingFlags = GameLogic.getInstance().getBombs();
        setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        display = new SevenSegmentDisplay(this, remainingFlags);
    }

    /**
     * Visszadja a zászló számláló panel egyetlen létező példányát.
     * Ha az osztálynak még nem létezik példánya, létrehoz egyet.
     *
     * @return a számláló példánya
     */
    public static FlagCounter getInstance() {
        if (instance == null) {
            instance = new FlagCounter();
        }
        return instance;
    }

    /**
     * Megnöveli a megmaradt zászlók értékét eggyel.
     */
    public void increment() {
        remainingFlags++;
        draw();
    }

    /**
     * Levon egyet a megmaradt zászlók számából.
     */
    public void decrement() {
        remainingFlags--;
        draw();
    }

    /**
     * Újraindítja a számlálót. Gyakorlatilag készít belőle egy új példányt.
     */
    public void resetFlags() {
        instance = new FlagCounter();
    }

    /**
     * Frissíti a zászlók számát kirajzoló kijelzőt (átadja a megmaradt zászlók számát).
     */
    public void draw() {
        display.drawDigits(remainingFlags);
    }

    /**
     * Visszaadja a megmaradt zászlók számát
     *
     * @return megmaradt zászlók száma
     */
    public int getRemainingFlags() {
        return remainingFlags;
    }
}
