package com.mario;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.mario.game.Game;
import com.mario.game.utils.Window;
public class Main {
	public static void main(String[] args) {
		final int BOARD_WIDTH = 1268,BOARD_HEIGHT=708;
			//set dark theme
			FlatDarculaLaf.setup();
			try {
			UIManager.setLookAndFeel(new FlatDarculaLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

        

		new Window(BOARD_WIDTH, BOARD_HEIGHT, "Super Mario Game Prototype", new Game());
	}
}