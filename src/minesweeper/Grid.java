package minesweeper;

import java.util.Random;

public class Grid {
    int width;
    int height;
    Tile[][] grid;
    boolean exploded;

    Grid(int width, int height){
        this.width = width;
        this.height = height;
        grid = new Tile[width][height];
        exploded = false;
        init();
    }

    public void init(){
        for(int x = 0; x < width; x++ )
        {
            for( int y = 0; y < height; y++ )
            {
                grid[x][y] = new Tile(x,y,false,false,0);
            }
        }
    }

    public boolean isExploded() {
        return exploded;
    }

    public void putBombs(int bombs){
        Random rn = new Random();
        int x;
        int y;
        if(bombs <= width *height){
            while (bombs!=0){
                x = Math.abs(rn.nextInt() % width);
                y = Math.abs(rn.nextInt() % height);
                if(!grid[x][y].isBomb()) {
                    grid[x][y].setBomb(true);
                    grid[x][y].setBombNeighbours(9);
                    bombs--;
                }
            }
        }
    }

    public boolean inGrid(int x, int y){
        return x <= width - 1 && x >= 0 && y <= height - 1 && y >= 0;
    }

    public void checkNeighbours(){
        for(int x = 0; x < width; x++ )
        {
            for( int y = 0; y < height; y++ )
            {
                if(!grid[x][y].isBomb()){
                    if(inGrid(x+1,y+1)&&grid[x+1][y+1].isBomb()){grid[x][y].reveal(1);}
                    if(inGrid(x+1,y)&&grid[x+1][y].isBomb()){grid[x][y].reveal(1);}
                    if(inGrid(x+1,y-1)&&grid[x+1][y-1].isBomb()){grid[x][y].reveal(1);}
                    if(inGrid(x,y+1)&&grid[x][y+1].isBomb()){grid[x][y].reveal(1);}
                    if(inGrid(x,y-1)&&grid[x][y-1].isBomb()){grid[x][y].reveal(1);}
                    if(inGrid(x-1,y+1)&&grid[x-1][y+1].isBomb()){grid[x][y].reveal(1);}
                    if(inGrid(x-1,y)&&grid[x-1][y].isBomb()){grid[x][y].reveal(1);}
                    if(inGrid(x-1,y-1)&&grid[x-1][y-1].isBomb()){grid[x][y].reveal(1);}
                }
            }
        }
    }

    public void display(){
        System.out.println("╔" + "═".repeat(height*2) + "╗");
        for(int x = 0; x< width; x++) {
            System.out.print("║");
            for (int y = 0; y < height; y++)
                if(grid[x][y].isVisible())
                    System.out.print(grid[x][y].getBombNeighbours() +" ");
                else
                    System.out.print("  ");
            System.out.print("║");
            System.out.println();
        }
        System.out.println("╚" + "═".repeat(height*2) + "╝");
    }

    public void reveal(int x, int y){
        if(grid[x][y].isBomb()){
            grid[x][y].setVisible(true);
            exploded = true;
        }else if(grid[x][y].getBombNeighbours()!=0){
            grid[x][y].setVisible(true);
        }else if(grid[x][y].getBombNeighbours()==0){
            grid[x][y].setVisible(true);
            if(inGrid(x+1,y+1) && !grid[x+1][y+1].isVisible()){reveal(x+1,y+1);}
            if(inGrid(x+1,y) && !grid[x+1][y].isVisible()){reveal(x+1,y);}
            if(inGrid(x+1,y-1) && !grid[x+1][y-1].isVisible()){reveal(x+1,y-1);}
            if(inGrid(x,y+1) && !grid[x][y+1].isVisible()){reveal(x,y+1);}
            if(inGrid(x,y-1) && !grid[x][y-1].isVisible()){reveal(x,y-1);}
            if(inGrid(x-1,y+1) && !grid[x-1][y+1].isVisible()){reveal(x-1,y+1);}
            if(inGrid(x-1,y) && !grid[x-1][y].isVisible()){reveal(x-1,y);}
            if(inGrid(x-1,y-1) && !grid[x-1][y-1].isVisible()){reveal(x-1,y-1);}
        }
    }

    public void revealAll(){
        for(int i = 0; i < width; i++ )
        {
            for( int j = 0; j < height; j++ )
            {
                grid[i][j].setVisible(true);
            }
        }
    }
}
