package minesweeper;

public class GameLogic {
    Grid grid;
    Frame frame;

    int width = 16;
    int height = 16;
    int bombs = 30;

    void initGame() {
        grid = new Grid(width, height);
        frame = new Frame(grid, this);
        grid.putBombs(bombs);
        grid.checkNeighbours();
    }

    public void startGame() {
        frame.drawGame();
    }

    public void tileLeftClick(int x, int y) {
        if (!grid.isTileFlagged(x,y)) {
            grid.reveal(x, y);
            if (false) {
                grid.revealAll();
                System.out.println("YOU WON");
            }
        }
    }

    public void tileRightClick(int x, int y) {
        grid.flag(x, y);
    }


    public boolean isExploded() {
        return grid.isExploded();
    }

    void endGame() {
        grid.revealAll();
        System.out.println("GAME OVER");
    }

    public void SetGameAttributes(int width, int height, int bombs) {
        this.width = width;
        this.height = height;
        this.bombs = bombs;
    }
}
