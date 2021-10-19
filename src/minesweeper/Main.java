package minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        GameLogic gl = new GameLogic();
        Graphic g = new Graphic();
        gl.initGame();
        gl.startGame();
        g.displayMenu();


        while (!gl.isExploded()){
            System.out.print("fuggoleges vizszintes:");
            int x = scan.nextInt();
            int y = scan.nextInt();
            gl.clickTile( x, y);
        }
        gl.endGame();
    }
}