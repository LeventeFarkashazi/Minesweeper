package minesweeper;

public class GameLogic {
    Grid grid;
    Frame frame;
    Timer timer;
    HighScoresFrame highScoresFrame;
    Difficulty difficulty = Difficulty.INTERMEDIATE;

    int width = 16;
    int height = 16;
    int bombs = 30;

    void initGame() {
        highScoresFrame = new HighScoresFrame();
        timer = new Timer();
        grid = new Grid(width, height, bombs);
        grid.putBombs(bombs);
        frame = new Frame(grid, this, timer, grid.flagCounter);
        grid.checkNeighbours();
    }

    public void tileLeftClick(int x, int y) {
        if(timer.isKilled()){timer.start();}
        if (!grid.isTileFlagged(x, y)) {
            grid.reveal(x, y);
            if (grid.getRevealed() == width * height - bombs) {
                grid.revealAll();
                if (!isExploded()){
                    System.out.println("YOU WON");
                    timer.stop();
                    WinFrame winFrame = new WinFrame(highScoresFrame, difficulty, timer.getSeconds());
                    winFrame.setVisible(true);
                }
            }
        }
    }

    public void tileRightClick(int x, int y) {
        if(timer.isKilled()){timer.start();}
        grid.flag(x, y);
    }


    public boolean isExploded() {
        return grid.isExploded();
    }

    void endGame() {
        timer.stop();
        grid.revealAll();
        System.out.println("GAME OVER");
    }

    public void SetGameAttributes(int width, int height, int bombs) {
        this.width = width;
        this.height = height;
        this.bombs = bombs;
    }

    public void setDifficulty(Difficulty difficulty) {
        if (difficulty != null) {
            this.difficulty = difficulty;
            switch (difficulty) {
                case EASY -> SetGameAttributes(9, 9, 10);
                case INTERMEDIATE -> SetGameAttributes(16, 16, 40);
                case OVERKILL -> SetGameAttributes(30, 16, 99);
                case DEATH_WISH -> SetGameAttributes(60, 30, 350);
                case SZOFTTECH -> SetGameAttributes(16, 16, 16 * 16);
            }
        }
    }
}
