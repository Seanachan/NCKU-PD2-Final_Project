package com.mario.objects;

import static com.mario.framework.Level.removeItem;

import com.mario.Game;
import com.mario.framework.Texture;

public class BrickPiece extends CanvasItem {

    Texture tex = Game.getTex();
    private float velocityX, velocityY;
    protected final float gravity = 0.7f;

    public BrickPiece( float x, float y, float velocityX, float velocityY, String style ){
        super(x, y, 16, 16, "white");
        setSprites( tex.get( "brickpiece-" + style ) );
        setAnimationSpeed(3);
        this.velocityY = velocityY;
        this.velocityX = velocityX;
    }

    public void tick() {
        super.tick(); // run animation

        x += velocityX;
        y += velocityY;
        velocityY += gravity;
        if ( y >= Game.HEIGHT ) removeItem(this);
    }

    public void handleCollision( int contactPoint, GameObject neighbor ) {
        // Do nothing
    }


}
