package com.mario;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Level extends JPanel {

    public Level() {
        // 使 JPanel 背景透明以顯示底下的 Background
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 繪製方塊
        g.setColor(Color.ORANGE);
        g.fillRect(100, getHeight() - 100, 50, 50);
        g.fillRect(200, getHeight() - 150, 50, 50);
        g.fillRect(300, getHeight() - 100, 50, 50);
    }
}
