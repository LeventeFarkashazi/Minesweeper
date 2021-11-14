package minesweeper;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Graphic {
    Grid grid;
    GameLogic gameLogic;
    JFrame frame = new JFrame("Epic minesweeper");
    JPanel mainFrame = new JPanel();
    JPanel gamePanel = new JPanel();
    int height, width;
    int tileDim = 25;
    JMenu menu;
    JMenuItem diffEasy, diffMedium, diffHard, diffMenu, highScores, newGame;

    Graphic(Grid grid, GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        this.grid = grid;
        this.height = grid.getHeight();
        this.width = grid.getWidth();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePanel.setLayout(new GridLayout(height, width));
        gamePanel.setBorder(new EmptyBorder(tileDim / 2, tileDim / 2, tileDim / 2, tileDim / 2));
        initMenu();
        drawGame();
        frame.pack();
        frame.setMinimumSize(frame.getPreferredSize());
    }


    public void initMenu() {
        JMenuBar menuBar = new JMenuBar();

        highScores = new JMenuItem("High scores");

        menu = new JMenu("Game");

        diffMenu = new JMenu("Difficulty");
        diffEasy = new JMenuItem("Easy");
        diffMedium = new JMenuItem("Medium");
        diffHard = new JMenuItem("Hard");
        diffMenu.add(diffEasy);
        diffMenu.add(diffMedium);
        diffMenu.add(diffHard);
        menu.add(diffMenu);

        newGame = new JMenuItem("New game");
        menu.add(newGame);
        menu.add(highScores);

        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

    }

    public void drawGame() {
        gamePanel.removeAll();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid.GetTileNeighbours(x, y) == -1) {
                    JButton tmpButton = new JButton();
                    tmpButton.setPreferredSize(new Dimension(tileDim, tileDim));
                    int finalY = y;
                    int finalX = x;
                    tmpButton.addActionListener(e -> {
                        grid.reveal(finalX, finalY);
                        if (gameLogic.isExploded()) {
                            gameLogic.endGame();
                        }
                        drawGame();
                    });

                    gamePanel.add(tmpButton);
                } else {
                    JTextField tmpText = new JTextField(Integer.toString(grid.GetTileNeighbours(x, y)));
                    tmpText.setPreferredSize(new Dimension(tileDim, tileDim));
                    gamePanel.add(tmpText);
                }
            }
        }
        mainFrame.add(gamePanel);
        frame.setContentPane(mainFrame);
        frame.setVisible(true);
    }
}

