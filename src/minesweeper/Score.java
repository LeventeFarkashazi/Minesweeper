package minesweeper;

import java.io.Serializable;

public class Score implements Serializable {

    private final String playerName;
    private final Difficulty diff;
    private final int time;

    public Score(String playerName, Difficulty diff, int time) {
        this.playerName = playerName;
        this.diff = diff;
        this.time = time;
    }

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

