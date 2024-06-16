package com.jkai.mario.utils;

import javax.swing.*;

import com.jkai.mario.Game;
import com.jkai.mario.objects.Player;

import java.awt.*;

public class Window {
    JFrame frame;
    
    public static int score ;
    public static boolean shouldShowScore=false;
    public static boolean isCompleted = false;
    public Window(int width, int height, String title, Game game){
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
        
    }
    public void showScore(){
        frame.getGraphics().drawString(String.format("%d", score), frame.getWidth()/2, frame.getHeight()/2);

    }
}
