package com.mario.objects;

import static com.mario.framework.Level.addItem;
import static com.mario.framework.Level.removeItem;

import com.mario.Game;
import com.mario.framework.Texture;

public class BrickBlock extends GameObject {

    Texture tex = Game.getTex();
    private float originalY;
    private int numberOfCoins;
    private String style;

    public BrickBlock( float x, float y, String style ) {
        super(x, y, 32, 32, "block", "white");
        setSprites( tex.get( "brick-" + style ) );
        originalY = y;
        this.style = style;
        numberOfCoins = -1;
    }

    public BrickBlock( float x, float y, String style, int numberOfCoins ) {
        super(x, y, 32, 32, "block", "white");
        setSprites( tex.get( "brick-" + style ) );
        this.style = style;
        originalY = y;
        if ( numberOfCoins > 0 ) {
            this.numberOfCoins = numberOfCoins;
        } else {
            this.numberOfCoins = -1;
        }
    }

    public void tick() {
        super.tick();
        if ( y >= originalY ) {
            y = originalY;
            velocityY = 0;
        }
    }

    public void handleCollision( int contactPoint, GameObject neighbor ) {
        super.handleCollision( contactPoint, neighbor );

        if ( contactPoint == 3 && neighbor.getType() == "player" ) {
            if ( numberOfCoins != 0 ) setVelocityY(-3);

            if ( numberOfCoins > 0 ) {
                numberOfCoins -= 1;
                addItem( new Coin( x + 10, y - 32 ) );
                if ( numberOfCoins == 0 ) {
                    setSprites( tex.get( "block-empty-" + style ) );
                }
            } else if ( numberOfCoins == -1 ) {
                if ( ((Player) neighbor).canDestroyBrick() ) destroyBlock();
            }
        }
    }

    public void destroyBlock() {
        removeItem(this);
        addItem( new BrickPiece( x, y, -2, -13, "normal" ) );
        addItem( new BrickPiece( x + width - 16, y, 2, -13, "normal" ) );
        addItem( new BrickPiece( x, y + height - 16, -2, -8, "normal" ) );
        addItem( new BrickPiece( x + width - 16, y + height - 16, 2, -8, "normal" ) );
    }

}
