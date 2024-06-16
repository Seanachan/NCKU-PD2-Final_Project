package com.mario.objects;

import static com.mario.framework.Level.removeItem;

import com.mario.Game;
import com.mario.framework.Texture;
import com.mario.objects.Player;

public class Coin extends CanvasItem {

    Texture tex = Game.getTex();
    private float originalY;
    private float velocityY;
    protected final float gravity = 0.5f;
    
    public Coin( float x, float y ) {
        super( x, y, 12, 32, "white" );
        setSprites( tex.get( "jumpingcoin" ) );
        setAnimationSpeed(3);
        Player.setScoreCount(10);
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
