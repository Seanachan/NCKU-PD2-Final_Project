package com.code;

import java.awt.Color;

import java.io.*;

import javax.sound.sampled.*;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.doge.OsUtils;

public class GameCode extends JFrame {
	public GameCode() {
		 setFocusableWindowState(true);
		 this.setTitle("System.out.print(\"NCKU\")");
	}
	private Panel p = new Panel();
	private int width = 1000;
	private int height = 801;
	private int playBgmCount = 0;
	private Clip bgm = null;
	
	public void launch() {
		try {
            if(OsUtils.isWindows()){
				loadBGM("src/res/sound/piano.wav");
            }
            else{
				loadBGM("src/res/sound/piano.wav");
            }
            
        }catch (Exception e) {
            e.printStackTrace();
        }
		playBGM();
		setFocusableWindowState(true);
		
		
		this.addKeyListener(new KeyAdapter() {        
 			public void keyPressed(KeyEvent e) {
	    		super.keyPressed(e);
	    		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
	    			if(Panel.isOverlap) {
	    				Panel.isOverlap = false;
	    				stopBGM();
	    				p.initDraw();
	    			}else {
	    				if(playBgmCount > 0) {
	    					if(!Panel.isStart) {
	    						playBGM();
	    					}else {
	    						stopBGM();
	    					}
	    				}
	    				playBgmCount++;	
	    				Panel.isStart = !Panel.isStart;			
	    			}
	    			
	    			repaint();
	    		}
	    		if(e.getKeyCode() == 38) {
	    			Panel.direction = "U";
	    		}
	    		if(e.getKeyCode() == 40) {
	    			Panel.direction = "D";
	    		}
	    		if(e.getKeyCode() == 37) {
	    			Panel.direction = "L";
	    		}
	    		if(e.getKeyCode() == 39) {
	    			Panel.direction = "R";
	    		}
	    	}
 		});
		//avoid
		requestFocusInWindow();
        this.setFocusable(true);
        
	    this.setVisible(true);
		this.setSize(width, height);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
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
                	closeBGM();
                    dispose();
                }
            }
        });
	    this.add(p);
	    this.setVisible(true);
	    showRules();
	    //start counting
	    while(!Panel.isComplete) {
	    	if(Panel.foodIndex == 24) 
	    		Panel.isComplete = true;
	    	if(Panel.isStart) {
	    		Panel.elapsedSeconds = Panel.elapsedSeconds + 1;
	    		//playBGM();
	    	}else {
//	    		if(playBgmCount > 0)
//	    		stopBGM();	    		
	    	}
	    	try {
				Thread.sleep(1200);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
	    }
	}
	
	
	private void showRules() {
	    // Create a separate dialog to show the rules
	    JDialog rulesDialog = new JDialog(this, "Rules", Dialog.ModalityType.APPLICATION_MODAL);
	    rulesDialog.setSize(525, 225);
	    rulesDialog.setLocationRelativeTo(this);
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
	
	private void loadBGM(String soundPath) {
		try {
			bgm = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(soundPath));
			bgm.open(ais);
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
	        e.printStackTrace();
	    }
	}

	private void playBGM() {
		if (bgm != null) {
            bgm.loop(Clip.LOOP_CONTINUOUSLY);
        }
	}
	
	private void stopBGM() {
		if (bgm != null) {
            bgm.stop();
        }
	}

	public void closeBGM() {
		if (bgm != null) {
            bgm.close();
            bgm = null;
        }
	}
	public static void main(String[] args) {
		GameCode codeGame =new GameCode();
		codeGame.launch();
	}
	
}
