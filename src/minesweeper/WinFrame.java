package minesweeper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * A játék megnyerésekor megjelenő ablak.
 */
public class WinFrame extends JFrame {
    private final Difficulty difficulty;
    private final int time;
    private String winnerName;

    /**
     * Konstruktor.
     * Példányosítj az ablakot.
     * Beállítja az ablak attribútumait és meghívja a komponenseket inicializáló függvényt.
     */
    public WinFrame() {
        //set window properties
        super("You Win!");
        setResizable(false);
        ImageIcon img = new ImageIcon(System.getProperty("user.dir") + "\\src\\minesweeper.png");
        setIconImage(img.getImage());

        //set arguments
        difficulty = GameLogic.getInstance().getDifficulty();
        time = Timer.getInstance().getSeconds();

        //init components
        initComponents();
        setAlwaysOnTop(true);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Inicializálja a megjelenítendő paneleket.
     */
    private void initComponents() {
        //content pane
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(12, 12, 0, 12));
        setContentPane(mainPanel);

        //GIF panel
        Frame.GifPanel gifPanel = new Frame.GifPanel("\\src\\images\\youWin.gif", 640, 360);

        //winner name panel
        JPanel namePanel = new JPanel();
        namePanel.add(new JLabel("Name:"));
        JTextField winnerTextField = new JTextField(20);
        namePanel.add(winnerTextField);

        //add score button
        JButton addButton = new JButton("OK");
        addButton.addActionListener(ae -> {
            winnerName = winnerTextField.getText();
            HighScoresData.getInstance().addScore(winnerName, difficulty, time);
            dispose();
        });
        namePanel.add(addButton);
        namePanel.setBorder(new EmptyBorder(12, 12, 12, 12));

        //add panels
        mainPanel.add(gifPanel, BorderLayout.CENTER);
        mainPanel.add(namePanel, BorderLayout.SOUTH);
    }
}