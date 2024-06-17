package com.doge;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.time.LocalDate;
import javax.swing.Timer;

public class Background {
    final int BOARD_WIDTH = 1268,BOARD_HEIGHT=708;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private CardLayout cardLayout;
    static Double startPriceDouble = 0.0, highestPriceDouble, lowestPriceDouble, endPriceDouble, diffPriceDouble;
    static Integer dealNumInteger = 0;
    Double n1, n2, n3, n4, n5, n6, n7, n8, n9;
    static JLabel startPrice, endPrice, highestPrice, lowestPrice, diffPrice, dealAmount;
    public Timer updateLineTimer;
    static JPanel bgPanel;

    public Background(JPanel flappyBirdPane) {
        // this.updateLineTimer=updateLineTimer;
        bgPanel=flappyBirdPane;
        bgPanel.setBackground(new Color(12,1,12,0));
        cardLayout = new CardLayout();
        contentPane = new JPanel(cardLayout);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(0,0,0,0));
        contentPane.setMinimumSize(new Dimension(BOARD_WIDTH,BOARD_HEIGHT));
        contentPane.setPreferredSize(new Dimension(BOARD_WIDTH,BOARD_HEIGHT));
        bgPanel.add(contentPane);
        // setContentPane(contentPane);

        bgPanel.setMinimumSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        bgPanel.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        // setResizable(false);//not adjustable window size
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bgPanel.setBounds(100, 100, 450, 300);
        
        highestPriceDouble = 0.0;
        lowestPriceDouble = 1000000.0;
        diffPriceDouble = 0.0;
        dealNumInteger = 0;
        
        // Add main panel
        JPanel mainPanel = createMainPanel();
        contentPane.add(mainPanel, "MainPanel");
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(new Color(0,0,0,0));
        mainPanel.setLayout(null);

        JPanel info = new JPanel();
        info.setBounds(61, 29, 1100, 42);
        info.setAlignmentY(Component.TOP_ALIGNMENT);
        info.setLayout(new BoxLayout(info, BoxLayout.X_AXIS));
        info.setBackground(new Color(0,0,0,255));
        
        JPanel nameAndDate = new JPanel();
        nameAndDate.setBackground(Color.GRAY);
        info.add(nameAndDate);
        GridBagLayout topGridBagPanel = new GridBagLayout();
        topGridBagPanel.columnWidths = new int[]{185, 95, 0, 30, 0};
        topGridBagPanel.rowHeights = new int[]{16, 0, 0, 0, 0, 0};
        topGridBagPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        topGridBagPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        nameAndDate.setLayout(topGridBagPanel);
        
        JLabel stockName = new JLabel("玩家狀態 0116");
        stockName.setFont(new Font("Avenir", Font.PLAIN, 20));
        stockName.setForeground(Color.CYAN);
        GridBagConstraints gbc_stockName = new GridBagConstraints();
        gbc_stockName.anchor = GridBagConstraints.EAST;
        gbc_stockName.insets = new Insets(0, 0, 5, 5);
        gbc_stockName.gridx = 0;
        gbc_stockName.gridy = 2;
        nameAndDate.add(stockName, gbc_stockName);
        stockName.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        JLabel date = new JLabel(LocalDate.now().toString());
        date.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        date.setForeground(Color.WHITE);
        GridBagConstraints gbc_date = new GridBagConstraints();
        gbc_date.fill = GridBagConstraints.BOTH;
        gbc_date.insets = new Insets(0, 0, 5, 5);
        gbc_date.gridx = 2;
        gbc_date.gridy = 2;
        nameAndDate.add(date, gbc_date);
        
        JPanel statsGridBagPanel = new JPanel();
        statsGridBagPanel.setBackground(Color.GRAY);
        info.add(statsGridBagPanel);
        GridBagLayout statsGridBag = new GridBagLayout();
        statsGridBag.columnWidths = new int[]{30, 0};
        statsGridBag.rowHeights = new int[]{22, 16, 0};
        statsGridBag.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        statsGridBag.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        statsGridBagPanel.setLayout(statsGridBag);
        
