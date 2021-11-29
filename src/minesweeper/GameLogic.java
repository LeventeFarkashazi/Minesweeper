package minesweeper;

/**
 * A játék logikáját megvalósító osztály.
 * Csak egy példánya lehet, azaz singleton.
 */
public class GameLogic {
    private static GameLogic instance;
    Grid grid;
    Frame frame;
    Difficulty difficulty = Difficulty.INTERMEDIATE;
    private boolean gameStarted;

    private int width = 16;
    private int height = 16;
    private int bombs = 30;

    /**
     * Visszadja az osztály egyetlen létező példányát.
     * Ha az osztálynak még nem létezik példánya, létrehoz egyet.
     *
     * @return az osztály példánya
     */
    public static GameLogic getInstance() {
        if (instance == null) {
            instance = new GameLogic();
        }
        return instance;
    }

    /**
     * Inicializálja  a játékot
     */
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

    /**
     * Egy adott mezőre bal kattintást reprezentál. A mezőre kattintás következményeiért felelős.
     *
     * @param x a kattintott mező gridben elfoglalt helyének x koodinátája
     * @param y a kattintott mező gridben elfoglalt helyének y koodinátája
     */
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

    /**
     * Tile right click.
     * Egy adott mezőre jobb kattintást reprezentál. A mezőre kattintás következményeiért felelős.
     *
     * @param x a kattintott mező gridben elfoglalt helyének x koodinátája
     * @param y a kattintott mező gridben elfoglalt helyének y koodinátája
     */
    public void tileRightClick(int x, int y) {
        grid.flag(x, y);
    }

    /**
     * Visszaadja, hogy felrobbant e az aknamező valamelyik bombája.
     *
     * @return felrobbant-e az aknamező
     */
    public boolean isExploded() {
        return grid.isExploded();
    }

    /**
     *A játék elvesztése. Megállítja az időzítőt és felfedi az összes mezőt, majd megjeleníti a gameOver ablakot.
     */
    void gameOver() {
        Timer.getInstance().stop();
        grid.revealAll();
        GameOverFrame gameOverFrame = new GameOverFrame();
        gameOverFrame.setVisible(true);
    }

    /**
     * Beállítja a játék attribútumait.
     *
     * @param width  az aknamező szélessége
     * @param height az aknamező magassága
     * @param bombs  a bombák száma
     */
    public void SetGameAttributes(int width, int height, int bombs) {
        this.width = width;
        this.height = height;
        this.bombs = bombs;
    }

    /**
     * A játék nehézségét állítja be. Beállítja a megadott nehézséghez tartozó játék attribútumokat.
     *
     * @param difficulty a beállítani kívánt nehézség
     */
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

    /**
     * Visszatér az aknamező szélességeével.
     *
     * @return a mező szélessége
     */
    public int getWidth() {
        return width;
    }

    /**
     * Visszatér az aknamező magasságával.
     *
     * @return a mező magasságá
     */
    public int getHeight() {
        return height;
    }

    /**
     * Visszatér az aknamezőn lévő bombák számával.
     *
     * @return a bombák száma
     */
    public int getBombs() {
        return bombs;
    }
}