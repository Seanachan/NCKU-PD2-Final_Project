package com.doge;

import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

class Grids extends JPanel {
    private static final long serialVersionUID = 1L;
    int width, height;
    int rows, cols;
    int BOARD_WIDTH = 1268, BOARD_HEIGHT = 708;
    static boolean shouldDraw = false; // Add this flag
    public Grids(int w, int h, int r, int c) {
        this.width = w;
        this.height = h;
        this.rows = r;
        this.cols = c;
    }
    public static void setShouldDraw(boolean b) {
        shouldDraw = true;
        // repaint(); // Trigger repaint when the flag changes
    }
    @Override
    protected void paintComponent(Graphics g) {
        //when initiated, paintComponent is called
        // if(!shouldDraw) {repaint();return;}
        super.paintComponent(g);
        int i;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(1));
        int frameStartX = 0, frameEndX = this.getBounds().width;
        int frameStartY = 10;
        int frameEndY = this.getBounds().height;

        // draw horizontal
        int boxHeight = (frameEndY - frameStartY) / (rows);
        int boxWidth = (frameEndX - frameStartX) / (cols);
        g.setColor(Color.BLUE);
        //draw frame of kline
        g.drawLine(frameStartX, frameStartY, frameStartX, frameEndY);
        g.drawLine(frameStartX, frameStartY, frameEndX, frameStartY);
        g.drawLine(frameStartX, frameEndY, frameEndX, frameEndY);
        g.drawLine(frameEndX, frameStartY, frameEndX, frameEndY);

        g.setColor(Color.WHITE);

        //draw horizontal
        for (i = 1; i < rows; i++) {
            g.drawLine(frameStartX, frameStartY + i * boxHeight, frameEndX, frameStartY + i * boxHeight);
        }
        // draw the vertical
        for (i = 1; i < cols; i++) {
            g.drawLine(frameStartX + i * boxWidth, frameStartY, frameStartX + i * boxWidth, frameEndY);
        }
    }
}