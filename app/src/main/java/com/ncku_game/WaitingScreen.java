package com.ncku_game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

import com.doge.OsUtils;


public class WaitingScreen extends JFrame{
	
	public WaitingScreen() {
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
		this.setSize(1000,800);
		this.setTitle("Waiting Room");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		this.addWindowListener(new WindowAdapter() {
			
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to exit?",
                        "Confirm Exit",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirmed == JOptionPane.YES_OPTION) {				              	
                    dispose();
					System.exit(0);
                }
            }
        });
		
	
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
		JLabel title = new JLabel("NCKU GAME");
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

		//TextField
		JTextField nameField = new JTextField();
		nameField.setMaximumSize(new Dimension(200, 30));
		container.add(nameField, BorderLayout.CENTER);

		//Name label
		JLabel nameLabel = new JLabel("Type Your Name");
		nameLabel.setForeground(Color.white);
		nameLabel.setBounds(350, 400, 200, 200);
		nameLabel.setSize(200, 200);
		container.add(nameLabel);
		
		//blank
		JLabel blank1 = new JLabel(" ");
		blank1.setSize(200, 200);
		container.add(blank1);

		//blank
		JLabel blank2 = new JLabel(" ");
		blank2.setSize(200, 200);
		container.add(blank2);

		//buttons
		JButton button1 = new JButton("Join The Game");
		JButton button2 = new JButton("Exit");

		button1.setSize(200,200);
		container.add(button1);
		button1.setAlignmentX(CENTER_ALIGNMENT);
		button1.setAlignmentY(CENTER_ALIGNMENT);
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(PlayerSocket.playerState == PlayerSocket.NOT_JOINED) {
					PlayerSocket.sentToServer = true;
					PlayerSocket.receiveFromServer = true;
					PlayerSocket.playerName = nameField.getText();
					PlayerSocket.waitingScreen.setVisible(false);
					title.setText("等待更多玩家加入");	
					container.remove(nameField);
					container.remove(button1);
					container.remove(button2);
					container.remove(nameLabel);
				}	
			}	
		});
		//blank
		JLabel blank3 = new JLabel(" ");
		blank3.setSize(200, 200);
		container.add(blank3);
	
		container.add(button2);
		button2.setAlignmentX(CENTER_ALIGNMENT);
		button2.setAlignmentY(CENTER_ALIGNMENT);
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}	
		});	
	
	}
}
