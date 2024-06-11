package com.mario.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.mario.object.Handler;

public class Player extends GameObject{
    private static final float WIDTH =16;
    private static final float HEIGHT = 32;

    private Handler handler;
    private boolean jumped = false;


    public Player(float x ,float y , int scale , Handler handler){
        super(x ,y, "Player" , WIDTH ,HEIGHT ,scale);
    }

    @Override
    public void tick() {
        setX(getVelX()+getX());
        setY(getVelY()+getY());
       applyGravity();
       collision();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect((int)getX(), (int)getY(), (int)WIDTH, (int)HEIGHT);
        showBounds(g);
    }
    private void collision(){
        for(int i=0; i<handler.getGameObjs().size() ; i++){
            GameObject temp = handler.getGameObjs().get(i);
            if(temp.getId() == "Block" || temp.getId() == "Pipe"){
                if(getBounds().intersects(temp.getBounds()));
            }
            if(getBounds().intersects(temp.getBounds())){
                setY(temp.getY()-getHeight());
                setVelY(0);
                jumped = false;
            }
            if(getBoundsTop().intersects(temp.getBounds())){
                setY(temp.getY() + temp.getHeight());
                setVelY(0);
            }
            if(getBoundsRight().intersects(temp.getBounds())){
                setX(temp.getX() -getWidth());

            }
            if(getBoundsLeft().intersects(temp.getBounds())){
                setX(temp.getX() + temp.getWidth());
            }
        }
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)(getX()+getWidth()/2 - getWidth()/4) , (int)(getY() +getHeight()/2) , (int)getWidth()/2 , (int)getHeight()/2);
    }

    public Rectangle getBoundsTop(){
        return new Rectangle((int)(getX() +getWidth()/2 - getWidth()/4) , (int)getY() , (int)getWidth()/2 , (int)getHeight()/2);
    }
    public Rectangle getBoundsRight(){
        return new Rectangle((int)(getX()+getWidth() -5), (int)getY()+5,5,(int)getHeight()-10) ;
    }
    public Rectangle getBoundsLeft(){
        return new Rectangle((int)getX() , (int)(getY()+5) , 5 , (int)(getHeight()-10));
    }
    
    private void showBounds(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.red);
        g2d.draw(getBounds());
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsTop());
    }

    public boolean hasJumped(){
        return jumped ;
    }
    public void setJumped(boolean hasJumped){
        jumped = hasJumped;
    }
}
