package com.doge;
import java.awt.*;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class DogeGame {
    public static JFrame frame;
    public static boolean isComplete = false;
    public static int score = 0;

    public void launch() {
        int BOARD_WIDTH = 1268, BOARD_HEIGHT = 708;

        frame = new JFrame("Doge Game");
        FlappyBird flappyBird = new FlappyBird();
        showRules(frame);

        FlappyBird.startAllTimer();

        frame.add(flappyBird);
        frame.setSize(BOARD_WIDTH, BOARD_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
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

        System.out.println("Game Over. Final Score: " + DogeGame.score);
        frame.dispose();
    }
    public static void showRules(JFrame frame) {
	    // Create a separate dialog to show the rules
	    JDialog rulesDialog = new JDialog(frame, "Rules", Dialog.ModalityType.APPLICATION_MODAL);
	    rulesDialog.setSize(525, 225);
	    rulesDialog.setLocationRelativeTo(frame);
	    JTextArea rulesTextArea = new JTextArea();
	    rulesTextArea.setText(" Game Rules :\n\n" +
	            " 1. Goal: Dodge the chives on the path to let Doge Coin surrvive.    \n" +
	            " 2. Elapsed Time Equals To Your Score, total of 100 points.   \n" +
	            " 3. Press space bar to fly, but the gravity will also drag you down.   \n" +
                " 4. The total amount of time is 60 seconds, try the best you can!   \n\n" +
	            " 5. !! THE GAME WILL START AS SOON AS YOU CLOSE THIS TAB !!   \n" 
	            );

	    rulesTextArea.setEditable(false);
	    rulesTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
	    

	    rulesTextArea.setForeground(Color.WHITE); 
	    rulesTextArea.setBackground(Color.DARK_GRAY);

	    rulesDialog.add(rulesTextArea);
	    rulesDialog.setVisible(true);
	}
}
