package minesweeper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Frame extends JFrame {
    private final Grid grid;
    private final GameLogic gameLogic;
    private final JPanel mainPanel, gamePanel, infoPanel;
    private final int height, width;
    private final int tileDim = 25;
    private final BufferedImage[] pictures;

    Frame() {
        grid = Grid.getInstance();
        gameLogic = GameLogic.getInstance();
        height = GameLogic.getInstance().getHeight();
        width = GameLogic.getInstance().getWidth();
        pictures = ImageReader.getInstance().getImages();

        ImageIcon img = new ImageIcon(System.getProperty("user.dir") + "\\src\\minesweeper.png");
        setIconImage(img.getImage());
        setTitle("Minesweeper");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(height, width));
        gamePanel.setBorder(new EmptyBorder(tileDim / 2, tileDim / 2, tileDim / 2, tileDim / 2));

        infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBorder(new EmptyBorder(tileDim / 2, tileDim / 2, 0, tileDim / 2));


        infoPanel.add(Timer.getInstance(), BorderLayout.WEST);
        infoPanel.add(FlagCounter.getInstance(), BorderLayout.EAST);

        mainPanel.add(infoPanel, BorderLayout.NORTH);
        mainPanel.add(gamePanel, BorderLayout.CENTER);

        setContentPane(mainPanel);

        initMenu();
        drawGame();
        pack();
        setMinimumSize(getPreferredSize());
        setLocationRelativeTo(null);
        setVisible(true);
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
                    if (grid.isTileFlagged(x, y))
                        tmpIcon = new ImageIcon(pictures[11]);
                    else if (grid.isTileMarked(x, y))
                        tmpIcon = new ImageIcon(pictures[12]);
                    else
                        tmpIcon = new ImageIcon(pictures[10]);
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
                            } else if (e.getButton() == 3) {
                                gameLogic.tileRightClick(finalX, finalY);
                                gamePanel.removeAll();
                                drawGame();
                            }
                        }
                    });

                    gamePanel.add(tmpButton);
                } else {
                    JLabel picLabel = new JLabel(new ImageIcon(pictures[grid.GetTileNeighbours(x, y)]));
                    picLabel.setPreferredSize(new Dimension(tileDim, tileDim));
                    gamePanel.add(picLabel);
                }
            }
        }
        gamePanel.updateUI();
    }
}