        highestPrice = new JLabel("最高:" + highestPriceDouble.toString());
        highestPrice.setForeground(Color.RED);
        GridBagConstraints gbc_highestPrice = new GridBagConstraints();
        gbc_highestPrice.anchor = GridBagConstraints.EAST;
        gbc_highestPrice.insets = new Insets(0, 0, 5, 0);
        gbc_highestPrice.gridx = 0;
        gbc_highestPrice.gridy = 0;
        statsGridBagPanel.add(highestPrice, gbc_highestPrice);
        
        lowestPrice = new JLabel("最低:" + lowestPriceDouble.toString());
        lowestPrice.setForeground(Color.GREEN);
        GridBagConstraints gbc_lowestPrice = new GridBagConstraints();
        gbc_lowestPrice.anchor = GridBagConstraints.SOUTH;
        gbc_lowestPrice.gridx = 0;
        gbc_lowestPrice.gridy = 1;
        statsGridBagPanel.add(lowestPrice, gbc_lowestPrice);
        
        JPanel startAndEnd = new JPanel();
        startAndEnd.setBackground(Color.GRAY);
        info.add(startAndEnd);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{30, 0};
        gbl_panel.rowHeights = new int[]{23, 16, 0};
        gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        startAndEnd.setLayout(gbl_panel);
        
        startPrice = new JLabel("開盤:");
        startPrice.setForeground(Color.WHITE);
        GridBagConstraints gbc_startPrice = new GridBagConstraints();
        gbc_startPrice.fill = GridBagConstraints.BOTH;
        gbc_startPrice.insets = new Insets(0, 0, 5, 0);
        gbc_startPrice.gridx = 0;
        gbc_startPrice.gridy = 0;
        startAndEnd.add(startPrice, gbc_startPrice);

        endPrice = new JLabel("收盤:");
        endPrice.setForeground(Color.WHITE);
        GridBagConstraints gbc_endPrice = new GridBagConstraints();
        gbc_endPrice.fill = GridBagConstraints.BOTH;
        gbc_endPrice.gridx = 0;
        gbc_endPrice.gridy = 1;
        startAndEnd.add(endPrice, gbc_endPrice);
        
        JPanel diffAndDeal = new JPanel();
        diffAndDeal.setBackground(Color.GRAY);
        info.add(diffAndDeal);
        GridBagLayout gbl_panel_2 = new GridBagLayout();
        gbl_panel_2.columnWidths = new int[]{79, 0};
        gbl_panel_2.rowHeights = new int[]{23, 16, 0};
        gbl_panel_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_panel_2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        diffAndDeal.setLayout(gbl_panel_2);

        diffPrice = new JLabel("漲跌:" + diffPriceDouble.toString());
        diffPrice.setForeground(Color.RED);
        GridBagConstraints gbcDiffPrice = new GridBagConstraints();
        gbcDiffPrice.fill = GridBagConstraints.BOTH;
        gbcDiffPrice.insets = new Insets(0, 0, 5, 0);
        gbcDiffPrice.gridx = 0;
        gbcDiffPrice.gridy = 0;
        diffAndDeal.add(diffPrice, gbcDiffPrice);

        dealAmount = new JLabel("成交量:" + dealNumInteger.toString());
        dealAmount.setForeground(Color.RED);
        GridBagConstraints gbcDealAmount = new GridBagConstraints();
        gbcDealAmount.fill = GridBagConstraints.BOTH;
        gbcDealAmount.gridx = 0;
        gbcDealAmount.gridy = 1;
        diffAndDeal.add(dealAmount, gbcDealAmount);
        mainPanel.add(info);
        
        JPanel numRowPanel = new JPanel();
        numRowPanel.setBounds(1180, 59, 75, 615);
        numRowPanel.setForeground(Color.WHITE);
        numRowPanel.setBackground(new Color(0,0,0,0));
        numRowPanel.setLayout(new GridLayout(0, 1, 0, 0));
        
