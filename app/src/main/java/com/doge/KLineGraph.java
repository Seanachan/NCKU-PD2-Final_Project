package com.doge;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
import javax.swing.Timer;
class KLine{
	double highest,lowest;
	double startPrice,endPrice;
	KLine(double highest,double lowest, double startPrice,double endPrice){
		this.highest=highest;
		this.lowest=lowest;
		this.startPrice=startPrice;
		this.endPrice=endPrice;
	}
}
class KLineGraph extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	static Deque<KLine> KLineDeque;
	static Double startDouble=0.0,endDouble=0.0,highDouble=0.0,lowDouble=0.0;
	static Integer amountInt=0;
	// Timer updateLineTimer;
	public static Timer gameLoop;
    int timeFrame = 600;
	JPanel bird;
	public KLineGraph(){
		KLineDeque = new ArrayDeque<>();
		// this.updateLineTimer=updateLineTimer;

		gameLoop=new Timer(timeFrame/3,this);
        // gameLoop.start();
	}
	
	public static void setKLine(int birdY) {
		Double randomStart=100.0,randomEnd=100.0,randomHigh,randomLow;
        // double increaseLimit=1.2,decreaseLimit=0.8;
        Random rand = new Random();
		if(KLineDeque.size()>16){
			KLineDeque.removeFirst();
		}
        if(!KLineDeque.isEmpty()) 
			randomStart=KLineDeque.getLast().endPrice;
        else randomStart = (double) (rand.nextInt(200)+200); 

		randomEnd=(double) birdY;

        randomHigh = randomStart+(rand.nextDouble()*0.2)*randomStart;
        randomLow = randomStart-rand.nextDouble()*0.2*randomStart;

		if(randomHigh>708) randomHigh=708.0;
		if(randomLow<0) randomLow=0.0;
		
		// System.out.println("randEnd, randStart");
        // System.out.println(randomEnd+" " +randomStart);
		// System.out.println(randomLow+" " +randomHigh);

		KLineDeque.addLast(new KLine(randomHigh,randomLow,randomStart,randomEnd));
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public static void draw(Graphics g) {
		int i=10;
		int startPrice,endPrice,highestPrice,lowestPrice;
		for(KLine line: KLineDeque) {
			
			startPrice=(int) Math.round(line.startPrice);endPrice=(int) Math.round(line.endPrice);
			highestPrice=(int) Math.round(line.highest);lowestPrice=(int) Math.round(line.lowest);
			if(line.startPrice>line.endPrice) g.setColor(Color.RED);
			else if(line.startPrice<line.endPrice) g.setColor(Color.GREEN);
			else g.setColor(Color.WHITE);

            Graphics2D g2d = (Graphics2D) g;
			g2d.setStroke(new BasicStroke(3));
			g.drawLine(i, highestPrice,i , Math.max(startPrice,endPrice));
			g.drawLine(i, lowestPrice, i, Math.min(startPrice,endPrice));

			g2d.setStroke(new BasicStroke(10));
			g.drawLine(i, startPrice,i , endPrice);
            i+=15;
		}
		Double randomStart=0.0,randomEnd=0.0,randomHigh=0.0,randomLow=0.0;
		Integer randomAmount=0;
		Random rand = new Random();
		randomAmount = rand.nextInt(100000)+1000;
		try {
			if(KLineDeque.size()>0){
				randomEnd=KLineDeque.getLast().endPrice;
				randomStart=KLineDeque.getLast().startPrice;
				highDouble=KLineDeque.getLast().highest;
				lowDouble=KLineDeque.getLast().lowest;
				
				randomHigh=KLineDeque.getLast().highest;
				randomLow=KLineDeque.getLast().lowest;
			}
			
			startDouble=randomStart;
			endDouble=randomEnd;
			highDouble=randomHigh;
			amountInt=randomAmount;

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override 
	public void actionPerformed(ActionEvent e) {        
        Background.updateInfo(endDouble-startDouble, amountInt, highDouble, lowDouble, startDouble, endDouble);
		repaint();
		paintComponent(getGraphics());
	}
}