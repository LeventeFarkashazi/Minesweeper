package minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameLogic game = new GameLogic();
        game.initGame();
        game.startGame();
    }
}