package com.mario.object;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.mario.object.ObjectId;

public class Block extends GameObject{
    public Block(int x, int y, int width, int height, int scale){
        
        super(x, y , ObjectId.Block , width , height , scale);
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tick'");
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }

    @Override
    public Rectangle getBounds() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBounds'");
    }
}
