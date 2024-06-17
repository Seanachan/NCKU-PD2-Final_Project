package com.code;

import java.awt.Color;
import java.util.Random;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JPanel;



public class Panel extends JPanel implements ActionListener {
	private int length = 1;
	public int[] codeX = new int[800];
	public int[] codeY = new int[800];
	
	public static int foodIndex = 3;
	public static int incorrectIndex = 4;
	
	public static String direction = "R";
	public static boolean isStart = false;
	public static boolean isOverlap = false;
	public static boolean isComplete = false;
	public static boolean isFail = false;
	//spend time
	public static int elapsedSeconds = 0;
	
	private int foodX;
	private int foodY;
	private int incorrectFoodX;
	private int incorrectFoodY;
	private boolean eatCorrectFood = false;
	
	private int width = 25;
	private int height = 25;
	
	Random rand = new Random();
	Random randIncorrect = new Random();
	
	private Timer timer = new Timer(80, this);
	
	

//Constructor
 	public Panel() {
 		initDraw();
 		timer.start();
 	}
	
	
	public void paint(Graphics g) {
		
		super.paint(g);
		int panelWidth = getWidth();
        int panelHeight = getHeight();
        int rectWidth = 970;
        int rectHeight = 750;
        int rectX = (panelWidth - rectWidth) / 2;
        int rectY = (panelHeight - rectHeight) / 2;
        g.setColor(Color.black);
	    g.fillRect(rectX, rectY, rectWidth, rectHeight);
	    g.setColor(Color.gray);
	    //7, 730 
	    g.fillRect(rectX, rectY+724, rectWidth, 40);
	    g.drawImage(GameImage.S.getImage(), codeX[0], codeY[0], width, height, null);
	    //body
	    for(int i = 1; i < length; i++) {
	    	g.drawImage(GameImage.setImage(i), codeX[i], codeY[i], width, height, null);
	    }
	    //food
	    g.drawImage(GameImage.setImage(foodIndex), foodX, foodY, width, height, null);
	    //incorrect food
	    g.drawImage(GameImage.setImage(incorrectIndex), incorrectFoodX, incorrectFoodY, width, height, null);
	    
	    //stop  
	    if(!isStart && !isComplete) {
	    	GameImage.drawWord(g, "Press Space to Start", Color.WHITE, 40,300, 350);
	    }
	    
	    if(isOverlap && !isFail) {
	    	GameImage.drawWord(g, "Unfortunately, Press Space to Restart", Color.WHITE, 40,150, 350);
	    }
	    if(isComplete) {
	    	if(!isFail) {
	    		GameImage.drawWord(g, "Good Job~ You Spend : " + elapsedSeconds + " sec", Color.WHITE, 40,200, 285);
	    		GameImage.drawWord(g, "View your work", Color.WHITE, 40,260, 432);
	    	}else {
	    		GameImage.drawWord(g, "I am Sorry, You Failed", Color.WHITE, 40,260, 400);
	    	}
	    }else {
	    	GameImage.drawWord(g, ""+elapsedSeconds, Color.WHITE, 30,450, 760);
	    }
	    
	}
	
	public void initDraw() {
		length = 3;
		codeX[0] = 75+7;
		codeY[0] = 75+6;
		codeX[1] = 75+7-25;
		codeY[1] = 75+6;
		codeX[2] = 75+7-50;
		codeY[2] = 75+6;
		
		foodX = 7 + 25*rand.nextInt(39);
		foodY = 6 + 25*rand.nextInt(29);
		
		incorrectFoodX = setIncorrectX(foodX);
		incorrectFoodY = setIncorrectY(foodY);
		
		incorrectIndex = setIncorrectIndex(foodIndex);
		
		direction = "R";
		foodIndex = 3;
	}
	
	int setIncorrectX(int foodX) {
		incorrectFoodX = 7 + 25*rand.nextInt(39);
		while(incorrectFoodX == foodX) incorrectFoodX = 7 + 25*rand.nextInt(39);
		return incorrectFoodX;
	}
	
	int setIncorrectY(int foodY) {
		incorrectFoodY = 6 + 25*rand.nextInt(29);
		while(incorrectFoodY == foodY) incorrectFoodY = 6 + 25*rand.nextInt(29);
		return incorrectFoodY;
	}
	
	int setIncorrectIndex(int foodIndex) {
		incorrectIndex = 3 + 1 * randIncorrect.nextInt(21) ;
		switch(foodIndex) {
			case 3:
			case 9:
			case 15: 
				while(incorrectIndex == 3 || incorrectIndex == 9 || incorrectIndex == 15 ) {
					incorrectIndex = 3 + 1 * randIncorrect.nextInt(21) ;
				}
				return incorrectIndex;
			case 6:
			case 10:
				while(incorrectIndex == 6 || incorrectIndex == 10) {
					incorrectIndex = 3 + 1 * randIncorrect.nextInt(21) ;
				}
				return incorrectIndex;
			case 17:
			case 22:
				while(incorrectIndex == 17 || incorrectIndex == 22) {
					incorrectIndex = 3 + 1 * randIncorrect.nextInt(21) ;
				}
				return incorrectIndex;
			default:
				while(incorrectIndex == foodIndex) {
					incorrectIndex = 3 + 1 * randIncorrect.nextInt(21) ;
				}
				if(foodIndex == 24 || length == 0) incorrectIndex = 24;
				return incorrectIndex;
				
		}
		
	}



//Time
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(isStart && !isOverlap ) {
			for(int i= length -1; i > 0; i--) {
				codeX[i] = codeX[i-1];
				codeY[i] = codeY[i-1];
			}
			if(direction == "R") {
				codeX[0] = codeX[0]+25;
				if(codeX[0] > 977) {
					codeX[0] = 7;
				}
			}else if(direction == "L") {
				codeX[0] = codeX[0]-25;
				if(codeX[0] < 7) {
					codeX[0] = 957;
				}
			}else if(direction == "U") {
				codeY[0] = codeY[0]-25;
				if(codeY[0] < 6) {
					codeY[0] = 706;
				}
			}else if(direction == "D") {
				codeY[0] = codeY[0]+25;
				if(codeY[0] > 706) {
					codeY[0] = 6;
				}
			}
			//+1
			if(codeX[0] == foodX && codeY[0] == foodY) {
				eatCorrectFood = true;
				foodIndex++;
				length++;
				foodX = 7 + 25*rand.nextInt(39);
				foodY = 6 + 25*rand.nextInt(28);
			}
			//-1
			if(codeX[0] == incorrectFoodX && codeY[0] == incorrectFoodY) {
				foodIndex--;
				length--;
				incorrectFoodX = setIncorrectX(foodX);
				incorrectFoodY = setIncorrectY(foodY);
				incorrectIndex = setIncorrectIndex(foodIndex);
			}
			if(eatCorrectFood) {
				eatCorrectFood = false;
				incorrectFoodX = setIncorrectX(foodX);
				incorrectFoodY = setIncorrectY(foodY);
				incorrectIndex = setIncorrectIndex(foodIndex);
			}
			if(length == 0 || elapsedSeconds >= 180) { //if(length == 0 || elapsedSeconds >= 180) {
				isFail = true;
				isComplete = true;
			}
			
			
			for(int i = 1; i < length; i++) {
				if(!isComplete) {
					if(codeX[0] == codeX[i] && codeY[0] == codeY[i]) {
						isOverlap = true;
					}
				}
			}
			
			repaint();
		}
		
		timer.start();
	}
	
	
	
	
}



