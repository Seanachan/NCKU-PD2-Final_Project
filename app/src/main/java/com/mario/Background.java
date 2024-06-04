package com.mario;
import javax.swing.*;
import java.awt.*;
//implement stock incline-like background

public class Background{
    JFrame frame=null;
    int BOARD_WIDTH,BOARD_HEIGHT;

    JPanel kLines=null;

    public Background(int BOARD_WIDTH,int BOARD_HEIGHT){
        this.BOARD_HEIGHT=BOARD_HEIGHT;
        this.BOARD_WIDTH=BOARD_WIDTH;
        frame = new JFrame("Stock Mario");

        
         // adding buttons to the frame       
        
        Grids grids = new Grids(30, 30, 4, 5);
        frame.add(grids);

        frame.pack();
        // setting grid layout of 3 rows and 3 columns    
        frame.setLayout(new GridLayout(3,1));    
        frame.setSize(BOARD_WIDTH,BOARD_HEIGHT);    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.black);

        

    }

    public void show(){
        frame.setVisible(true);    
    }
}
class Grids extends JPanel{
    int width, height; int rows,cols;
    Grids(int w,int h,int r,int c){
        setSize(width = w, height = h);
        rows = r;
        cols = c;
    }
    public void paint(Graphics g) {
        int i;
        width = getSize().width;
        height = getSize().height;
    
        // draw the rows
        int rowHt = height / (rows);
        for (i = 0; i < rows; i++)
          g.drawLine(0, i * rowHt, width, i * rowHt);
    
        // draw the columns
        int rowWid = width / (cols);
        // for (i = 0; i < cols; i++)
        //   g.drawLine(i * rowWid, 0, i * rowWid, height);
    }
}


