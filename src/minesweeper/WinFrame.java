package minesweeper;

import javax.swing.*;
import java.awt.*;

public class WinFrame extends JFrame {
    HighScoresFrame highScoresFrame;
    Difficulty difficulty;
    int time;
    private String winnerName;

    private void initComponents() {
        this.setLayout(new BorderLayout());
        //table.setFillsViewportHeight(true);

        JPanel addPanel = new JPanel();
        addPanel.add(new JLabel("Name:"));
        JTextField winnerTextField = new JTextField(20);
        addPanel.add(winnerTextField);

        JButton addButton = new JButton("OK");
        addButton.addActionListener(ae -> {
            winnerName = winnerTextField.getText();
            highScoresFrame.addWinner(winnerName,difficulty,time);
            dispose();
        });
        addPanel.add(addButton);

        this.add(addPanel, BorderLayout.SOUTH);
    }

    public WinFrame(HighScoresFrame highScoresFrame,Difficulty difficulty, int time) {
        super("You Won!");

        this.difficulty = difficulty;
        this.highScoresFrame = highScoresFrame;
        this.time=time;

        setMinimumSize(new Dimension(350, 400));
        initComponents();
    }
}
