package minesweeper;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Frame extends JFrame {
    private final Grid grid;
    private final GameLogic gameLogic;
    private final JPanel mainFrame = new JPanel();
    private final JPanel gamePanel = new JPanel();
    private final JPanel infoPanel = new JPanel();
    private final int height;
    private final int width;
    private final int tileDim = 25;
    private BufferedImage[] images;

    Frame() {
        grid = Grid.getInstance();
        gameLogic = GameLogic.getInstance();
        height = GameLogic.getInstance().getHeight();
        width = GameLogic.getInstance().getWidth();

        setTitle("Epic minesweeper");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());

        gamePanel.setLayout(new GridLayout(height, width));
        gamePanel.setBorder(new EmptyBorder(tileDim / 2, tileDim / 2, tileDim / 2, tileDim / 2));

        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBorder(new EmptyBorder(tileDim / 2, tileDim / 2, 0, tileDim / 2));

        initMenu();
        readPictures();
        drawGame();
        pack();
        setMinimumSize(getPreferredSize());
        setLocationRelativeTo(null);
    }

    public void readPictures() {
        images = new BufferedImage[13];
        for (int i = 0; i < 13; i++) {
            try {
                images[i] = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\images\\" + i + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void initMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Game");
        //Difficulty menu
        JMenuItem diffMenu = new JMenu("Difficulty");
        //Difficulty menu items
        for (Difficulty tmpDiff : Difficulty.values()) {
            JMenuItem diffMenuItem = new JMenuItem(tmpDiff.toString());
            diffMenuItem.addActionListener(e -> {
                dispose();
                gameLogic.setDifficulty(tmpDiff);
                gameLogic.initGame();
            });
            diffMenu.add(diffMenuItem);
        }

        menu.add(diffMenu);

        //New Game
        JMenuItem newGame = new JMenuItem("New game");
        newGame.addActionListener(e -> {
            dispose();
            gameLogic.initGame();
        });
        menu.add(newGame);

        //High Scores
        JMenuItem highScores = new JMenuItem("High Scores");
        highScores.addActionListener(e -> {
            HighScoresFrame highScoresFrame = new HighScoresFrame();
            highScoresFrame.setVisible(true);
        });
        menu.add(highScores);

        menuBar.add(menu);
        setJMenuBar(menuBar);

    }

    public void drawGame() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid.GetTileNeighbours(x, y) == -1) {
                    Icon tmpIcon;
                    if(grid.isTileFlagged(x, y))
                        tmpIcon = new ImageIcon(images[11]);
                    else if(grid.isTileMarked(x, y))
                        tmpIcon = new ImageIcon(images[12]);
                    else
                        tmpIcon = new ImageIcon(images[10]);
                    JButton tmpButton = new JButton(tmpIcon);
                    tmpButton.setPreferredSize(new Dimension(tileDim, tileDim));
                    int finalY = y;
                    int finalX = x;

                    tmpButton.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            if (e.getButton() == 1) {
                                gameLogic.tileLeftClick(finalX, finalY);
                                if (gameLogic.isExploded()) {
                                    gameLogic.endGame();
                                }
                                gamePanel.removeAll();
                                drawGame();
                            }else if (e.getButton() == 3) {
                                gameLogic.tileRightClick(finalX, finalY);
                                gamePanel.removeAll();
                                drawGame();
                            }
                        }
                    });

                    gamePanel.add(tmpButton);
                } else {
                    JLabel picLabel = new JLabel(new ImageIcon(images[grid.GetTileNeighbours(x, y)]));
                    picLabel.setPreferredSize(new Dimension(tileDim, tileDim));
                    gamePanel.add(picLabel);
                }
            }
        }

        infoPanel.add(Timer.getInstance(),BorderLayout.WEST);
        infoPanel.add(FlagCounter.getInstance(),BorderLayout.EAST);

        mainFrame.add(infoPanel, BorderLayout.NORTH);
        mainFrame.add(gamePanel,BorderLayout.CENTER);

        setContentPane(mainFrame);
        setVisible(true);
    }
}

