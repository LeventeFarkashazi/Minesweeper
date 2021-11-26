package minesweeper;

public enum Difficulty {
    CRYBABY("Crybaby"),
    EASY("Easy"),
    INTERMEDIATE("Intermediate"),
    OVERKILL("Overkill"),
    DEATH_WISH("Death Wish"),
    FEELING_LUCKY("Feeling Lucky"),
    SZOFTTECH("Szofttech");

    private final String name;

    Difficulty(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }

}

