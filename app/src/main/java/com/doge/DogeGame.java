package com.doge;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class DogeGame {
    public static JFrame frame;
    public static boolean isComplete = false;
    public static int score = 0;
    Clip bgm = null; //bgm

    public void launch() {
        try {
            if(OsUtils.isWindows()){
				loadBGM("src/res/sound/dogeGame.wav");
            }
            else{
				loadBGM("src/res/sound/dogeGame.wav");
            }
            
        }catch (Exception e) {
            e.printStackTrace();
        }
		playBGM();

        int BOARD_WIDTH = 1268, BOARD_HEIGHT = 708;

        frame = new JFrame("Doge Game");
        FlappyBird flappyBird = new FlappyBird();
        showRules(frame);

        FlappyBird.startAllTimer();

        frame.add(flappyBird);
        frame.setSize(BOARD_WIDTH, BOARD_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
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

    public static void main(String[] args) {
        
        DogeGame dogeGame = new DogeGame();
        dogeGame.launch();

        while (!isComplete) {
            try {
                Thread.sleep(10); // reduce CPU usage
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        dogeGame.closeBGM();

        System.out.println("Game Over. Final Score: " + DogeGame.score);
        frame.dispose();
    }
    public static void showRules(JFrame frame) {
	    // Create a separate dialog to show the rules
	    JDialog rulesDialog = new JDialog(frame, "Rules", Dialog.ModalityType.APPLICATION_MODAL);
	    rulesDialog.setSize(525, 225);
        rulesDialog.setFont(new Font("Arial",Font.PLAIN,20));
	    rulesDialog.setLocationRelativeTo(frame);
	    JTextArea rulesTextArea = new JTextArea();
	    rulesTextArea.setText(" Game Rules :\n\n" +
	            " 1. Goal: Dodge the chives on the path to let Doge Coin surrvive.    \n" +
	            " 2. Elapsed Time Equals To Your Score, total of 100 points.   \n" +
	            " 3. Press space bar to fly, but the gravity will also drag you down.   \n" +
                " 4. The total amount of time is 60 seconds, try the best you can!   \n" +
                " 5. But bear in mind that if your position is below half of the screen, the point will be deducted ! \n\n"+
	            " !! THE GAME WILL START AS SOON AS YOU CLOSE THIS TAB !!   \n" 
	            );

	    rulesTextArea.setEditable(false);
	    rulesTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
	    

	    rulesTextArea.setForeground(Color.WHITE); 
	    rulesTextArea.setBackground(Color.DARK_GRAY);

	    rulesDialog.add(rulesTextArea);
	    rulesDialog.setVisible(true);
	}
}
