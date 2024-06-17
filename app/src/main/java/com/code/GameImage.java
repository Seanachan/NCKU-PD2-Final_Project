package com.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameImage extends JPanel{
	
	static boolean UP = false;
	static boolean DOWN = false;
	static boolean LEFT = false;	
	static boolean RIGHT = true;
	
	
	public static ImageIcon S = new ImageIcon("src/res/image/code/S.png");
	public static ImageIcon y = new ImageIcon("src/res/image/code/y.png");
	public static ImageIcon s = new ImageIcon("src/res/image/code/s_.png");
	public static ImageIcon t = new ImageIcon("src/res/image/code/t.png");
	public static ImageIcon e = new ImageIcon("src/res/image/code/e.png");
	public static ImageIcon m = new ImageIcon("src/res/image/code/m.png");
	public static ImageIcon o = new ImageIcon("src/res/image/code/o.png");
	public static ImageIcon u = new ImageIcon("src/res/image/code/u.png");
	public static ImageIcon p = new ImageIcon("src/res/image/code/p.png");
	public static ImageIcon r = new ImageIcon("src/res/image/code/r.png");
	public static ImageIcon i = new ImageIcon("src/res/image/code/i.png");
	public static ImageIcon n = new ImageIcon("src/res/image/code/n.png");
	
	public static ImageIcon dot = new ImageIcon("src/res/image/code/dot.png");

	public static ImageIcon leftP = new ImageIcon("src/res/image/code/LP.png");
	public static ImageIcon rightP = new ImageIcon("src/res/image/code/RP.png");
	public static ImageIcon quotation = new ImageIcon("src/res/image/code/QU.png");
	
	public static ImageIcon N = new ImageIcon("src/res/image/code/N_.png");
	public static ImageIcon C = new ImageIcon("src/res/image/code/C.png");
	public static ImageIcon K = new ImageIcon("src/res/image/code/K.png");
	public static ImageIcon U = new ImageIcon("src/res/image/code/U_.png");
	
	public static Image setImage(int index) {
		switch(index) {
		case 1:return y.getImage();
		case 2:return s.getImage();
		case 3:return t.getImage();
		case 4:return e.getImage();
		case 5:return m.getImage();
		case 6:return dot.getImage();
		case 7:return o.getImage();
		case 8:return u.getImage();
		case 9:return t.getImage();
		case 10:return dot.getImage();
		case 11:return p.getImage();
		case 12:return r.getImage();
		case 13:return i.getImage();
		case 14:return n.getImage();
		case 15:return t.getImage();
		case 16:return leftP.getImage();
		case 17:return quotation.getImage();
		case 18:return N.getImage();
		case 19:return C.getImage();
		case 20:return K.getImage();
		case 21:return U.getImage();
		case 22:return quotation.getImage();
		case 23:return rightP.getImage();
		default: return null;
		}
		
		
	}
	
	public static void drawWord(Graphics g,String str, Color color, int size, int x, int y) {
		g.setColor(color);
		g.setFont(new Font("arial", Font.BOLD, size));
		g.drawString(str, x, y);
	}
	
	
	
}
