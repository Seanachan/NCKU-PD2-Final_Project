package com.doge;
import javax.swing.*;
import java.awt.*;

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
	            " 1. Goal: Collect \" System.out.print(\"NCKU\") \"    \n" +
	            " 2. Elapsed Time Equals To Your Score   \n" +
	            " 3. Arrow keys control the direction. Press Space to pause   \n" +
	            " 4. If you eat the wrong letter (symbol), you will lose one letter (symbol).   \n" +
	            " 5. If you collide with your own body, you will need to restart.   \n" +
	            " 6. If you exceeds 180 sec, it will be considered failure.   \n\n" 
	            );

	    rulesTextArea.setEditable(false);
	    rulesTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
	    

	    rulesTextArea.setForeground(Color.WHITE); 
	    rulesTextArea.setBackground(Color.DARK_GRAY);

	    rulesDialog.add(rulesTextArea);
	    rulesDialog.setVisible(true);
	}
}
