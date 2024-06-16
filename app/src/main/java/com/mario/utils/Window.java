package com.mario.utils;

import javax.swing.*;

import com.mario.Game;

import java.awt.*;

public class Window {
    public JFrame frame;
    public static int score = 0;
    public static boolean isComplete = false;
    public static boolean shouldShowScore=false;
    public Window(int width, int height, String title, com.tngo.mario.Game game){
        game.setPreferredSize(new Dimension(width, height));
        game.setMinimumSize(new Dimension(width, height));
        game.setMaximumSize(new Dimension(width, height));

        frame = new JFrame(title);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
        System.out.println("here");
        while(true){
            if(shouldShowScore){
                showScore();
                System.out.println("yes");
                break;
            }
        }
        // @Override
        //     public void windowClosing(WindowEvent e) {
        //         game.stop();
        //         System.exit(0);
        //     }
        // });
        
        // frame.setResizable(false);
        // frame.setLocationRelativeTo(null);
        // frame.add(game);
        // frame.setVisible(true);
        // game.start(
    }
    public void showScore(){
        frame.getGraphics().drawString(String.format("%d", score), frame.getWidth()/2, frame.getHeight()/2);

    }
}