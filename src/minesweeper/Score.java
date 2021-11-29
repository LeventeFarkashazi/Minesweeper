package minesweeper;

import java.io.Serializable;

/**
 * The type Score.
 */
public record Score(String playerName, Difficulty diff, int time) implements Serializable {

    /**
     * Gets player name.
     *
     * @return the player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Gets diff.
     *
     * @return the diff
     */
    public Difficulty getDiff() {
        return diff;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public int getTime() {
        return time;
    }

}

