package minesweeper;

public class Tile {
    private boolean bomb;
    private int bombNeighbours;
    private boolean visible;
    private boolean flagged;
    private boolean marked;

    Tile(boolean isBomb, boolean isVisible, int bombNeighbours) {
        this.bomb = isBomb;
        this.visible = isVisible;
        this.bombNeighbours = bombNeighbours;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getBombNeighbours() {
        return bombNeighbours;
    }

    public void setBombNeighbours(int n) {
        this.bombNeighbours = n;
    }

    public void AddBombNeighbour(int n) {
        this.bombNeighbours += n;
    }

    public boolean isBomb() {
        return bomb;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }
}
