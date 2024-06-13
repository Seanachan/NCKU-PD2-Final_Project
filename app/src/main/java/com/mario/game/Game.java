package com.mario.game;

import java.awt.*;
import java.awt.image.BufferStrategy;

import com.mario.game.framework.Level;
import com.mario.game.framework.Texture;
import com.mario.game.utils.Window;

public class Game extends Canvas implements Runnable {

    private boolean running = false;
    private Thread thread;

    public static int WIDTH, HEIGHT;
    Level currentLevel;
    static Texture tex;
    
    public synchronized void start() {
        if ( running ) return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private void init() {
        WIDTH = getWidth();
        HEIGHT = getHeight();

        tex = new Texture();

        currentLevel = new Level( this, "src/res/level1.png", "skyblue" );
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
        while ( running ) {
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
                System.out.println("FPS: " + frames + " TICKS: " + updates);
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

        currentLevel.render(g);

        g.dispose();
        bs.show();
    }

    public static Texture getTex(){
        return tex;
    }
}