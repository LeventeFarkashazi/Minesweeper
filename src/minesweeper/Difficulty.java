package minesweeper;

public enum Difficulty {
    EASY ("Easy"),
    INTERMEDIATE ("Intermediate"),
    OVERKILL ("Overkill"),
    DEATH_WISH("Death Wish"),
    SZOFTTECH("Szofttech");

    private final String name;

    Difficulty(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }


}

