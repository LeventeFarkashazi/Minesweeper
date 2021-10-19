package minesweeper;

public class GameLogic {
    Grid g;
    int width = 85;
    int height = 85;
    int bombs = 0;
    int anyadseszeret = width * height - bombs;

    void initGame(){
        System.out.println("tyű:" + anyadseszeret);
        g = new Grid(width,height);
        g.putBombs(bombs);
        g.checkNeighbours();
    }

    void startGame(){
        g.display();
    }

    void clickTile(int x, int y){
        g.reveal(x,y);
        g.display();
    }

    boolean isExploded(){
        return g.isExploded();
    }

    void endGame(){
        g.revealAll();
        g.display();
        System.out.println("végæ xdddddd");
    }
}
