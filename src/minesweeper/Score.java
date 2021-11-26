package minesweeper;

import java.io.Serializable;

public record Score(String playerName, Difficulty diff, int time) implements Serializable {

    public String getPlayerName() {
        return playerName;
    }

    public Difficulty getDiff() {
        return diff;
    }

    public int getTime() {
        return time;
    }

}

