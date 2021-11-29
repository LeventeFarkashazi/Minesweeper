package minesweeper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * The type Win frame.
 */
public class WinFrame extends JFrame {
    private final Difficulty difficulty;
    private final int time;
    private String winnerName;

    /**
     * Instantiates a new Win frame.
     */
    public WinFrame() {
        super("You Win!");
        setResizable(false);
        ImageIcon img = new ImageIcon(System.getProperty("user.dir") + "\\src\\minesweeper.png");
        setIconImage(img.getImage());

        difficulty = GameLogic.getInstance().difficulty;
        time = Timer.getInstance().getSeconds();

        initComponents();
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        Frame.GifPanel gifPanel = new Frame.GifPanel("\\src\\images\\youWin.gif", 640, 360);

        JPanel namePanel = new JPanel();
        namePanel.add(new JLabel("Name:"));
        JTextField winnerTextField = new JTextField(20);
        namePanel.add(winnerTextField);

        JButton addButton = new JButton("OK");
        addButton.addActionListener(ae -> {
            winnerName = winnerTextField.getText();
            HighScoresData.getInstance().addScore(winnerName, difficulty, time);
            dispose();
        });
        namePanel.add(addButton);
        namePanel.setBorder(new EmptyBorder(12, 12, 12, 12));

        mainPanel.add(gifPanel, BorderLayout.CENTER);
        mainPanel.add(namePanel, BorderLayout.SOUTH);
        mainPanel.setBorder(new EmptyBorder(12, 12, 0, 12));

        setContentPane(mainPanel);
        pack();
    }
}
