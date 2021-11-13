package minesweeper;

public class GameLogic {
    Grid grid;
    Graphic graphic;
    int width = 35;
    int height = 30;
    int bombs = 150;


    void initGame(){
        grid = new Grid(width,height);
        graphic = new Graphic(grid,this);
        grid.putBombs(bombs);
        grid.checkNeighbours();
    }

    void startGame(){
        graphic.drawGame();
    }

    boolean isExploded(){
        return grid.isExploded();
    }

    void endGame(){
        grid.revealAll();
        System.out.println("GAME OVER");
    }
}
