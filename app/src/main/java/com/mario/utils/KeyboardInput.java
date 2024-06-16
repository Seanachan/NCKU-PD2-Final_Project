package com.mario.utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.mario.objects.Player;

public class KeyboardInput extends KeyAdapter {

    Player player;

    public KeyboardInput( Player player ) {
        this.player = player;
    }

    public void keyPressed( KeyEvent e ) {
        int key = e.getKeyCode();

        if ( key == KeyEvent.VK_ESCAPE ) { System.exit(1); }
        if ( key == KeyEvent.VK_RIGHT ) player.moveRight();
        if ( key == KeyEvent.VK_LEFT ) player.moveLeft();
        if ( key == KeyEvent.VK_SPACE ) player.jump();
//        if ( key == KeyEvent.VK_DOWN ) player.setVelocityY(5);
    }

    public void keyReleased( KeyEvent e ) {
        int key = e.getKeyCode();

        if ( key == KeyEvent.VK_RIGHT ) player.stopMoveRight();
        if ( key == KeyEvent.VK_LEFT ) player.stopMoveLeft();
//        if ( key == KeyEvent.VK_SPACE ) player.setVelocityY(0);
//        if ( key == KeyEvent.VK_DOWN ) player.setVelocityY(0);
    }

}
