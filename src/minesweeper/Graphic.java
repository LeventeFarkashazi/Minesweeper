package minesweeper;

import javax.swing.*;
import java.awt.*;

public class Graphic {
    JFrame f = new JFrame("Űber epikus minesweeper");
    JPanel p = new JPanel();
    JButton b = new JButton("Explode");
    JTextField t = new JTextField("Type here!");

    public void displayMenu() {
        p.add(b);
        f.add(p, BorderLayout.NORTH);
        f.pack();
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
