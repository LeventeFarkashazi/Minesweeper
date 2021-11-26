package minesweeper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Frame extends JFrame {
    private final GameLogic gameLogic;

    Frame() {
        gameLogic = GameLogic.getInstance();

        ImageIcon img = new ImageIcon(System.getProperty("user.dir") + "\\src\\minesweeper.png");
        setIconImage(img.getImage());
        setTitle("Minesweeper");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        GamePanel gamePanel = new GamePanel();

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());

        infoPanel.setBorder(new EmptyBorder(GameLogic.getInstance().getTileDim() / 2, GameLogic.getInstance().getTileDim() / 2, 0, GameLogic.getInstance().getTileDim() / 2));

        infoPanel.add(Timer.getInstance(), BorderLayout.WEST);
        infoPanel.add(FlagCounter.getInstance(), BorderLayout.EAST);

        mainPanel.add(infoPanel, BorderLayout.NORTH);
        mainPanel.add(gamePanel, BorderLayout.CENTER);

        setContentPane(mainPanel);

        initMenu();
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

    static class GifPanel extends JPanel {

        Image image;

        public GifPanel(String path, Dimension dim) {
            image = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + path);
            setPreferredSize(dim);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, this);
            }
        }
    }
}