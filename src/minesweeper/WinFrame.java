package minesweeper;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WinFrame extends JFrame {
    HighScoresFrame highScoresFrame;
    Difficulty difficulty;
    int time;
    private String winnerName;

    private void initComponents() {
        this.setLayout(new BorderLayout());

        JPanel imagePanel = new JPanel();
        try {
            JLabel picLabel = new JLabel(new ImageIcon(ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\images\\winedited.png"))));
            imagePanel.add(picLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        imagePanel.setBorder(new EmptyBorder(12,12,0,12));

        JPanel namePanel = new JPanel();
        namePanel.add(new JLabel("Name:"));
        JTextField winnerTextField = new JTextField(20);
        namePanel.add(winnerTextField);

        JButton addButton = new JButton("OK");
        addButton.addActionListener(ae -> {
            winnerName = winnerTextField.getText();
            highScoresFrame.addWinner(winnerName,difficulty,time);
            dispose();
        });
        namePanel.add(addButton);

        this.add(imagePanel, BorderLayout.CENTER);
        this.add(namePanel, BorderLayout.SOUTH);
        pack();
    }

    public WinFrame(HighScoresFrame highScoresFrame,Difficulty difficulty, int time) {
        super("You Win!");

        this.difficulty = difficulty;
        this.highScoresFrame = highScoresFrame;
        this.time=time;

        //setMinimumSize(new Dimension(350, 400));
        initComponents();
    }
}
