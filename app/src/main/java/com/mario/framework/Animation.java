package com.mario.framework;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {

    private int speed = 30;
    private int frames;

    private int index = 0;
    private int count = 0;

    private int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
    private int maxFrameWidth = 0, maxFrameHeight = 0;

    Color fallbackColor;
    private BufferedImage[] images;
    private BufferedImage currentImg;

    public Animation( String color ) {
        if ( color == "white" ) {
            fallbackColor = Color.white;
        } else if ( color == "blue" ) {
            fallbackColor = Color.blue;
        } else if ( color == "red" ) {
            fallbackColor = Color.black;
        } else if ( color == "green" ) {
            fallbackColor = Color.green;
        } else if ( color == "yellow" ) {
            fallbackColor = Color.yellow;
        }
    }

    public Animation( BufferedImage... args ){
        frames = args.length;
        images = new BufferedImage[frames];
        System.arraycopy( args, 0, images, 0, frames );

        // We are going to keep the longest widths and heights; this will be important for smaller frames
        for ( int i = 0; i < images.length; i++ ) {
            if ( images[i].getWidth() > maxFrameWidth ) maxFrameWidth = images[i].getWidth();
            if ( images[i].getHeight() > maxFrameHeight ) maxFrameHeight = images[i].getHeight();
            if ( images[i].getMinX() < minX ) minX = images[i].getMinX();
            if ( images[i].getMinY() < minY ) minY = images[i].getMinY();
        }
        nextFrame();
    }

    public void setAnimationSpeed( int speed ) { this.speed = speed; }

    public void runAnimation() {
        if ( fallbackColor == null ) {
            index++;
            if ( index > speed ) {
                index = 0;
                nextFrame();
            }
        }
    }

    private void nextFrame(){
        currentImg = images[count];
        count = ( count + 1 ) % frames;
    }

    public void drawAnimation( Graphics g, int x, int y, int width, int height ) {
        if ( fallbackColor == null ) {
            double animationW = Math.round( currentImg.getWidth()/(double)maxFrameWidth * width );
            double animationH = Math.round( currentImg.getHeight()/(double)maxFrameHeight * height );

            // move image to the middle of the zone
            double middleX = x + ( (double)width / 2 );
            double middleY = y + ( (double)height / 2 );

            g.drawImage(
                currentImg,
                (int)Math.round(middleX - animationW/2),
                (int)Math.round(middleY - animationH/2),
                (int)animationW,
                (int)animationH,
                null
            );
        } else {
            g.setColor( fallbackColor );
            g.fillRect( x, y, width, height );
        }
    }

}
