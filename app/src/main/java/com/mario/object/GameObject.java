package com.mario.object;


import java.awt.Graphics;
import java.awt.Rectangle;

import com.mario.object.ObjectId;

public abstract class GameObject {
    private float x;
    private float y;
    private ObjectId id;
    private float velX , velY;
    private float width,height;
    private int scale;

    public GameObject(float x , float y , ObjectId id ,float width , float height , int scale){
        this.x = x*scale;
        this.y = y*scale;
        this.width = width*scale;
        this.height = height*scale;
        this.scale = scale;
    }
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public void applyGravity(){
        velY += 0.5f;
    }
    public void setX (float x){
        this.x = x;
    }
    public void setY(float y){
        this.y =y;
    }
    public void setId(ObjectId id){
        this.id = id;
    }
    public void setVelX(float velX){
        this.velX = velX;
    }
    public void setVelY(float velY){
        this.velY = velY;
    }
    public void setWidth(float width){
        this.width = width*scale;
    }
    public void setHeight(float height){
        this.height = height*scale;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public ObjectId getId(){
        return id;
    }
    public float getVelX(){
        return velX;
    }
    public float getVelY(){
        return velY;
    }
    public float getWidth(){
        return width;
    }
    public float getHeight(){
        return height;
    }
}
