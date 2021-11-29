package minesweeper;

/**
 * A játék main osztálya.
 * @author Farkasházi Levente
 */
public class Main {
    /**
     * A játék belépési pontja.
     *
     * @param args parancssori argumentumok
     */
    public static void main(String[] args) {
        GameLogic.getInstance().initGame();
    }
}