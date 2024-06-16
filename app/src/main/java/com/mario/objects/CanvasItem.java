package com.mario.objects;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.mario.framework.Animation;

public class CanvasItem {

    protected float x, y;
    protected float width, height;
    protected Animation sprites;

    public CanvasItem( float x, float y, float width, float height, String color ) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        sprites = new Animation(color);
    }

    public CanvasItem( float x, float y, float width, float height, BufferedImage... imgs ) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        sprites = new Animation(imgs);
    }

    public float getX() { return x; }
    public float getY() { return y; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public void setX( float x ) { this.x = x; }
    public void setY( float y ) { this.y = y; }
    public void setSprites( String color ) { sprites = new Animation(color); }
    public void setSprites( BufferedImage... imgs ) { sprites = new Animation(imgs); }
    public void setAnimationSpeed( int speed ) { sprites.setAnimationSpeed(speed); }

    public void tick() {
        sprites.runAnimation();
    }

    public void render( Graphics g ) {
        sprites.drawAnimation( g, (int)x, (int)y, (int)width, (int)height );
    }

}
