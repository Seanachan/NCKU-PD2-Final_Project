package com.mario.utils;

import com.mario.Game;
import com.mario.objects.Player;

public class Camera {

    private float x = 0, y = 0;
    private float minX, maxX;
    private Player player;

    public Camera( float minX, float maxX, Player player ){
        this.minX = minX;
        this.maxX = maxX - (float) Game.WIDTH;
        this.player = player;
    }

    public void tick() {
        x = Math.min( minX, (float) Game.WIDTH/2 - player.getX() - player.getWidth() );
        x = Math.max( x, -maxX );
    }

    public float getX() { return x; }
    public float getY() { return y; }

    public void setX( float x ) { this.x = Math.min(minX, x); }
    public void setY( float y ) { this.y = y; }

}
