package minesweeper;

public class Tile {
    private boolean bomb;
    private int bombNeighbours;
    private boolean visible;
    private boolean flagged;
    private boolean marked;

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    Tile(boolean isBomb, boolean isVisible, int bombNeighbours) {
        this.bomb = isBomb;
        this.visible = isVisible;
        this.bombNeighbours = bombNeighbours;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setBombNeighbours(int n) {
        this.bombNeighbours = n;
    }

    public int getBombNeighbours() {
        return bombNeighbours;
    }

    public void AddBombNeighbour(int n) {
        this.bombNeighbours += n;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public boolean isBomb() {
        return bomb;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public boolean isMarked() {
        return marked;
    }
}
