package com.tngo.mario.objects;

import static com.tngo.mario.framework.Level.itemIsVisible;
import static com.tngo.mario.framework.Level.removeItem;

import java.awt.image.BufferedImage;

import com.tngo.mario.Game;

public class Enemy extends GameObject {

//    public Enemy( float x, float y, float width, float height, String color ) {
//        super( x, y, width, height, "enemy", color );
//        defaultSpeed = 1;
//    }

    public Enemy( float x, float y, float width, float height, BufferedImage... imgs ) {
        super( x, y, width, height, "enemy", imgs );
        defaultSpeed = 1;
    }

    public void tick() {
        super.tick();

        if ( velocityX == 0 && itemIsVisible(this) ) moveLeft();
        if ( x < 0 ) moveRight();
        if ( y >= Game.HEIGHT ) removeItem(this);
    }

    public void handleCollision( int contactPoint, GameObject neighbor ) {

        if ( neighbor instanceof Player ) {
            neighbor.handleCollision( ( contactPoint + 2 ) % 4, this );
            return;
        }

        super.handleCollision(contactPoint, neighbor);
        if ( contactPoint == 2 ) {
            moveLeft();
            if ( !(neighbor instanceof Player) ) neighbor.moveRight();
        } else if ( contactPoint == 4 ) {
            moveRight();
            if ( !(neighbor instanceof Player) ) neighbor.moveLeft();
        }
    }

    public void die() {
        // Will differ depending on enemy type
    }

}
