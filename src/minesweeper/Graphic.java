package minesweeper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphic {
    Grid grid;
    GameLogic gameLogic;
    JFrame frame = new JFrame("Epic minesweeper");
    JPanel mainFrame = new JPanel();
    JPanel menuPanel = new JPanel();
    JPanel gamePanel = new JPanel();
    int height,width;
    int tileDim = 25;


    Graphic(Grid grid, GameLogic gameLogic){
        this.gameLogic=gameLogic;
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
        for(int y = 0; y < height; y++ ){
            for( int x = 0; x < width; x++ ){
                int tmpx=x;
                int tmpy=y;
                if(grid.GetTileNeighbours(x,y)==-1){
                    JButton tmpButton = new JButton();
                    tmpButton.setPreferredSize(new Dimension(tileDim, tileDim));
                    tmpButton.addActionListener(e -> {
                        grid.reveal(tmpx, tmpy);
                        if(gameLogic.isExploded()){
                            gameLogic.endGame();
                        }
                        drawGame();
                    });

                    gamePanel.add(tmpButton);
                }else{
                    JTextField tmpText = new JTextField(Integer.toString(grid.GetTileNeighbours(x,y)));
                    tmpText.setPreferredSize(new Dimension(tileDim, tileDim));
                    gamePanel.add(tmpText);
                }
            }
        }
        mainFrame.add(gamePanel);
        frame.setContentPane(mainFrame);
        frame.setVisible(true);
    }
}

