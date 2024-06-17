package com.doge;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;


public class FlappyBird extends JPanel implements ActionListener,KeyListener{
    int BOARD_WIDTH=1268,BOARD_HEIGHT=708;
    //Images
    Image backgroundImg,birdImg,topPipeImg,bottomImg,bottomPipeImg;
    //Bird
    int birdX = BOARD_WIDTH/4,birdY = 0;
    final int birdWidth = 38,birdHeight=38;

    boolean gameOver = false;
    int gameTime = 60;
    int score=0;

    class Bird{
        int x=birdX;
        int y=birdY;
        int width=birdWidth,height = birdHeight;
        Image img;
        Bird(Image img){
            this.img=img;
        }
    }
    // Pipes
    int pipeX = BOARD_WIDTH, pipeY=0;
    int pipeWidth=64;// scaled by 1/6
    int pipeHeight=512;

    class Pipe{
        int x=pipeX,y=pipeY;
        int width=pipeWidth,height=pipeHeight;
        Image img;
        boolean passed=false;

        Pipe(Image img){
            this.img=img;
        }
    }

    //game logic
    Bird bird;
    int velocityX = -4;//moves pipe to left
    int velocityY = -4;//move bird up/down
    int gravity = 1;

    List<Pipe> pipes;
    Random random = new Random();

    static Timer gameLoop;
    static Timer placesPipesTimer;

    FlappyBird(){
        setPreferredSize(new Dimension(BOARD_WIDTH,BOARD_HEIGHT));
        setFocusable(true);//JPanel is the one that takes keyEvent
        setBackground(new Color(12,12,12,20));
        addKeyListener(this);

        //load images
        try {
            if(OsUtils.isWindows()){
                backgroundImg = ImageIO.read(new FileInputStream("src/res/flappybirdbg.png"));
                birdImg = ImageIO.read(new FileInputStream("src/res/d_coin.png"));
                bottomImg = ImageIO.read(new FileInputStream("src/res/chives.png"));
                topPipeImg = ImageIO.read(new FileInputStream("src/res/chives_reversed.png"));
            }
            else{
                backgroundImg = ImageIO.read(new FileInputStream("src/res/flappybirdbg.png"));
                birdImg = ImageIO.read(new FileInputStream("src/res/d_coin.png"));
                bottomImg = ImageIO.read(new FileInputStream("src/res/chives.png"));
                topPipeImg = ImageIO.read(new FileInputStream("src/res/chives_reversed.png"));
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(bottomImg!=null);

        // Bird
        bird = new Bird(birdImg);
        pipes = new ArrayList<>();
        //places pipes timer
        placesPipesTimer = new Timer(1000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e ){
                placePipes();
                KLineGraph.setKLine(bird.y);
                KLineGraph.draw(getGraphics());
                gameTime--;
                
            }
        } );
        gameLoop=new Timer(1000/90,this);

        new Background(this);
    }
    public static void startAllTimer(){
        placesPipesTimer.start();
        gameLoop.start();
        KLineGraph.gameLoop.start();
    }
    public void placePipes(){
        Random rand = new Random();
        int randomPipeY = (int) (pipeY-pipeHeight/4-Math.random()*(pipeHeight-pipeHeight/2.5));
        Pipe topPipe = new Pipe(topPipeImg);

        topPipe.y=randomPipeY;
        pipes.add(topPipe); 
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);          
        draw(g);
    }
    public void draw(Graphics g){
        //background
        g.drawImage(birdImg, bird.x, bird.y, bird.width, bird.height, null);
        for(Pipe pipe : pipes){
            g.drawImage(pipe.img, pipe.x
            , pipe.y, pipe.width, pipe.height, null);
        }
        g.setColor(Color.white);
        
        if(gameTime<=0) gameOver=true;
        if(gameOver){
            g.setFont(new Font("Arial", Font.PLAIN,50));
            g.drawString("Game Over!",this.getWidth()/2-170,this.getHeight()/2-30);
            g.drawString("   Score: "+ score,this.getWidth()/2-170,this.getHeight()/2+30);
            DogeGame.score=(int) Math.round(score);
            
            gameLoop.stop();
            placesPipesTimer.stop();
            KLineGraph.gameLoop.stop();
            
            try {
                repaint();    
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            DogeGame.isComplete=true;
            
        }else{
            g.setFont(new Font("Arial",Font.PLAIN,20));
            g.drawString("Time: "+gameTime,this.getWidth()/10,this.getWidth()/10);
            g.drawString("Score: "+(int) Math.round(((double) (60-gameTime)/60)*350),this.getWidth()/10,this.getWidth()/10+30);
            score=(int) Math.round(((double) (60-gameTime)/60)*350);

            if(bird.y>BOARD_HEIGHT/2){
                score-=1;
                if(score <= 1) score = 1;
            }
        }

    }

    public void move(){
        //bird
        velocityY += gravity;
        bird.y += velocityY;
        if(bird.y<=0){
            bird.y=0;
        }
        else if(bird.y>this.getHeight()+40){
            gameOver=true;
        }
        
        for(Pipe pipe: pipes){
            pipe.x+=velocityX;

            if(collision(bird, pipe)){
                gameOver=true;
            }
        }
    }

    public boolean collision(Bird bird,Pipe pipe){
        return bird.x<pipe.x &&
            bird.x+bird.width> pipe.x&&
            bird.y<pipe.y+pipe.height&&
            bird.y+bird.height>pipe.y;

	}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(gameOver){
            placesPipesTimer.stop();
            gameLoop.stop();
            KLineGraph.gameLoop.stop();
        }
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            velocityY = -9;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
