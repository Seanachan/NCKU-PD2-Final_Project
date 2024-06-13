package com.mario;
import java.awt.BasicStroke;
import java.awt.CardLayout;
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
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import java.time.LocalDate;

public class Background extends JFrame {
    final int BOARD_WIDTH = 1268,BOARD_HEIGHT=708;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private CardLayout cardLayout;
    static Double startPriceDouble = 0.0, highestPriceDouble, lowestPriceDouble, endPriceDouble, diffPriceDouble;
    static Integer dealNumInteger = 0;
    Double n1, n2, n3, n4, n5, n6, n7, n8, n9;
    static JLabel startPrice, endPrice, highestPrice, lowestPrice, diffPrice, dealAmount;

    Background() {
        cardLayout = new CardLayout();
        contentPane = new JPanel(cardLayout);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.BLACK);
        setContentPane(contentPane);

        setMinimumSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setResizable(false);//not adjustable window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        
        highestPriceDouble = 0.0;
        lowestPriceDouble = 1000000.0;
        diffPriceDouble = 0.0;
        dealNumInteger = 0;
        
        // Add main panel
        JPanel mainPanel = createMainPanel();
        contentPane.add(mainPanel, "MainPanel");
        
        // Add start scene panel
        JPanel startScene = createStartScene();
        contentPane.add(startScene, "StartScene");

        cardLayout.show(contentPane,"StartScene");

        pack();
        setVisible(true);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(null);

        JPanel info = new JPanel();
        info.setBounds(61, 29, 1100, 42);
        info.setAlignmentY(Component.TOP_ALIGNMENT);
        info.setLayout(new BoxLayout(info, BoxLayout.X_AXIS));
        
        JPanel panel_3 = new JPanel();
        info.add(panel_3);
        GridBagLayout gbl_panel_3 = new GridBagLayout();
        gbl_panel_3.columnWidths = new int[]{185, 95, 0, 30, 0};
        gbl_panel_3.rowHeights = new int[]{16, 0, 0, 0, 0, 0};
        gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel_3.setLayout(gbl_panel_3);
        
        JLabel stockName = new JLabel("玩家狀態 0116");
        stockName.setFont(new Font("Avenir", Font.PLAIN, 20));
        stockName.setForeground(Color.CYAN);
        GridBagConstraints gbc_stockName = new GridBagConstraints();
        gbc_stockName.anchor = GridBagConstraints.EAST;
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
        gbl_panel_1.rowHeights = new int[]{22, 16, 0};
        gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        panel_1.setLayout(gbl_panel_1);
        
        highestPrice = new JLabel("最高:" + highestPriceDouble.toString());
        highestPrice.setForeground(Color.RED);
        GridBagConstraints gbc_highestPrice = new GridBagConstraints();
        gbc_highestPrice.anchor = GridBagConstraints.EAST;
        gbc_highestPrice.insets = new Insets(0, 0, 5, 0);
        gbc_highestPrice.gridx = 0;
        gbc_highestPrice.gridy = 0;
        panel_1.add(highestPrice, gbc_highestPrice);
        
        lowestPrice = new JLabel("最低:" + lowestPriceDouble.toString());
        lowestPrice.setForeground(Color.GREEN);
        GridBagConstraints gbc_lowestPrice = new GridBagConstraints();
        gbc_lowestPrice.anchor = GridBagConstraints.SOUTH;
        gbc_lowestPrice.gridx = 0;
        gbc_lowestPrice.gridy = 1;
        panel_1.add(lowestPrice, gbc_lowestPrice);
        
        JPanel panel = new JPanel();
        info.add(panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{30, 0};
        gbl_panel.rowHeights = new int[]{23, 16, 0};
        gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);
        
        startPrice = new JLabel("開盤:");
        startPrice.setForeground(Color.WHITE);
        GridBagConstraints gbc_startPrice = new GridBagConstraints();
        gbc_startPrice.fill = GridBagConstraints.BOTH;
        gbc_startPrice.insets = new Insets(0, 0, 5, 0);
        gbc_startPrice.gridx = 0;
        gbc_startPrice.gridy = 0;
        panel.add(startPrice, gbc_startPrice);

        endPrice = new JLabel("收盤:");
        endPrice.setForeground(Color.WHITE);
        GridBagConstraints gbc_endPrice = new GridBagConstraints();
        gbc_endPrice.fill = GridBagConstraints.BOTH;
        gbc_endPrice.gridx = 0;
        gbc_endPrice.gridy = 1;
        panel.add(endPrice, gbc_endPrice);
        
