package minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        GameLogic game = new GameLogic();
        game.initGame();
        game.startGame();

        while (!game.isExploded()){
            System.out.print("fuggőleges vízszintes:");
            int x = scan.nextInt();
            int y = scan.nextInt();
            game.clickTile( x, y);
        }
        game.endGame();
    }
}