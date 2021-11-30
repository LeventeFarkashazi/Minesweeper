package minesweeper;

import java.util.Random;

public class Grid {
    private static Grid instance;

    private final int width;
    private final int height;
    private final Tile[][] grid;
    private int revealed;
    private boolean exploded;

    private Grid() {
        width = GameLogic.getInstance().getWidth();
        height = GameLogic.getInstance().getHeight();
        grid = new Tile[width][height];
        exploded = false;
        revealed = 0;
        initMatrix();
    }

    public static Grid getInstance() {
        if (instance == null) {
            instance = new Grid();
        }
        return instance;
    }

    public void initMatrix() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = new Tile(false, false, 0);
            }
        }
    }

    public boolean inGrid(int x, int y) {
        return x <= width - 1 && x >= 0 && y <= height - 1 && y >= 0;
    }


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

    public void checkNeighbours() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y].setBombNeighbours(0);
                if (!grid[x][y].isBomb()) {
                    //check the neighbours in every direction
                    if (inGrid(x + 1, y + 1) && grid[x + 1][y + 1].isBomb()) {
                        grid[x][y].AddBombNeighbour();
                    }
                    if (inGrid(x + 1, y) && grid[x + 1][y].isBomb()) {
                        grid[x][y].AddBombNeighbour();
                    }
                    if (inGrid(x + 1, y - 1) && grid[x + 1][y - 1].isBomb()) {
                        grid[x][y].AddBombNeighbour();
                    }
                    if (inGrid(x, y + 1) && grid[x][y + 1].isBomb()) {
                        grid[x][y].AddBombNeighbour();
                    }
                    if (inGrid(x, y - 1) && grid[x][y - 1].isBomb()) {
                        grid[x][y].AddBombNeighbour();
                    }
                    if (inGrid(x - 1, y + 1) && grid[x - 1][y + 1].isBomb()) {
                        grid[x][y].AddBombNeighbour();
                    }
                    if (inGrid(x - 1, y) && grid[x - 1][y].isBomb()) {
                        grid[x][y].AddBombNeighbour();
                    }
                    if (inGrid(x - 1, y - 1) && grid[x - 1][y - 1].isBomb()) {
                        grid[x][y].AddBombNeighbour();
                    }
                } else {
                    grid[x][y].setBombNeighbours(9);
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

    public boolean isTileFlagged(int x, int y) {
        return !grid[x][y].isVisible() && grid[x][y].isFlagged();
    }

    public boolean isTileMarked(int x, int y) {
        return !grid[x][y].isVisible() && grid[x][y].isMarked();
    }

    public boolean isTileBomb(int x, int y) {
        return !grid[x][y].isVisible() && grid[x][y].isBomb();
    }

    public void removeTileBomb(int x, int y) {
        grid[x][y].setBomb(false);
    }

    public void reveal(int x, int y) {
        //if the revealed tile is bomb
        if (grid[x][y].isBomb()) {
            grid[x][y].setVisible(true);
            exploded = true;
        }
        //else if the revealed tile has more than 0 neighbours and not flagged
        else if (grid[x][y].getBombNeighbours() != 0 && !grid[x][y].isFlagged()) {
            grid[x][y].setVisible(true);
            revealed++;
        }
        //if the revealed tile has 0 neighbours and not flagged
        else if (grid[x][y].getBombNeighbours() == 0 && !grid[x][y].isFlagged()) {
            grid[x][y].setVisible(true);
            revealed++;
            //recursive call if the neighbour is in the grid, not visible and not flagged
            if (inGrid(x + 1, y + 1) && !grid[x + 1][y + 1].isVisible() && !grid[x][y].isFlagged()) {
                reveal(x + 1, y + 1);
            }
            if (inGrid(x + 1, y) && !grid[x + 1][y].isVisible() && !grid[x][y].isFlagged()) {
                reveal(x + 1, y);
            }
            if (inGrid(x + 1, y - 1) && !grid[x + 1][y - 1].isVisible() && !grid[x][y].isFlagged()) {
                reveal(x + 1, y - 1);
            }
            if (inGrid(x, y + 1) && !grid[x][y + 1].isVisible() && !grid[x][y].isFlagged()) {
                reveal(x, y + 1);
            }
            if (inGrid(x, y - 1) && !grid[x][y - 1].isVisible() && !grid[x][y].isFlagged()) {
                reveal(x, y - 1);
            }
            if (inGrid(x - 1, y + 1) && !grid[x - 1][y + 1].isVisible() && !grid[x][y].isFlagged()) {
                reveal(x - 1, y + 1);
            }
            if (inGrid(x - 1, y) && !grid[x - 1][y].isVisible() && !grid[x][y].isFlagged()) {
                reveal(x - 1, y);
            }
            if (inGrid(x - 1, y - 1) && !grid[x - 1][y - 1].isVisible() && !grid[x][y].isFlagged()) {
                reveal(x - 1, y - 1);
            }
        }
    }

    public void flag(int x, int y) {
        //flagged->marked
        if (grid[x][y].isFlagged()) {
            grid[x][y].setFlagged(false);
            FlagCounter.getInstance().increment();
            grid[x][y].setMarked(true);
        }
        //marked->empty
        else if (grid[x][y].isMarked()) {
            grid[x][y].setMarked(false);
        }
        //empty->flagged
        else if (FlagCounter.getInstance().getRemainingFlags() > 0) {
            grid[x][y].setFlagged(true);
            FlagCounter.getInstance().decrement();
        }
    }

    public void revealAll() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y].setVisible(true);
            }
        }
    }

    public void resetGrid() {
        instance = new Grid();
    }

    public int getRevealed() {
        return revealed;
    }

    public boolean isExploded() {
        return exploded;
    }

}
