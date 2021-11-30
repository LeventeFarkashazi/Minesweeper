package minesweeper;

public class GameLogic {
    private static GameLogic instance;
    private Grid grid;
    private Difficulty difficulty = Difficulty.INTERMEDIATE;
    private boolean gameStarted;

    private int width = 16;
    private int height = 16;
    private int bombs = 30;
    private Frame frame;

    public static GameLogic getInstance() {
        if (instance == null) {
            instance = new GameLogic();
        }
        return instance;
    }

    void initGame() {
        gameStarted = false;
        Grid.getInstance().resetGrid();
        grid = Grid.getInstance();
        grid.putBombs(bombs);
        grid.checkNeighbours();
        Timer.getInstance().resetTimer();
        FlagCounter.getInstance().resetFlags();
        frame = new Frame();
    }

    public void tileLeftClick(int x, int y) {
        //On the first click, starts the timer (and the game)
        if (!gameStarted) {
            Timer.getInstance().start();

            //If the first clicked tile is a bomb, put the bomb to another tile (if there is an empty tile)
            while (grid.isTileBomb(x, y) && bombs < width * height) {
                grid.removeTileBomb(x, y);
                grid.putBombs(1);
            }
            grid.checkNeighbours();
            gameStarted = true;
        }

        if (!grid.isTileFlagged(x, y)) {
            grid.reveal(x, y);

            //Check if the game is ended
            if (grid.getRevealed() == width * height - bombs) {
                grid.revealAll();

                //If the minefield is not exploded, stop timer and make a win frame
                if (!isExploded()) {
                    Timer.getInstance().stop();
                    WinFrame winFrame = new WinFrame();
                    winFrame.setVisible(true);
                }
            }
        }
    }

    public void tileRightClick(int x, int y) {
        grid.flag(x, y);
    }

    public boolean isExploded() {
        return grid.isExploded();
    }

    void gameOver() {
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBombs() {
        return bombs;
    }

    public Grid getGrid() {
        return grid;
    }

    public Frame getFrame() {
        return frame;
    }

    public Difficulty getDifficulty() {
        return difficulty;
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

    public boolean isGameStarted() {
        return gameStarted;
    }
}