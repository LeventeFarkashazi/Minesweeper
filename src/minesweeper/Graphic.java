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
    BufferedImage[] images;

    Graphic(Grid grid, GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        this.grid = grid;
        this.height = grid.getHeight();
        this.width = grid.getWidth();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePanel.setLayout(new GridLayout(height, width));
        gamePanel.setBorder(new EmptyBorder(tileDim / 2, tileDim / 2, tileDim / 2, tileDim / 2));
        initMenu();
        readPictures();
        drawGame();
        frame.pack();
        frame.setMinimumSize(frame.getPreferredSize());
    }

    public void readPictures() {
        images = new BufferedImage[13];
        for (int i = 0; i < 13; i++) {
            try {
                images[i] = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\smallimages\\"+i+".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
                    Icon icon = new ImageIcon(images[10]);
                    JButton tmpButton = new JButton(icon);
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
                    JLabel picLabel = new JLabel(new ImageIcon(images[grid.GetTileNeighbours(x,y)]));
                    picLabel.setPreferredSize(new Dimension(tileDim, tileDim));
                    gamePanel.add(picLabel);
                }
            }
        }
        mainFrame.add(gamePanel);
        frame.setContentPane(mainFrame);
        frame.setVisible(true);
    }
}

