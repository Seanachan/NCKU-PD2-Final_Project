package com.mario;
import com.formdev.flatlaf.FlatDarculaLaf;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.UIManager;

// import javax.swing.UIManager;
public class Main {
    public static void main(String[] args) throws Exception {
        //set dark theme
        FlatDarculaLaf.setup();
        UIManager.setLookAndFeel(new FlatDarculaLaf());

        //set background size 
        final int BOARD_WIDTH = 1268,BOARD_HEIGHT=708;   

        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorderPane root = new BorderPane();
	        		// Scene scene = new Scene(root,BOARD_WIDTH,BOARD_HEIGHT);
					Background frame = new Background(BOARD_WIDTH, BOARD_HEIGHT);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}
