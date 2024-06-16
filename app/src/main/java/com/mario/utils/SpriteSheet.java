package com.mario.utils;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage image;
    int size = 32; // Standard size for block in sprite sheets

    public SpriteSheet( BufferedImage img ) {
        image = img;
    }

    public BufferedImage grabImage( int col, int row, int width, int height ) {
        // +1 and -2 because the sprites are surrounded by a 1px black outline
        BufferedImage img = image.getSubimage(
            ( col * size ) - size + 1,
            ( row * size ) - size + 1,
            width - 2,
            height - 2
        );
        return huecoMundo(img);
    }

    /**
     * Performs some calculations to determine the boundaries of a region within the image that contains colored pixels.
     * It then returns a cropped version of the input image that includes only that region.
     */
    private BufferedImage huecoMundo( BufferedImage img ) {
        int width = img.getWidth();
        int height = img.getHeight();
        int minX = width, minY = height, maxX = 0, maxY = 0;

        for ( int y = 0; y < height; y++ ) {
            for ( int x = 0; x < width; x++ ) {
                int pixel = img.getRGB(x, y);
                if ( isTransparent(pixel) ) continue; // Check transparency

                if ( x < minX ) minX = x;
                if ( y < minY ) minY = y;
                if ( x > maxX ) maxX = x;
                if ( y > maxY ) maxY = y;

            }
        }
//        System.out.println("width: " + imgWidth + "   height:  " + imgHeight + "   x:    " + imgX + "   y:    " + imgY);

        /* At this stage we have all we need. Just grab a more accurate image*/
        return img.getSubimage( minX, minY, maxX - minX + 1, maxY - minY + 1 );
    }

    private boolean isTransparent( int pixel ) { return ( pixel >> 24 ) == 0x00; }
}
