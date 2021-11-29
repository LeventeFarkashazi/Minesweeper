package minesweeper;

import java.util.Random;

/**
 * Az aknamezőt reprezentáló osztály.
 * pozíció szerint tárolja az egyes mezőket és felelős a rajtuk elvégzett műveletekért és az állapotuk lekérdezéséért.
 * Csak egy példánya lehet, azaz singleton osztály.
 */
public class Grid {
    private static Grid instance;

    private final int width;
    private final int height;
    private final Tile[][] grid;
    private int revealed;
    private boolean exploded;

    /**
     * Konstruktor.
     * Példányosítj a grid-et és meghívja a mátrixot inicializáló függvényt.
     */
    private Grid() {
        width = GameLogic.getInstance().getWidth();
        height = GameLogic.getInstance().getHeight();
        grid = new Tile[width][height];
        exploded = false;
        revealed = 0;
        initMatrix();
    }

    /**
     * Visszadja a grid egyetlen létező példányát.
     * Ha az osztálynak még nem létezik példánya, létrehoz egyet.
     *
     * @return a grid példánya
     */
    public static Grid getInstance() {
        if (instance == null) {
            instance = new Grid();
        }
        return instance;
    }

    /**
     *Feltölti a grid mátrixot mezőkkel.
     */
    public void initMatrix() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = new Tile(false, false, 0);
            }
        }
    }

    /**
     * Ellenőrzi, hogy egy koordináta benne van e a mátrixban.
     *
     * @param x a lekérdezendő mező x koordinátája
     * @param y a lekérdezendő mező y koordinátája
     * @return benne van-e a koordináta a mátrixban
     */
    public boolean inGrid(int x, int y) {
        return x <= width - 1 && x >= 0 && y <= height - 1 && y >= 0;
    }


    /**
     * Egyenletesen random módon elhelyez bombs darab bombát a mezőn.
     * Ellenőrzi, hogy a random kiválasztott mezőn van e már bomba, ekkor újra kiválaszt egy random mezőt.
     *
     * @param bombs az elhelyezendő bombák száma
     */
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

    /**
     * Mezőnként kiszámolja és beállítja a szomszédos mezőkön található bombák számát.
     * ha a mező bomba, akkor a szomszédos bombák számát 9-re állítja (lehetetlen érték...).
     */
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

    /**
     * Visszaadja az x és y koordinátákkal megadott mező bomba szomszéedainak számát.
     * -1-ae ad vissza, ha a mező még nem lett felfedve.
     *
     * @param x a lekérdezendő mező x koodinátája
     * @param y a lekérdezendő mező y koodinátája
     * @return a bomba szomszédok száma
     */
    public int GetTileNeighbours(int x, int y) {
        if (grid[x][y].isVisible())
            return grid[x][y].getBombNeighbours();
        else
            return -1;
    }

    /**
     * Visszaadja, hogy az x és y koordinátákkal megadott mezőn található-e jelölő zászló.
     * @param x a lekérdezendő mező x koodinátája
     * @param y a lekérdezendő mező y koodinátája
     * @return van-e zászló az adott mezőn
     */
    public boolean isTileFlagged(int x, int y) {
        return !grid[x][y].isVisible() && grid[x][y].isFlagged();
    }

    /**
     * Visszaadja, hogy az x és y koordinátákkal megadott mezőn található-e jelölő kérdőjel.
     * @param x a lekérdezendő mező x koodinátája
     * @param y a lekérdezendő mező y koodinátája
     * @return van-e jelölő kérdőjel az adott mezőn
     */
    public boolean isTileMarked(int x, int y) {
        return !grid[x][y].isVisible() && grid[x][y].isMarked();
    }

    /**
     * Visszaadja, hogy az x és y koordinátákkal megadott mező bomba-e.
     * @param x a lekérdezendő mező x koodinátája
     * @param y a lekérdezendő mező y koodinátája
     * @return bomba-e az adott mező
     */
    public boolean isTileBomb(int x, int y) {
        return !grid[x][y].isVisible() && grid[x][y].isBomb();
    }

    /**
     * Leveszi a bombát az x és y koordinátákkal megadott mezőről.
     *
     * @param x a módosítandó mező x koodinátája
     * @param y a módosítandó mező y koodinátája
     */
    public void removeTileBomb(int x, int y) {
        grid[x][y].setBomb(false);
    }

    /**
     * Rekurzív függvény, ami feloldja az x és y koordinátákkal megadott mezőt.
     * Ha egy mezőnek 0 db szomszédja van, akkor a körülötte lévő mezőkre rekurzívan meghívódik a függvényt.
     * A zászlóval mefjelölt mezők nem oldódnak föl.
     *
     * @param x a feloldandó mező x koodinátája
     * @param y a feloldandó mező y koodinátája
     */
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

    /**
     * Zászlót helyez az x és y koordinátákkal megadott mezőre.
     * Ha a mezőn már van zászló akkor leveszi és helyére kérdőjelet rak.
     * Ha a mezőn kérdőjel van akkor leveszi azt.
     *
     * @param x the x
     * @param y the y
     */
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

    /**
     * Felfedi a gridben található összes mezőt.
     */
    public void revealAll() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y].setVisible(true);
            }
        }
    }

    /**
     * Alaphelyzetbe állítja a gridet (gyakorlatilag létrehoz egy új példányt).
     */
    public void resetGrid() {
        instance = new Grid();
    }

    /**
     * Visszatér a már felfedett mezők számával.
     *
     * @return a felfedett mezők száma
     */
    public int getRevealed() {
        return revealed;
    }

    /**
     * Visszaadja, hogy felrobban-e az aknamező.
     *
     * @return felrobban-e az aknamező
     */
    public boolean isExploded() {
        return exploded;
    }

}
