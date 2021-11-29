package minesweeper;

import java.io.Serializable;

/**
 * Az adatok tárolásához használt adategység. Minden példánya egy rekordot reprezentál.
 */
public record Score(String playerName, Difficulty diff, int time) implements Serializable {

    /**
     * Visszaadja a játékos nevét.
     *
     * @return a játékos neve
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Visszaadja a játék nehézségi szintjét.
     *
     * @return a játék nehézségi szintje
     */
    public Difficulty getDiff() {
        return diff;
    }

    /**
     * Visszaadja a játék hosszát másodpercben.
     *
     * @return a játék hossza (mp).
     */
    public int getTime() {
        return time;
    }

}

