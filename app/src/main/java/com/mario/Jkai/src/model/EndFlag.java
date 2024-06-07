package model;

import java.awt.image.BufferedImage;

public class EndFlag {
    private boolean touched = false;
    public EndFlag(double x, double y , BufferedImage style){
        super(x,y,style);
    }

    @Override
    public void updateLocation(){
        if(touched){
            if(getY() + getDimension().getHeight() >= 576){
                setFalling(flase);

            }
        }
    }
}
