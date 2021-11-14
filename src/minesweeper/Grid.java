package minesweeper;

import java.util.Random;

public class Grid {
    private int width;
    private int height;
    private Tile[][] grid;
    private boolean exploded;

    Grid(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new Tile[width][height];
        exploded = false;
        init();
    }

    //init the matrix:
    public void init() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = new Tile(false, false, 0);
            }
        }
    }

    //check if a tile is in the grid:
    public boolean inGrid(int x, int y) {
        return x <= width - 1 && x >= 0 && y <= height - 1 && y >= 0;
    }


    //chose bombs radnomly:
    public void putBombs(int bombs) {
        Random rn = new Random();
        int x;
        int y;
        if (bombs <= width * height) {
            while (bombs != 0) {
                x = Math.abs(rn.nextInt() % width);
                y = Math.abs(rn.nextInt() % height);
                if (!grid[x][y].isBomb()) {
                    grid[x][y].setBomb(true);
                    grid[x][y].setBombNeighbours(9);
                    bombs--;
                }
            }
        }
    }

    //calculate tile's number:
    public void checkNeighbours() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (!grid[x][y].isBomb()) {
                    if(inGrid(x+1,y+1)&&grid[x+1][y+1].isBomb()){grid[x][y].reveal(1);}
                    if(inGrid(x+1,y)&&grid[x+1][y].isBomb()){grid[x][y].reveal(1);}
                    if(inGrid(x+1,y-1)&&grid[x+1][y-1].isBomb()){grid[x][y].reveal(1);}
                    if(inGrid(x,y+1)&&grid[x][y+1].isBomb()){grid[x][y].reveal(1);}
                    if(inGrid(x,y-1)&&grid[x][y-1].isBomb()){grid[x][y].reveal(1);}
                    if(inGrid(x-1,y+1)&&grid[x-1][y+1].isBomb()){grid[x][y].reveal(1);}
                    if(inGrid(x-1,y)&&grid[x-1][y].isBomb()){grid[x][y].reveal(1);}
                    if(inGrid(x-1,y-1)&&grid[x-1][y-1].isBomb()){grid[x][y].reveal(1);}
                }
            }
        }
    }

    public int GetTileNeighbours(int x, int y) {
        if (grid[x][y].isVisible())
            return grid[x][y].getBombNeighbours();
        else
            return -1;
    }

    //reveal tiles recursively:
    public void reveal(int x, int y) {
        if (grid[x][y].isBomb()) {
            grid[x][y].setVisible(true);
            exploded = true;
        } else if (grid[x][y].getBombNeighbours() != 0) {
            grid[x][y].setVisible(true);
        } else if (grid[x][y].getBombNeighbours() == 0) {
            grid[x][y].setVisible(true);
            if(inGrid(x+1,y+1) && !grid[x+1][y+1].isVisible()){reveal(x+1,y+1);}
            if(inGrid(x+1,y) && !grid[x+1][y].isVisible()){reveal(x+1,y);}
            if(inGrid(x+1,y-1) && !grid[x+1][y-1].isVisible()){reveal(x+1,y-1);}
            if(inGrid(x,y+1) && !grid[x][y+1].isVisible()){reveal(x,y+1);}
            if(inGrid(x,y-1) && !grid[x][y-1].isVisible()){reveal(x,y-1);}
            if(inGrid(x-1,y+1) && !grid[x-1][y+1].isVisible()){reveal(x-1,y+1);}
            if(inGrid(x-1,y) && !grid[x-1][y].isVisible()){reveal(x-1,y);}
            if(inGrid(x-1,y-1) && !grid[x-1][y-1].isVisible()){reveal(x-1,y-1);}
        }
    }

    //reveal all tiles (end of game):
    public void revealAll() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y].setVisible(true);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Tile[][] getGrid() {
        return grid;
    }

    public void setGrid(Tile[][] grid) {
        this.grid = grid;
    }

    public boolean isExploded() {
        return exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }
}
