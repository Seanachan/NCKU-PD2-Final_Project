package com.mario.object;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Pipe extends GameObject{
    private boolean enterable; // to determine whether I can enter the pipe or not
    public Pipe(int x ,int y , int width , int height, int scale , boolean enterable){
        super(x,y,ObjectId.Pipe, width , height , scale);
        this.enterable = enterable;
    }
    @Override
    public void tick() {
        
    }
    @Override
    public void render(Graphics g) {
        
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)getX() , (int)getY() , (int) getWidth() , (int) getHeight());
    } 
}
