package com.mario.utils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import com.doge.OsUtils;
import com.mario.Game;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class Window {
    public static JFrame frame;
    public static int score = 1;
    public static boolean isComplete = false;
    public static boolean shouldShowScore=false;
    static int width;
    static int height;
    Clip bgm = null;

    public Window(int width, int height, String title, Game game){
        Window.width=width;
        Window.height=height;
        game.setPreferredSize(new Dimension(width, height));
        game.setMinimumSize(new Dimension(width, height));
        game.setMaximumSize(new Dimension(width, height));

        frame = new JFrame(title);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);       
        
        try {
            if(OsUtils.isWindows()){
				loadBGM("src/res/sound/mario.wav");
            }
            else{
				loadBGM("src/res/sound/mario.wav");
            }
            
        }catch (Exception e) {
            e.printStackTrace();
        }
		playBGM();

        game.start(); 
    }
    public static void closeWindow(int score,boolean win){
        try {
            frame.dispose();    
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame overFrame = new JFrame();
        overFrame.setBackground(Color.black);
        overFrame.setPreferredSize(new Dimension(width, height));
        overFrame.setMinimumSize(new Dimension(width, height));
        overFrame.setMaximumSize(new Dimension(width, height));
        overFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        overFrame.setResizable(false);
        overFrame.setLocationRelativeTo(null);
        overFrame.setVisible(true);
        overFrame.addWindowListener(new WindowAdapter() {
			
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(
                        null,
                        "See Your Total scores?",
                        "You have finished all the games",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirmed == JOptionPane.YES_OPTION) {
                	isComplete=true;
                    System.out.println("mario isComplete:" + isComplete);
                }else{
                    isComplete=true;
                }
            }
        });

        JLabel gameOverLabel = new JLabel();
        gameOverLabel.setBackground(Color.BLACK);
        gameOverLabel.setHorizontalAlignment(JLabel.CENTER);
        gameOverLabel.setVerticalAlignment(JLabel.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        gameOverLabel.setForeground(Color.WHITE);
        gameOverLabel.setBackground(Color.DARK_GRAY);
        gameOverLabel.setOpaque(true);

        // Use HTML to center the text
        String text;
        int finalScore = score;
        if (win) {
            text = "<html><div style='text-align: center;'>You Win!<br/>Score: " + finalScore + "</div></html>";
        } else {
            text = "<html><div style='text-align: center;'>Game Over!<br/>Score: " + finalScore + "</div></html>";
        }
        Window.score = finalScore;
        gameOverLabel.setText(text);
        overFrame.add(gameOverLabel);
        overFrame.setVisible(true);
    }

    private void loadBGM(String soundPath) {
		try {
			bgm = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(soundPath));
			bgm.open(ais);
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
	        e.printStackTrace();
	    }
	}

    private void playBGM() {
		if (bgm != null) {
            bgm.loop(Clip.LOOP_CONTINUOUSLY);
        }
	}
	
	private void stopBGM() {
		if (bgm != null) {
            bgm.stop();
        }
	}

	public void closeBGM() {
		if (bgm != null) {
            bgm.close();
            bgm = null;
        }
	}
}
