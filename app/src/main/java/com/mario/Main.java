package com.mario;
import java.awt.CardLayout;
// import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarculaLaf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

// import javax.swing.UIManager;
public class Main {
	// static Handler handler;
	// JLabel hello1;
	// static JButton b1;
	public static void main(String[] args) {
		final int BOARD_WIDTH = 1268,BOARD_HEIGHT=708;
        //set dark theme
        FlatDarculaLaf.setup();
        try {
			UIManager.setLookAndFeel(new FlatDarculaLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
        

        // JFrame frame = new JFrame("CardLayout Example");
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(400, 300);

        // // Create the panel that uses CardLayout
        // JPanel cardPanel = new JPanel(new CardLayout());

        // // Create the cards
        // JPanel card1 = new JPanel();
        // card1.add(new JLabel("This is the first panel."));
        // JButton switchToCard2 = new JButton("Switch to Card 2");
        // card1.add(switchToCard2);

        
        Background card2 = new Background(BOARD_WIDTH,BOARD_HEIGHT);
//        JPanel card2 = new JPanel();
//        card2.add(new JLabel("This is the first panel."));
//        JButton switchToCard1 = new JButton("Switch to Card 2");
//        card1.add(switchToCard2);

        // Add cards to the cardPanel
        // cardPanel.add(card1, "card1");
//        cardPanel.add(card2, "card2");

        // Add ActionListeners to switch between cards
        // switchToCard2.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         CardLayout cl = (CardLayout)(cardPanel.getLayout());
        //         cl.show(cardPanel, "card2");
        //     }
        // });

        // // Add cardPanel to the frame
        // frame.getContentPane().add(cardPanel);

        // // Display the window
        // frame.setVisible(true);
    }
}
// @Override
	// 	public void start(Stage stage) {
	// 		try {
	// 			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Scene1.fxml"));
	// 			Scene scene = new Scene(root,1268,708);
	// 			stage.setScene(scene);
	// 			stage.show();
				
	// 		} catch (Exception e) {
	// 			e.printStackTrace();
	// 		}
	// 	}