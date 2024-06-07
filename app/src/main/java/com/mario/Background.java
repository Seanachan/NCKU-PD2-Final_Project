package com.mario;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.time.LocalDate;

public class Background extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Integer sPrice,hPrice,lPrice,ePrice,difPrice,dealNum;
	Integer n1,n2,n3,n4,n5,n6,n7,n8,n9;
	Background() {
		setMinimumSize(new Dimension(1120, 630));
		setResizable(false);//cannot adjust window size
		sPrice=12;hPrice=12;lPrice=12;ePrice=12;difPrice=12;dealNum=12;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.BLACK);
		
		JPanel info = new JPanel();
		info.setBounds(57, 15, 1020, 42);
		info.setAlignmentY(Component.TOP_ALIGNMENT);
		contentPane.add(info);
		info.setLayout(new BoxLayout(info, BoxLayout.X_AXIS));
		
		JPanel panel_3 = new JPanel();
		info.add(panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] {142, 80, 0, 30, 0};
		gbl_panel_3.rowHeights = new int[]{16, 0, 0, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel stockName = new JLabel("櫃買加權.9065");
		stockName.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		stockName.setForeground(Color.CYAN);
		GridBagConstraints gbc_stockName = new GridBagConstraints();
		gbc_stockName.anchor = GridBagConstraints.WEST;
		gbc_stockName.fill = GridBagConstraints.VERTICAL;
		gbc_stockName.insets = new Insets(0, 0, 5, 5);
		gbc_stockName.gridx = 0;
		gbc_stockName.gridy = 2;
		panel_3.add(stockName, gbc_stockName);
		stockName.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		JLabel date = new JLabel(LocalDate.now().toString());
		date.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		date.setForeground(Color.WHITE);
		GridBagConstraints gbc_date = new GridBagConstraints();
		gbc_date.fill = GridBagConstraints.BOTH;
		gbc_date.insets = new Insets(0, 0, 5, 5);
		gbc_date.gridx = 2;
		gbc_date.gridy = 2;
		panel_3.add(date, gbc_date);
		
		JPanel panel_1 = new JPanel();
		info.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{30, 0};
		gbl_panel_1.rowHeights = new int[]{16, 16, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel highestPrice = new JLabel("最高:"+hPrice.toString());
		highestPrice.setForeground(Color.RED);
		GridBagConstraints gbc_highestPrice = new GridBagConstraints();
		gbc_highestPrice.anchor = GridBagConstraints.SOUTHEAST;
		gbc_highestPrice.insets = new Insets(0, 0, 5, 0);
		gbc_highestPrice.gridx = 0;
		gbc_highestPrice.gridy = 0;
		panel_1.add(highestPrice, gbc_highestPrice);
		
		JLabel lowestPrice = new JLabel("最低:"+lPrice.toString());
		lowestPrice.setForeground(Color.RED);
		GridBagConstraints gbc_lowestPrice = new GridBagConstraints();
		gbc_lowestPrice.anchor = GridBagConstraints.SOUTH;
		gbc_lowestPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_lowestPrice.gridx = 0;
		gbc_lowestPrice.gridy = 1;
		panel_1.add(lowestPrice, gbc_lowestPrice);
		
		JPanel panel = new JPanel();
		info.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{30, 0};
		gbl_panel.rowHeights = new int[]{16, 16, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel startPrice = new JLabel("開盤:"+sPrice.toString());
		startPrice.setForeground(Color.RED);
		GridBagConstraints gbc_startPrice = new GridBagConstraints();
		gbc_startPrice.fill = GridBagConstraints.BOTH;
		gbc_startPrice.insets = new Insets(0, 0, 5, 0);
		gbc_startPrice.gridx = 0;
		gbc_startPrice.gridy = 0;
		panel.add(startPrice, gbc_startPrice);
		
		JLabel endPrice = new JLabel("收盤:"+ePrice.toString());
		endPrice.setForeground(Color.RED);
		GridBagConstraints gbc_endPrice = new GridBagConstraints();
		gbc_endPrice.fill = GridBagConstraints.BOTH;
		gbc_endPrice.gridx = 0;
		gbc_endPrice.gridy = 1;
		panel.add(endPrice, gbc_endPrice);
		
		JPanel panel_2 = new JPanel();
		info.add(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{79, 0};
		gbl_panel_2.rowHeights = new int[]{16, 16, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel diffPrice = new JLabel("漲跌:"+difPrice.toString());
		diffPrice.setForeground(Color.RED);
		GridBagConstraints gbc_diffPrice = new GridBagConstraints();
		gbc_diffPrice.fill = GridBagConstraints.BOTH;
		gbc_diffPrice.insets = new Insets(0, 0, 5, 0);
		gbc_diffPrice.gridx = 0;
		gbc_diffPrice.gridy = 0;
		panel_2.add(diffPrice, gbc_diffPrice);
		
		JLabel dealAmount = new JLabel("成交量:"+dealNum.toString());
		dealAmount.setForeground(Color.RED);
		GridBagConstraints gbc_dealAmount = new GridBagConstraints();
		gbc_dealAmount.fill = GridBagConstraints.BOTH;
		gbc_dealAmount.gridx = 0;
		gbc_dealAmount.gridy = 1;
		panel_2.add(dealAmount, gbc_dealAmount);
		
		JPanel panel_4 = new JPanel();
		panel_4.setForeground(Color.WHITE);
		panel_4.setBackground(Color.BLACK);
		panel_4.setBounds(1030, 32, 69, 564);
		contentPane.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel num1 = new JLabel(String.format("%.2f", n1));
		num1.setForeground(Color.WHITE);
		num1.setAlignmentY(Component.TOP_ALIGNMENT);
		num1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_4.add(num1);
		
		JLabel num2 = new JLabel(String.format("%.2f", n2));
		num2.setForeground(Color.WHITE);
		num2.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_4.add(num2);
		
		JLabel num3 = new JLabel(String.format("%.2f", n3));
		num3.setForeground(Color.WHITE);
		panel_4.add(num3);
		
		JLabel num4 = new JLabel(String.format("%.2f", n4));
		num4.setForeground(Color.WHITE);
		panel_4.add(num4);
		
		JLabel num5 = new JLabel(String.format("%.2f", n5));
		num5.setForeground(Color.WHITE);
		panel_4.add(num5);
		
		JLabel num6 = new JLabel(String.format("%.2f", n6));
		num6.setForeground(Color.WHITE);
		panel_4.add(num6);
		
		JLabel num7 = new JLabel(String.format("%.2f", n7));
		num7.setForeground(Color.WHITE);
		panel_4.add(num7);
		
		JLabel num8 = new JLabel(String.format("%.2f", n8));
		num8.setForeground(Color.WHITE);
		panel_4.add(num8);
		
		JLabel num9 = new JLabel(String.format("%.2f", n9));
		num9.setForeground(Color.WHITE);
		panel_4.add(num9);
		
		Grids grids = new Grids(915, 220, 8, 6);// 60% of total width
		grids.setBackground(new Color(0,0,0,255));
		grids.setBounds(56, 59, 971, 501);
		contentPane.add(grids);
		grids.setLayout(new BoxLayout(grids, BoxLayout.X_AXIS));
		
		KLineGraph KGraph = new KLineGraph();
		KGraph.setBounds(57, 69, 970, 491);
		contentPane.add(KGraph);
		KGraph.setOpaque(false);
		KGraph.setBackground(new Color(0,0,0,255));
		pack();
		setVisible(true);
	}
	void updateInfo(int sPrice, int ePrice, int hPrice, int lPrice){
		this.sPrice=sPrice;
		this.ePrice=ePrice;
		this.hPrice=hPrice;
		this.lPrice=lPrice;
	}
}

class Grids extends JPanel{
    int width, height; int rows,cols;
    int BOARD_WIDTH = 1120,BOARD_HEIGHT=630;
    Grids(int w,int h,int r,int c){
        this.width=w;
        this.height=h;
        this.rows=r;
        this.cols=c;
    }
    
    // @Override
    protected void paintComponent(Graphics g) {
        //when initiated, paintComponent is called
        super.paintComponent(g);
        int i;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(1));
        int frameStartX=0,frameEndX=this.getBounds().width;
        int frameStartY=10;int frameEndY=this.getBounds().height;

        // draw horizontal
        int boxHeight = (frameEndY-frameStartY) / (rows);
        int boxWidth = (frameEndX-frameStartX) / (cols);
        g.setColor(Color.BLUE);
        //draw frame of kline
        g.drawLine(frameStartX, frameStartY, frameStartX, frameEndY);
        g.drawLine(frameStartX, frameStartY, frameEndX, frameStartY);
        g.drawLine(frameStartX, frameEndY, frameEndX, frameEndY);
        g.drawLine(frameEndX, frameStartY, frameEndX, frameEndY);

        g.setColor(Color.WHITE);

        //draw horizontal
        for (i = 1; i < rows; i++){
            g.drawLine(frameStartX, frameStartY + i * boxHeight, frameEndX, frameStartY+i * boxHeight);
        }
        // draw the vertical
        for (i = 1; i < cols; i++){
            g.drawLine(frameStartX + i*boxWidth, frameStartY, frameStartX + i*boxWidth, frameEndY);
        }
    }
}