        JPanel panel_2 = new JPanel();
        info.add(panel_2);
        GridBagLayout gbl_panel_2 = new GridBagLayout();
        gbl_panel_2.columnWidths = new int[]{79, 0};
        gbl_panel_2.rowHeights = new int[]{23, 16, 0};
        gbl_panel_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_panel_2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        panel_2.setLayout(gbl_panel_2);

        diffPrice = new JLabel("漲跌:" + diffPriceDouble.toString());
        diffPrice.setForeground(Color.RED);
        GridBagConstraints gbc_diffPrice = new GridBagConstraints();
        gbc_diffPrice.fill = GridBagConstraints.BOTH;
        gbc_diffPrice.insets = new Insets(0, 0, 5, 0);
        gbc_diffPrice.gridx = 0;
        gbc_diffPrice.gridy = 0;
        panel_2.add(diffPrice, gbc_diffPrice);

        dealAmount = new JLabel("成交量:" + dealNumInteger.toString());
        dealAmount.setForeground(Color.RED);
        GridBagConstraints gbc_dealAmount = new GridBagConstraints();
        gbc_dealAmount.fill = GridBagConstraints.BOTH;
        gbc_dealAmount.gridx = 0;
        gbc_dealAmount.gridy = 1;
        panel_2.add(dealAmount, gbc_dealAmount);
        mainPanel.add(info);
        
        JPanel panel_4 = new JPanel();
        panel_4.setBounds(1180, 59, 75, 615);
        panel_4.setForeground(Color.WHITE);
        panel_4.setBackground(Color.BLACK);
        panel_4.setLayout(new GridLayout(0, 1, 0, 0));
        
        n9 = 220.0; n8 = 320.0; n7 = 420.0; n6 = 520.0; n5 = 620.0; n4 = 720.0; n3 = 820.0; n2 = 920.0; n1 = 1020.0;
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
        mainPanel.add(panel_4);
        
        Grids grids = new Grids(915, 220, 8, 6);
        grids.setBounds(61, 83, 1100, 560);
        grids.setBackground(new Color(0, 0, 0, 255));
        grids.setLayout(new BoxLayout(grids, BoxLayout.X_AXIS));
        
        KLineGraph KGraph = new KLineGraph();
        grids.add(KGraph);
        KGraph.setOpaque(false);
        KGraph.setBackground(new Color(0, 0, 0, 255));
        mainPanel.add(grids);
        
        return mainPanel;
    }

    private JPanel createStartScene() {
        // Load the image and create a JLabel to hold it
        ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("start_scene.png"));
        JLabel picLabel = new JLabel(img);
        picLabel.setBounds(37, 6, img.getIconWidth(), img.getIconHeight());

        // Create the Start button
        JButton startButton = new JButton("Start");
        startButton.setForeground(Color.BLACK);
        startButton.setBackground(Color.LIGHT_GRAY);
        startButton.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        startButton.addActionListener(e -> {
            cardLayout.show(contentPane, "MainPanel");
            Grids.setShouldDraw(true);
        });

        startButton.setBounds(571, 423, 107, 45); // Set button bounds

        // Create a layered pane to overlay components
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(img.getIconWidth(), img.getIconHeight()));
        layeredPane.add(picLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(startButton, JLayeredPane.PALETTE_LAYER);

        // Create a panel to center the layered pane
        JPanel startScene = new JPanel(new GridBagLayout());
        startScene.setBackground(Color.BLACK);
        startScene.add(layeredPane);

        return startScene;
    
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

    public void showStartScene() {
        cardLayout.show(contentPane, "StartScene");
    }
}

class Grids extends JPanel {
    private static final long serialVersionUID = 1L;
    int width, height;
    int rows, cols;
    int BOARD_WIDTH = 1268, BOARD_HEIGHT = 708;
    static boolean shouldDraw = false; // Add this flag
    Grids(int w, int h, int r, int c) {
        this.width = w;
        this.height = h;
        this.rows = r;
        this.cols = c;
    }
    public static void setShouldDraw(boolean b) {
        shouldDraw = b;
        // repaint(); // Trigger repaint when the flag changes
    }
    @Override
    protected void paintComponent(Graphics g) {
        //when initiated, paintComponent is called
        if(!shouldDraw) {repaint();return;}
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

