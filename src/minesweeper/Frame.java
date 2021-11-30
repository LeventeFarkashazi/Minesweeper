package minesweeper;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private final GameLogic gameLogic;

    Frame() {
        gameLogic = GameLogic.getInstance();

        //set window properties and init menu
        ImageIcon img = new ImageIcon(System.getProperty("user.dir") + "\\src\\minesweeper.png");
        setIconImage(img.getImage());
        setTitle("Minesweeper");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initMenu();

        //content pane (main panel)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        //game panel
        GamePanel gamePanel = new GamePanel();
        gamePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(12, 12, 12, 12, Color.LIGHT_GRAY), BorderFactory.createLoweredBevelBorder()));

        //info panel (for the 2 counters)
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.add(Timer.getInstance(), BorderLayout.WEST);
        infoPanel.add(FlagCounter.getInstance(), BorderLayout.EAST);
        infoPanel.setBackground(Color.LIGHT_GRAY);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(12, 12, 0, 12, Color.LIGHT_GRAY), BorderFactory.createLoweredBevelBorder()));

        //add the panels
        mainPanel.add(infoPanel, BorderLayout.NORTH);
        mainPanel.add(gamePanel, BorderLayout.SOUTH);

        //size and position
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Game");
        menuBar.add(menu);
        setJMenuBar(menuBar);

        //Difficulty menu
        JMenuItem diffMenu = new JMenu("Difficulty");

        //Difficulty menu items and their listeners
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
    }

    static class GifPanel extends JPanel {

        Image image;

        public GifPanel(String path, int width, int height) {
            image = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + path);
            setPreferredSize(new Dimension(width, height));
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