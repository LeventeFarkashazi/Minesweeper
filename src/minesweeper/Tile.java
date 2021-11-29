package minesweeper;

/**
 * Az aknamező egy mezőjét reprezentálja.
 */
public class Tile {
    private boolean bomb;
    private int bombNeighbours;
    private boolean visible;
    private boolean flagged;
    private boolean marked;

    /**
     * Konstruktor.
     * Példányosítja a mezőt a megadott paraméterekkel.
     *
     * @param isBomb         bomba-e a mező
     * @param isVisible      fel van-e fedve a mező
     * @param bombNeighbours a mező bomba szomszédainak száma
     */
    Tile(boolean isBomb, boolean isVisible, int bombNeighbours) {
        this.bomb = isBomb;
        this.visible = isVisible;
        this.bombNeighbours = bombNeighbours;
    }

    /**
     * Visszaadja, hogy fel van-e fedve a mező.
     *
     * @return fel van-e fedve a mező
     */
    public boolean isVisible() {
        return visible;
    }

    /**

     * Beállítja, hogy fel van-e fedve a mező.
     *
     * @param visible fel van-e fedve a mező
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Visszaadja, hogy hány bomba szomszédja van a mezőnek.
     *
     * @return a mező bomba szomszédainak száma
     */
    public int getBombNeighbours() {
        return bombNeighbours;
    }

    /**
     * Beállítja, hogy hány bomba szomszédja van a mezőnek.
     *
     * @param n a mező bomba szomszédainak száma
     */
    public void setBombNeighbours(int n) {
        this.bombNeighbours = n;
    }

    /**
     * Egyet ad a mező bomba szomszédainak számához.
     */
    public void AddBombNeighbour() {
        this.bombNeighbours++;
    }

    /**
     * Visszaadja, hogy bomba-e a mező.
     *
     * @return bomba-e a mező
     */
    public boolean isBomb() {
        return bomb;
    }

    /**
     * Beállítja, hogy bomba-e a mező.
     *
     * @param bomb bomba-e a mező
     */
    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    /**
     * Visszaadja, hogy zászlóval jelölt-e a mező.
     *
     * @return zászlóval jelölt-e a mező
     */
    public boolean isFlagged() {
        return flagged;
    }

    /**
     * Beállítja, hogy zászlóval jelölt-e a mező.
     *
     * @param flagged zászlóval jelölt-e a mező
     */
    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    /**
     * Visszaadja, hogy kérdőjellel jelölt-e a mező.
     *
     * @return kérdőjellel jelölt-e a mező
     */
    public boolean isMarked() {
        return marked;
    }

    /**
     * Beállítja, hogy kérdőjellel jelölt-e a mező.
     *
     * @param marked kérdőjellel jelölt-e a mező
     */
    public void setMarked(boolean marked) {
        this.marked = marked;
    }
}
