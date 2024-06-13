package com.mario;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarculaLaf;
public class Main {
	public static void main(String[] args) {
		
			//set dark theme
			FlatDarculaLaf.setup();
			try {
			UIManager.setLookAndFeel(new FlatDarculaLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

        Background card2 = new Background();
	}
}