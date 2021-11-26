package minesweeper;

public class GameLogic {
    private static GameLogic instance;
    Grid grid;
    Frame frame;
    HighScoresFrame highScoresFrame;
    Difficulty difficulty = Difficulty.INTERMEDIATE;
    private boolean started;

    private int width = 16;
    private int height = 16;
    private int bombs = 30;

    private GameLogic() {
    }

    public static GameLogic getInstance() {
        if (instance == null) {
            instance = new GameLogic();
        }
        return instance;
    }

    void initGame() {
        started = false;
        highScoresFrame = new HighScoresFrame();
        Grid.getInstance().resetGrid();
        grid = Grid.getInstance();
        grid.putBombs(bombs);
        Timer.getInstance().resetTimer();
        FlagCounter.getInstance().resetFlags();
        frame = new Frame();
        grid.checkNeighbours();
    }

    public void tileLeftClick(int x, int y) {
        if (!started) {
            Timer.getInstance().start();

            if (bombs < width * height) {
                while (grid.isTileBomb(x, y)) {
                    grid.removeTileBomb(x, y);
                    grid.putBombs(1);
                }
                grid.checkNeighbours();
            }
            started = true;
        }

        if (!grid.isTileFlagged(x, y)) {
            grid.reveal(x, y);
            if (grid.getRevealed() == width * height - bombs) {
                grid.revealAll();
                if (!isExploded()) {
                    Timer.getInstance().stop();
                    WinFrame winFrame = new WinFrame();
                    winFrame.setVisible(true);
                }
            }
        }
    }

    public void tileRightClick(int x, int y) {
        if (Timer.getInstance().isKilled()) {
            Timer.getInstance().start();
        }
        grid.flag(x, y);
    }

    public boolean isExploded() {
        return grid.isExploded();
    }

    void endGame() {
        Timer.getInstance().stop();
        grid.revealAll();
        GameOverFrame gameOverFrame = new GameOverFrame();
        gameOverFrame.setVisible(true);
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
                case CRYBABY -> SetGameAttributes(9, 9, 5);
                case EASY -> SetGameAttributes(9, 9, 10);
                case INTERMEDIATE -> SetGameAttributes(16, 16, 40);
                case OVERKILL -> SetGameAttributes(30, 16, 99);
                case DEATH_WISH -> SetGameAttributes(60, 30, 300);
                case FEELING_LUCKY -> SetGameAttributes(40, 25, 40 * 25 - 2);
                case SZOFTTECH -> SetGameAttributes(16, 16, 16 * 16);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBombs() {
        return bombs;
    }
}
