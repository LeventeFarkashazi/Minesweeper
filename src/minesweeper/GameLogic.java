package minesweeper;

public class GameLogic {
    Grid grid;
    Graphic graphic;
    int width = 10;
    int height = 10;
    int bombs = 3;

    void initGame(){
        grid = new Grid(width,height);
        graphic = new Graphic(grid);
        grid.putBombs(bombs);
        grid.checkNeighbours();
        graphic.initDisplay();
    }

    void startGame(){
        grid.display();
    }

    void clickTile(int x, int y){
        grid.reveal(x,y);
        grid.display();
    }

    boolean isExploded(){
        return grid.isExploded();
    }

    void endGame(){
        grid.revealAll();
        grid.display();
        System.out.println("végæ xdddddd");
    }
}
