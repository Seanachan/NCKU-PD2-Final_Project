package com.mario;
import com.formdev.flatlaf.FlatDarculaLaf;

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
        int BOARD_WIDTH = 1120,BOARD_HEIGHT=630;
        
        // Background background = new Background(BOARD_WIDTH,BOARD_HEIGHT);//create background object  
        //background.show();
        
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Background frame = new Background();
					frame.setVisible(true);
                    Level level = new Level();  // 創建 Level 實例
                    frame.add(level);           // 將 Level 添加到 Background
                    frame.setSize(BOARD_WIDTH, BOARD_HEIGHT);  // 確認設置窗口大小
                    frame.validate();           // 重新布局組件
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Background frame = new Background();
                    Level level = new Level();    // 創建 Level 實例
                    frame.add(level);             // 將 Level 添加到 Background
                    frame.setSize(BOARD_WIDTH, BOARD_HEIGHT);  // 確認設置窗口大小
                    frame.validate();             // 重新布局組件
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
