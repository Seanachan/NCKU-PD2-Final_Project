package com.mario;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class StartScene extends JPanel{
	public StartScene() throws IOException {
		
		BufferedImage startSceneImg = ImageIO.read(new File("app/src/img/startScene"));	
	}
}
