package minesweeper;

public class GameLogic {
    Grid grid;
    Graphic graphic;
    int width = 26;
    int height = 16;
    int bombs = 50;


    void initGame(){
        grid = new Grid(width,height);
        graphic = new Graphic(grid);
        grid.putBombs(bombs);
        grid.checkNeighbours();
    }

    void startGame(){
        grid.display();
        graphic.drawGame();
    }

    void clickTile(int x, int y){
        grid.reveal(x,y);
        grid.display();
        graphic.drawGame();
    }

    boolean isExploded(){
        return grid.isExploded();
    }

    void endGame(){
        grid.revealAll();
        grid.display();
        graphic.drawGame();
        System.out.println("végæ xdddddd");
    }
}