        n9 = 90.0; n8 = 145.0; n7 = 200.0; n6 = 255.0; n5 = 310.0; n4 = 365.0; n3 = 420.0; n2 = 475.0; n1 = 530.0;
        JLabel num1 = new JLabel(String.format("%.2f", n1));
        num1.setForeground(Color.WHITE);
        num1.setAlignmentY(Component.TOP_ALIGNMENT);
        num1.setAlignmentX(Component.CENTER_ALIGNMENT);
        numRowPanel.add(num1);
        
        JLabel num2 = new JLabel(String.format("%.2f", n2));
        num2.setForeground(Color.WHITE);
        num2.setAlignmentY(Component.TOP_ALIGNMENT);
        numRowPanel.add(num2);
        
        JLabel num3 = new JLabel(String.format("%.2f", n3));
        num3.setForeground(Color.WHITE);
        numRowPanel.add(num3);
        
        JLabel num4 = new JLabel(String.format("%.2f", n4));
        num4.setForeground(Color.WHITE);
        numRowPanel.add(num4);
        
        JLabel num5 = new JLabel(String.format("%.2f", n5));
        num5.setForeground(Color.WHITE);
        numRowPanel.add(num5);
        
        JLabel num6 = new JLabel(String.format("%.2f", n6));
        num6.setForeground(Color.WHITE);
        numRowPanel.add(num6);
        
        JLabel num7 = new JLabel(String.format("%.2f", n7));
        num7.setForeground(Color.WHITE);
        numRowPanel.add(num7);
        
        JLabel num8 = new JLabel(String.format("%.2f", n8));
        num8.setForeground(Color.WHITE);
        numRowPanel.add(num8);
        
        JLabel num9 = new JLabel(String.format("%.2f", n9));
        num9.setForeground(Color.WHITE);
        numRowPanel.add(num9);
        mainPanel.add(numRowPanel);
        
        Grids grids = new Grids(915, 220, 8, 6);
        grids.setBounds(61, 83, 1100, 560);
        grids.setBackground(new Color(0,0,0,0));
        grids.setLayout(new BoxLayout(grids, BoxLayout.X_AXIS));
        
        KLineGraph KGraph = new KLineGraph();
        grids.add(KGraph);
        KGraph.setOpaque(false);
        KGraph.setBackground(new Color(0,0,0,0));
        mainPanel.add(grids);
        
        return mainPanel;
    }

    public static void updateInfo(double dif, int amount, double h, double l, Double s, Double e) {
        if (startPriceDouble == 0.0) {
            startPriceDouble = s;
            endPriceDouble = e;
            startPrice.setText("開盤: " + String.format("%.2f", s));
            endPrice.setText("收盤: " + String.format("%.2f", e));
        }

        if (dif == 0) diffPrice.setForeground(Color.WHITE);
        if (dif > 0) diffPrice.setForeground(Color.GREEN);
        else diffPrice.setForeground(Color.RED);
        diffPriceDouble = Math.abs(dif);
        diffPrice.setText("漲跌:" + String.format("%.2f", diffPriceDouble));

        if (dealNumInteger == amount) dealAmount.setForeground(Color.WHITE);
        if (dealNumInteger > amount) dealAmount.setForeground(Color.GREEN);
        else dealAmount.setForeground(Color.RED);
        dealNumInteger = amount;
        dealAmount.setText("成交量:" + String.format("%d", dealNumInteger));

        highestPriceDouble = Math.max(h, highestPriceDouble);
        highestPrice.setText("最高:" + String.format("%.2f", highestPriceDouble));
        
        if (l != 0) lowestPriceDouble = Math.min(l, lowestPriceDouble);
        lowestPrice.setText("最低:" + String.format("%.2f", lowestPriceDouble));
    }

    public void showMainPanel() {
        cardLayout.show(contentPane, "MainPanel");
    }

}

