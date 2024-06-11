package com.mario;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.mario.object.Player;
import com.mario.object.Block;
import com.mario.object.Handler;
import com.mario.object.KeyInput;

public class Game extends Canvas implements Runnable {
    //GAME CONSTANT
    private static final int MILLIS_PER_SECOND = 1000; // 
    private static final int NANOS_PER_SEC = 1000000000;
    private static final double NUM_TICKS  = 60.0;  // number of ticks (update)
    private static final String NAME = "Super Mario Bros";

    private static final int WINDOW_WIDTH = 960 ;
    private static final int WINDOW_HEIGHT = 720;
    //GAME VARIABLE 
    private boolean running;  //hold the game to find whether the game is start or not
    // GAME COMPONENT
    private Thread thread; //thread is a runnable object when we called start method we will also start this object to execute runnable
    private Handler handler;
    public static void main(String args[]){
        new Game(); 
    }
    public Game(){
        initialize();
    }
    private void initialize(){
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        //temp 
        handler.setPlayer(new Player(32, 32, 1, handler));
        for(int i=0 ; i< 20 ; i++){
            handler.addObj(new Block(i*32, 32*10, 32, 32, 1));
        }
        for(int i=0 ; i<30 ; i++){
            handler.addObj(new Block(i*32 ,32*15,2,32,1));
        }
        // new Windows(WINDOW_WIDTH , WINDOW_HEIGHT , NAME, this);

        start();
    }
    private synchronized void start(){
        thread = new Thread(this);
        thread.start(); //it will call the "run" method
        running = true;
    }
    private synchronized void stop(){
        try {
            thread.join(); // we need to use try catch to  execute this code 讓執行緒的執行結果整合起來
            running = false;
        } catch (InterruptedException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    @Override
    public void run(){  //it contains in the Runnable abstract method 
        long lastTime = System.nanoTime();
        double amountOfTicks = NUM_TICKS;
        double ns = NANOS_PER_SEC / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        int updates =0;

        while(running){
            long now = System.nanoTime();
            delta +=(now-lastTime)/ns;
            lastTime = now;

            while(delta >= 1){
                tick(); // update everything about the game state
                updates++;
                delta --;
            }
            if(running){
                render(); //control the redering (everything shows on the screen)
                frames ++;
            }
            
            if(System.currentTimeMillis() - timer > MILLIS_PER_SECOND){
                timer += MILLIS_PER_SECOND;
                System.out.println("FPS: "+frames + "TPS: "+ updates);
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){ //用來更新當前狀態(player)
        handler.tick();
    }
    private void render(){  // 也是更新用的 主要是更新螢幕show 出來的畫面
        BufferStrategy buf = this.getBufferStrategy();
        if(buf == null){
            this.createBufferStrategy(3);
            return ;
        }

        //draw graphics
        Graphics g = buf.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        handler.render(g);
        //clean for next frame
        g.dispose();
        buf.show();
    }

    public static int getWindowHeight(){
        return WINDOW_HEIGHT;
    }
    public static int getWindowWidth(){
        return WINDOW_WIDTH;
    }
}
