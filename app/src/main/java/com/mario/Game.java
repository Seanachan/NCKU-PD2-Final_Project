package com.mario;

import java.awt.*;
import java.awt.image.BufferStrategy;

import com.doge.OsUtils;
import com.mario.framework.Level;
import com.mario.framework.Texture;
import com.mario.utils.Window;

public class Game extends Canvas implements Runnable {

    private volatile boolean running = false;
    private Thread thread;

    public static int WIDTH, HEIGHT;
    Level currentLevel;
    static Texture tex;

    public synchronized void start() {
        if ( running && thread.isInterrupted()) return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }
    public void stop(int scoreCount,boolean win) {
        running=false;
        Window.shouldShowScore=true;
        // System.out.println("\n\n"+Thread.currentThread()==null);
        // System.out.println("\n\n"+Thread.currentThread());
        Window.closeWindow(scoreCount,win);
        
        Thread.currentThread().stop();    
    }

    private void init() {
        WIDTH = getWidth();
        HEIGHT = getHeight();

        tex = new Texture();

        if(OsUtils.isWindows()){
            currentLevel = new Level( this, "src/res/level1.png", "black" );
        }else{
            currentLevel = new Level( this, "src/res/level1.png", "black" );
        }
        
    }

    public void run() {
        init();
        this.requestFocus();

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while ( running && !Thread.currentThread().isInterrupted()) {
            long now = System.nanoTime();
            delta += ( now - lastTime ) / ns;
            lastTime = now;
            while ( delta >= 1 ) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if ( System.currentTimeMillis() - timer > 1000 ) {
                timer += 1000;
                //System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    private void tick() {
        currentLevel.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if ( bs == null ){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        
        // Draw here
        currentLevel.render(g);

        g.dispose();
        bs.show();
    }

    public static Texture getTex(){
        return tex;
    }
    public boolean getRunning(){
        return running;
    }
    public void setRunning(boolean running){
        this.running = running;
    }

    public static void main(String[] args) {
        Window marioGame = new Window(1268, 708, "Super Mario Game Prototype", new Game());

        while (!Window.isComplete) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("jumped out while loop, score: " + Window.score);
        marioGame.closeBGM();
        
    }
}
