package com.mario.object;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//A key event listener takes action when certain event take place(key press , key release)
public class KeyInput extends KeyAdapter{
    private boolean[] keyDown = new boolean[4];
    private Handler handler;

    public KeyInput(Handler handler){
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }

        if(key == KeyEvent.VK_W){ // up
            if(handler.getPlayer().hasJumped()){
                handler.getPlayer().setVelY(-15);
                keyDown[0] = true;
                handler.getPlayer().setJumped(true);
            }
        }
        if(key == KeyEvent.VK_A){ // left
            handler.getPlayer().setVelX(-8);
            keyDown[1] = true;
        }
        if(key == KeyEvent.VK_D){ //right
            handler.getPlayer().setVelX(8);
            keyDown[2] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W){
            keyDown[0] = false;
        }
        if(key == KeyEvent.VK_A){
            keyDown[1] = false;
        }
        if(key == KeyEvent.VK_D){
            keyDown[2] = false;
        }
        if(!keyDown[1] && !keyDown[2]){
            handler.getPlayer().setVelX(0);
        }
    }
}
