package minesweeper;

/**
 * A játékban található, váasztható nehézségi szinteket reprezentálja.
 * A nehézségi szintek kizárólag az aknamező méretét és a bombák számát határozzák meg.
 */
public enum Difficulty {
    /**
     * Crybaby nehézségi szint.
     */
    CRYBABY("Crybaby"),
    /**
     * Easy nehézségi szint.
     */
    EASY("Easy"),
    /**
     * Intermediate nehézségi szint.
     */
    INTERMEDIATE("Intermediate"),
    /**
     * Overkill nehézségi szint.
     */
    OVERKILL("Overkill"),
    /**
     * Death wish nehézségi szint.
     */
    DEATH_WISH("Death Wish"),
    /**
     * Feeling lucky nehézségi szint.
     */
    FEELING_LUCKY("Feeling Lucky"),
    /**
     * Szofttech nehézségi szint.
     */
    SZOFTTECH("Szofttech");

    private final String name;

    Difficulty(String s) {
        name = s;
    }

    /**
     * Visszatér a nehézségi szint nevével.
     *
     * @return a nehézségi szint neve
     */
    public String toString() {
        return this.name;
    }
}

