package com.ncku_game;

import java.awt.*;
import javax.swing.*;

import com.doge.OsUtils;

public class HomeScreen extends JFrame{
	private int gameIndex;
	private String pathHeader = "";
	
	public HomeScreen(int gameIndex) {
		this.gameIndex = gameIndex;
		init();
	}
	
	private void init() {
		try {
			if(OsUtils.isWindows()){
				pathHeader = "";
			}
			else{
				pathHeader = "";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setSize(1000,800);
		switch(gameIndex){
			case 1:{
				this.setTitle("Joining the 1st game...");
				break;
			}
			case 2:{
				this.setTitle("Joining the 2nd game...");
				break;
			}
			case 3:{
				this.setTitle("Joining the 3rd game...");
				break;
			}
		}	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //exit the whole program
		this.setResizable(false);
		
	   
		//Container
		JPanel container = new JPanel();
		container.setBounds(300, 300, 400, 350);
		container.setBackground(new Color(0,0,0,0));
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		//this.setContentPane(container);
		
		//heading
		JPanel heading;
		heading = new JPanel();
		heading.setBackground(new Color(10,10,10,70));
		heading.setBounds(0,0,1000,250);
		
		Font font = new Font("Serif", Font.BOLD,100);
		JLabel title;
		switch(gameIndex){
			case 1:{
				title = new JLabel("Joining the 1st game");
				break;
			}
			case 2:{
				title = new JLabel("Joining the 2nd game");
				break;
			}
			case 3:{
				title = new JLabel("Joining the 3rd game");
				break;
			}
			default:{
				title = new JLabel("Joining the 1st game");
				break;
			}
		}	
		title.setFont(font);
		title.setBounds(300,100,300,100);
		heading.add(title);
		
		//background
		ImageIcon background_img;
		switch(gameIndex){
			case 1:{
				background_img = new ImageIcon(pathHeader + "src/res/image/ncku_game/path1.png");
				break;
			}
			case 2:{
				background_img = new ImageIcon(pathHeader + "src/res/image/ncku_game/path2.png");
				break;
			}
			case 3:{
				background_img = new ImageIcon(pathHeader + "src/res/image/ncku_game/path3.png");
				break;
			}
			default:{
				background_img = new ImageIcon(pathHeader + "src/res/image/ncku_game/path1.png");
				break;
			}
		}
		Image img = background_img.getImage();
		Image temp_img = img.getScaledInstance(1000, 800, Image.SCALE_SMOOTH);
		background_img = new ImageIcon(temp_img);
		JLabel background = new JLabel("", background_img, JLabel.CENTER);
		background.add(container);
		background.add(heading);
		background.setBounds(0,0,1000,800);
		add(background);
			
		
	}
	
}
