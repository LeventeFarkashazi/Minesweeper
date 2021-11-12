package minesweeper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Graphic {
    Grid grid;
    JFrame frame = new JFrame("Epic minesweeper");
    JPanel menu_panel = new JPanel();
    JPanel game_panel = new JPanel();

    Graphic(Grid grid){
        this.grid=grid;
    }

    public void initDisplay() {
        game_panel.setLayout(new GridLayout(grid.getHeight(), grid.getWidth()));
        frame.add(menu_panel, BorderLayout.NORTH);
        frame.add(game_panel, BorderLayout.CENTER);
        //game_panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        Dimension dim = new Dimension(20,20);
        for(int x = 0; x < grid.getHeight(); x++ ) {
            for( int y = 0; y < grid.getWidth(); y++ ) {
                JButton tmp = new JButton();
                tmp.setPreferredSize(new Dimension(dim));

                game_panel.add(tmp);
            }
        }

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
