package minesweeper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameOverFrame extends JFrame {

    public GameOverFrame() {
        super("Game Over!");
        ImageIcon img = new ImageIcon(System.getProperty("user.dir") + "\\src\\minesweeper.png");
        setIconImage(img.getImage());

        initComponents();
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel okPanel = new JPanel();

        JButton addButton = new JButton("OK");
        addButton.addActionListener(ae -> dispose());
        okPanel.add(addButton);
        okPanel.setBorder(new EmptyBorder(12, 12, 12, 12));

        Frame.GifPanel gifPanel = new Frame.GifPanel("\\src\\images\\gameOver.gif", new Dimension(640, 360));
        gifPanel.setBorder(new EmptyBorder(12, 12, 0, 12));

        mainPanel.add(gifPanel, BorderLayout.CENTER);
        mainPanel.add(okPanel, BorderLayout.SOUTH);
        mainPanel.setBorder(new EmptyBorder(12, 12, 0, 12));
        setContentPane(mainPanel);

        pack();
    }
}
