package com.mario;
import com.formdev.flatlaf.FlatDarculaLaf;

import java.awt.EventQueue;

import javax.swing.UIManager;
// import javax.swing.UIManager;
public class Main {
    public static void main(String[] args) throws Exception {
        //set dark theme
        FlatDarculaLaf.setup();
        UIManager.setLookAndFeel(new FlatDarculaLaf());

        //set background size 
        int BOARD_WIDTH = 1120,BOARD_HEIGHT=630;
//        Background background = new Background(BOARD_WIDTH,BOARD_HEIGHT);//create background object
//        
//        background.show();
        
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Background2 frame = new Background2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
        // Mario flayyBird = new Mario();
        // frame.add(Mario);
        // frame.pack();//size doesn't include the label row
        // Mario.requestFocus();
        // frame.setVisible(true);
    }
}
