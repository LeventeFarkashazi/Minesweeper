package minesweeper;

public class Tile {
    int x;
    int y;
    private boolean bomb;
    private int bombNeighbours;
    private boolean visible;

    Tile(){
        this.x = -1;
        this.y = -1;
        this.bomb = false;
        this.visible = false;
        this.bombNeighbours = 0;
    }

    Tile(int x, int y, boolean isBomb, boolean isVisible, int bombNeighbours){
        this.x = x;
        this.y = y;
        this.bomb = isBomb;
        this.visible = isVisible;
        this.bombNeighbours = bombNeighbours;
    }

    public void setVisible(boolean visible) {this.visible = visible;}
    public boolean isVisible() {return visible;}

    public void setBombNeighbours(int n) {this.bombNeighbours = n;}
    public int getBombNeighbours() {return bombNeighbours;}
    public void reveal(int n) {this.bombNeighbours += n;}


    public void setBomb(boolean bomb) {this.bomb = bomb;}
    public boolean isBomb() {return bomb;}
}
