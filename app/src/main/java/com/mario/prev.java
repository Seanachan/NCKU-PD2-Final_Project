package com.mario;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

//implement stock incline-like background

public class prev{
    JFrame frame=null;
    int BOARD_WIDTH,BOARD_HEIGHT;//1120*630

    JPanel kLines=null;
    // JPanel infoBar=null;
    String STOCK_NAME="櫃買加權.9065";
    Integer sPrice,hPrice,lPrice,ePrice;
    Integer difPrice,dealNum;
    GridBagConstraints gbc = null;
    public prev(int BOARD_WIDTH,int BOARD_HEIGHT){
        frame = new JFrame("Stock Mario");
        gbc = new GridBagConstraints(); 
        
        frame.setLayout(new GridBagLayout()); 

        this.BOARD_HEIGHT=BOARD_HEIGHT;
        this.BOARD_WIDTH=BOARD_WIDTH;
        
        JPanel infoBar = new JPanel(new GridBagLayout());
        infoBar.setBackground(Color.GRAY);
        infoBar.setSize(915,220);

        Grids grids = new Grids(915, 220, 4, 6);// 60% of total width

        /////////
        JLabel stockName = new JLabel();
        stockName.setText(STOCK_NAME);
        stockName.setForeground(Color.CYAN);

        JLabel date = new JLabel();
        date.setText(LocalDate.now().toString());
        date.setForeground(Color.WHITE);
        System.out.println(LocalDate.now().toString());

        sPrice=12;hPrice=12;lPrice=12;ePrice=12;difPrice=12;dealNum=12;

        JLabel startPrice = new JLabel();
        startPrice.setText("開盤:"+sPrice.toString());
        startPrice.setForeground(setColor(sPrice));
        

        JLabel endPrice = new JLabel();
        endPrice.setText("收盤:"+ePrice.toString());
        endPrice.setForeground(setColor(ePrice));
        

        JLabel highestPrice = new JLabel();
        highestPrice.setText("最高:"+hPrice.toString());
        highestPrice.setForeground(setColor(hPrice));
    
        JLabel lowestPrice = new JLabel();
        lowestPrice.setText("最低:"+lPrice.toString());
        lowestPrice.setForeground(setColor(lPrice));
        

        JLabel priceDiff = new JLabel();
        priceDiff.setForeground(setColor(difPrice));
        priceDiff.setText("漲跌:"+difPrice.toString());
        

        JLabel deal = new JLabel();
        deal.setText("成交量:"+dealNum.toString());
        deal.setForeground(setColor(dealNum));
        
        ///
        gbc.gridx=0;gbc.gridy=0;
        gbc.gridwidth=1;gbc.gridheight=2;
        infoBar.add(stockName,gbc);

        gbc.gridx=1;gbc.gridy=0;
        gbc.gridwidth=1;gbc.gridheight=2;
        infoBar.add(date,gbc);

        gbc.gridx=2;gbc.gridy=0;
        gbc.gridwidth=1;gbc.gridheight=1;
        infoBar.add(startPrice,gbc);

        gbc.gridx=2;gbc.gridy=1;
        gbc.gridwidth=1;gbc.gridheight=1;
        infoBar.add(endPrice,gbc);

        gbc.gridx=3;gbc.gridy=0;
        gbc.gridwidth=1;gbc.gridheight=1;
        infoBar.add(highestPrice,gbc);

        gbc.gridx=3;gbc.gridy=1;
        gbc.gridwidth=1;gbc.gridheight=1;
        infoBar.add(lowestPrice,gbc);
        
        gbc.gridx=4;gbc.gridy=0;
        gbc.gridwidth=1;gbc.gridheight=1;
        infoBar.add(priceDiff,gbc);

        gbc.gridx=4;gbc.gridy=1;
        gbc.gridwidth=1;gbc.gridheight=1;
        infoBar.add(deal,gbc);
        ///

        // gbc.gridx=0;gbc.gridy=0;
        // gbc.gridwidth=1;gbc.gridheight=1;
        frame.add(infoBar,gbc);

        gbc.gridx=0;gbc.gridy=0;
        gbc.gridwidth=1;gbc.gridheight=3;
        frame.add(grids,gbc);

        
        frame.pack();
        frame.setSize(BOARD_WIDTH,BOARD_HEIGHT);    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);

    }
    Color setColor(Integer n){
        if(n>0) return Color.RED;
        if(n<0) return Color.GREEN;
        return Color.white;
    }

    public void show(){
        frame.setVisible(true);    
    }
}
