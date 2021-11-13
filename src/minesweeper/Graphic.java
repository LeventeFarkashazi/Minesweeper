package minesweeper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Graphic {
    Grid grid;
    JFrame frame = new JFrame("Epic minesweeper");
    JPanel menuPanel = new JPanel();
    JPanel gamePanel = new JPanel();
    int height,width;
    int tileDim = 25;


    Graphic(Grid grid){
        this.grid=grid;
        this.height=grid.getHeight();
        this.width=grid.getWidth();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        gamePanel.setLayout(new GridLayout(height, width));
        gamePanel.setBorder(new EmptyBorder(tileDim, tileDim, tileDim, tileDim));
        drawGame();
        frame.pack();
    }

    public void drawGame(){
        gamePanel.removeAll();
        for(int y = 0; y < height; y++ ) {
            for( int x = 0; x < width; x++ ) {
                if(grid.GetTileNeighbours(x,y)==-1){
                    JButton tmpButton = new JButton();
                    tmpButton.setPreferredSize(new Dimension(tileDim, tileDim));
                    gamePanel.add(tmpButton);
                }else{
                    JTextField tmpText = new JTextField(Integer.toString(grid.GetTileNeighbours(x,y)));
                    tmpText.setPreferredSize(new Dimension(tileDim, tileDim));
                    gamePanel.add(tmpText);
                }
            }
        }
        frame.add(gamePanel);
        frame.setVisible(true);
    }
}

