package com.mario.objects;

import java.awt.*;

import com.mario.Game;

public class PipeHead extends GameObject {

//    public PipeHead( float x, float y, boolean isVertical, String color ) {
//        super( x, y, isVertical ? 64 : 32, isVertical ? 32 : 64, "pipe-head", color );
//    }

    public PipeHead( float x, float y, boolean isVertical ) {
        super( x, y, isVertical ? 64 : 32, isVertical ? 32 : 64, "pipe-head", "white" );
        setSprites( Game.getTex().get( "pipe-head-" + ( isVertical ? "v" : "h" ) ) );
    }

    public Rectangle getBounds(){ return new Rectangle( (int)x + 4, (int)y, (int)width - 8, (int)height ); }

}
