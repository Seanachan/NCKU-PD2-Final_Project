package com.mario.object;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends GameObject{
    public Block(int x, int y, int width, int height, int scale){
        //calling constructor in GameObject
        super(x, y , "Block" , width , height , scale);
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
       
    }

    @Override
    public Rectangle getBounds() {
       return new Rectangle((int) getX() , (int) getY() , (int)getWidth() , (int) getHeight());
    }
}
