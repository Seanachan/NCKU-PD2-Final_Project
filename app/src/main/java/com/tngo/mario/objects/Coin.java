package com.tngo.mario.objects;

import static com.tngo.mario.framework.Level.removeItem;

import com.tngo.mario.Game;
import com.tngo.mario.framework.Texture;

public class Coin extends CanvasItem {

    Texture tex = Game.getTex();
    private float originalY;
    private float velocityY;
    protected final float gravity = 0.5f;

    public Coin( float x, float y ) {
        super( x, y, 12, 32, "white" );
        setSprites( tex.get( "jumpingcoin" ) );
        setAnimationSpeed(3);
        originalY = y;
        velocityY = -8;
    }

    public void tick() {
        super.tick(); // run animation

        y += velocityY;
        velocityY += gravity;
        if ( y >= originalY ) removeItem(this);
    }

}
