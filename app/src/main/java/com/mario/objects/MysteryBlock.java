package com.mario.objects;

import static com.mario.framework.Level.addItemAtStart;

import com.mario.Game;
import com.mario.framework.Texture;

public class MysteryBlock extends GameObject {

    Texture tex = Game.getTex();
    private float originalY;
    private String style;
    private String hiddenItem;
    private boolean isEmpty = false;

    public MysteryBlock( float x, float y, String style, String hiddenItem ) {
        super( x, y, 32, 32, "block", "white" );
        setSprites( tex.get( "mysteryblock-" + style ) );
        this.style = style;
        originalY = y;
        this.hiddenItem = hiddenItem;
    }

    public void tick() {
        super.tick();
        if ( y >= originalY ) {
            y = originalY;
            velocityY = 0;
        }
    }

    public void handleCollision( int contactPoint, GameObject neighbor ) {
        if ( neighbor instanceof Mushroom ) return;
        super.handleCollision( contactPoint, neighbor );

        if ( contactPoint == 3 && neighbor.getType() == "player" ) {
            if ( !isEmpty ) {
                setVelocityY(-3);
                revealHiddenItem();
                setSprites( tex.get( "block-empty-" + style ) );

                isEmpty = true;
            }
        }
    }

    private void revealHiddenItem(){
        if ( hiddenItem == "coin" ) {
            addItemAtStart( new Coin( x + 10, y - 32 ) );
        } else if ( hiddenItem == "growth-mushroom" ) {
            addItemAtStart( new Mushroom( x, y, "growth") );
        }
    }

}
