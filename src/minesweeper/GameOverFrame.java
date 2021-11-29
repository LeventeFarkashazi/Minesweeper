package minesweeper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * A játék elvesztésekor megjelenő ablak.
 */
public class GameOverFrame extends JFrame {

    /**
     * Konstruktor.
     * Példányosítj az ablakot.
     */
    public GameOverFrame() {
        //set window properties
        super("Game Over!");
        setResizable(false);
        ImageIcon img = new ImageIcon(System.getProperty("user.dir") + "\\src\\minesweeper.png");
        setIconImage(img.getImage());
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);

        initComponents();
    }

    private void initComponents() {
        //content pane
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(12, 12, 0, 12));
        setContentPane(mainPanel);

        //close panel
        JPanel closePanel = new JPanel();
        JButton closeButton = new JButton("RIP");
        closeButton.addActionListener(ae -> dispose());
        closePanel.add(closeButton);
        closePanel.setBorder(new EmptyBorder(12, 12, 12, 12));

        //GIF panel
        Frame.GifPanel gifPanel = new Frame.GifPanel("\\src\\images\\gameOver.gif", 640, 360);
        gifPanel.setBorder(new EmptyBorder(12, 12, 0, 12));

        //add the panels
        mainPanel.add(gifPanel, BorderLayout.CENTER);
        mainPanel.add(closePanel, BorderLayout.SOUTH);

        pack();
    }
}
