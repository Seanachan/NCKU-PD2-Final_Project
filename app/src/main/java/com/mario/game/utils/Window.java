package com.mario.game.utils;

import javax.swing.JFrame;

import com.mario.Background;
import com.mario.game.Game;

import java.awt.Dimension;

public class Window {

    public Window(int width, int height, String title, Game game){
        game.setPreferredSize(new Dimension(width, height));
        game.setMinimumSize(new Dimension(width, height));
        game.setMaximumSize(new Dimension(width, height));

        // JFrame frame = new JFrame(title);
        JFrame frame1 = new Background(game);
//        JFrame frame = new JFrame(title);
//        frame.add(game);
//        frame.pack();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(false);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//        frame.dispose();
//        game.start();
    }
}
