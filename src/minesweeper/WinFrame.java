package minesweeper;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WinFrame extends JFrame {
    private final Difficulty difficulty;
    private final int time;
    private String winnerName;

    public WinFrame() {
        super("You Win!");
        difficulty = GameLogic.getInstance().difficulty;
        time = Timer.getInstance().getSeconds();

        initComponents();
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());

        JPanel imagePanel = new JPanel();
        try {
            JLabel picLabel = new JLabel(new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\images\\win.png"))));
            imagePanel.add(picLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        imagePanel.setBorder(new EmptyBorder(12, 12, 0, 12));

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

        this.add(imagePanel, BorderLayout.CENTER);
        this.add(namePanel, BorderLayout.SOUTH);
        pack();
    }
}
