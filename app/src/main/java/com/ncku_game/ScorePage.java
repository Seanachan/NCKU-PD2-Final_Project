package com.ncku_game;

import java.awt.*;

import javax.swing.*;

import com.doge.OsUtils;

public class ScorePage extends JFrame{
	public ScorePage() {
		init();
	}
	
	private void init() {
		String pathHeader = "";
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
		this.setLocationRelativeTo(null);
		this.setSize(1000,800);
		this.setTitle("Scores");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //exit the whole program
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		

		//Container
		JPanel container = new JPanel();
		container.setBounds(350, 300, 400, 350);
		container.setBackground(new Color(0,0,0,0));
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		//this.setContentPane(container);
		
		//heading
		JPanel heading;
		heading = new JPanel();
		heading.setBackground(new Color(10,10,10,70));
		heading.setBounds(0,0,1000,250);
		
		Font font = new Font("Serif", Font.BOLD,100);
		JLabel title = new JLabel("Scores");
		title.setFont(font);
		title.setBounds(300,100,300,100);
		heading.add(title);
		
		//background
		ImageIcon background_img = new ImageIcon(pathHeader + "src/res/image/ncku_game/ncku_tree.png");
		Image img = background_img.getImage();
		Image temp_img = img.getScaledInstance(1000, 800, Image.SCALE_SMOOTH);
		background_img = new ImageIcon(temp_img);
		JLabel background = new JLabel("", background_img, JLabel.CENTER);
		background.add(container);
		background.add(heading);
		background.setBounds(0,0,1000,800);
		add(background);

		//labels
		JLabel player1NameScore = new JLabel("⭐第一名     " + PlayerSocket.name_scores[0] + " points");
		player1NameScore.setForeground(Color.white);
		player1NameScore.setFont(new Font("Serif", Font.BOLD,30));
		container.add(player1NameScore);

		JLabel player2NameScore = new JLabel("⭐第二名     " + PlayerSocket.name_scores[1] + " points");
		player2NameScore.setForeground(Color.white);
		player2NameScore.setFont(new Font("Serif", Font.BOLD,30));
		container.add(player2NameScore);

		JLabel player3NameScore = new JLabel("⭐第三名     " + PlayerSocket.name_scores[2] + " points");
		player3NameScore.setForeground(Color.white);
		player3NameScore.setFont(new Font("Serif", Font.BOLD,30));
		container.add(player3NameScore);
	
	}

}
