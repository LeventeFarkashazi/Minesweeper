package minesweeper;

/**
 * The type Tile.
 */
public class Tile {
    private boolean bomb;
    private int bombNeighbours;
    private boolean visible;
    private boolean flagged;
    private boolean marked;

    /**
     * Instantiates a new Tile.
     *
     * @param isBomb         the is bomb
     * @param isVisible      the is visible
     * @param bombNeighbours the bomb neighbours
     */
    Tile(boolean isBomb, boolean isVisible, int bombNeighbours) {
        this.bomb = isBomb;
        this.visible = isVisible;
        this.bombNeighbours = bombNeighbours;
    }

    /**
     * Is visible boolean.
     *
     * @return the boolean
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Sets visible.
     *
     * @param visible the visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Gets bomb neighbours.
     *
     * @return the bomb neighbours
     */
    public int getBombNeighbours() {
        return bombNeighbours;
    }

    /**
     * Sets bomb neighbours.
     *
     * @param n the n
     */
    public void setBombNeighbours(int n) {
        this.bombNeighbours = n;
    }

    /**
     * Add bomb neighbour.
     *
     * @param n the n
     */
    public void AddBombNeighbour(int n) {
        this.bombNeighbours += n;
    }

    /**
     * Is bomb boolean.
     *
     * @return the boolean
     */
    public boolean isBomb() {
        return bomb;
    }

    /**
     * Sets bomb.
     *
     * @param bomb the bomb
     */
    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    /**
     * Is flagged boolean.
     *
     * @return the boolean
     */
    public boolean isFlagged() {
        return flagged;
    }

    /**
     * Sets flagged.
     *
     * @param flagged the flagged
     */
    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    /**
     * Is marked boolean.
     *
     * @return the boolean
     */
    public boolean isMarked() {
        return marked;
    }

    /**
     * Sets marked.
     *
     * @param marked the marked
     */
    public void setMarked(boolean marked) {
        this.marked = marked;
    }
}
