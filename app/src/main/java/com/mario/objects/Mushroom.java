package com.mario.objects;

import static com.mario.framework.Level.removeItem;

import java.awt.*;

import com.mario.Game;
import com.mario.framework.Texture;

public class Mushroom extends GameObject {

    Texture tex = Game.getTex();
    private float originalY;
    private String type;
    private boolean appeared = false;

//    public Mushroom( float x, float y, String type, String color ){
//        super( x, y, 32, 32, "mushroom", color );
//        defaultSpeed = 2;
//    }

    public Mushroom( float x, float y, String type ){
        super( x, y, 32, 32, "mushroom", "white" );
        setSprites( tex.get( "mushroom-" + type ) );
        defaultSpeed = 2;
        originalY = y;
        this.type = type;
        setVelocityY(-defaultSpeed);
    }

    public void tick() {
        if ( !appeared ) {
            y -= defaultSpeed * 0.3;
            if ( y <= originalY - height ) {
                appeared = true;
                setVelocityX(defaultSpeed);
            }
        } else {
            super.tick();
        }

        if ( x < 0 ) moveRight();
        if ( y >= Game.HEIGHT ) removeItem(this);
    }

    public void render( Graphics g ) {
        if ( appeared || y < originalY - 8 ) super.render(g); // Sprite bleeds outside of block when this is not added
    }

    public void handleCollision( int contactPoint, GameObject neighbor ) {
        if ( !appeared ) return;

        if ( neighbor instanceof Player ) {
            neighbor.handleCollision( ( contactPoint + 2 ) % 4, this );
            return;
        }

        super.handleCollision( contactPoint, neighbor );
        if ( contactPoint == 2 ) {
            moveLeft();
            if ( !(neighbor instanceof Player) ) neighbor.moveRight();
        } else if ( contactPoint == 4 ) {
            moveRight();
            if ( !(neighbor instanceof Player) ) neighbor.moveLeft();
        }
    }
}
