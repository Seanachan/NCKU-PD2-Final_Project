package com.mario.framework;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mario.Game;
import com.mario.objects.CanvasItem;
import com.mario.objects.GameObject;
import com.mario.objects.Player;
import com.mario.utils.*;

public class Level {

    protected static Handler gameObjectHandler;
    protected Handler backgroundItemsHandler;
    protected static Camera cam;
    protected static QuadTree qtree;
    protected BufferedImage stageImage;
    protected int playerIndex;
    protected Color backgroundColor;

    public Level( Game game, String model, String backgroundColor ) {
        gameObjectHandler = new Handler();
        backgroundItemsHandler = new Handler();

        if ( backgroundColor == "black" ) {
            this.backgroundColor = new Color( 0, 0, 0 );
        } else {
            this.backgroundColor = new Color( 0, 0, 0);
        }

        // Load stage from image
        BufferedImageLoader loader = new BufferedImageLoader();
        
        try {
            System.out.println(model);
            stageImage = ImageIO.read( new FileInputStream(  model) );
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        loadStage();

        game.addKeyListener( new KeyboardInput( (Player) gameObjectHandler.getItem(playerIndex) ));
    }

    public void tick() {
        qtree.flush();
        for ( int i = 0; i < gameObjectHandler.getSize(); i++ ) {
            if ( gameObjectHandler.getItem(i) instanceof GameObject )
                qtree.insert( (GameObject) gameObjectHandler.getItem(i) );
        }

        backgroundItemsHandler.tick();
        gameObjectHandler.tick();
        cam.tick();
    }

    public void render( Graphics g ) {
        Graphics2D g2d = (Graphics2D) g;

        // Canvas
        g.setColor(this.backgroundColor);
        g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);

        // Start of cam
        g2d.translate( cam.getX(), cam.getY() );

        // Anything within the camera is going to be affected by the translation
        backgroundItemsHandler.render(g);
        gameObjectHandler.render(g);
//        qtree.display(g);

        // End of cam
        g2d.translate( -cam.getX(), -cam.getY() );
    }

    public static QuadTree getQTree() { return qtree; }

    public void loadStage() {
        int w = stageImage.getWidth();
        int h = stageImage.getHeight();
        qtree = new QuadTree( new Rectangle( 32 * w, 32 * h ), 4 );
        ItemFactory factory = new ItemFactory();

        for ( int i = 0; i < w; i++ ) {
            for ( int j = 0; j < h; j++ ) {
                int pixel = stageImage.getRGB(i,j);
                int red = ( pixel >> 16 ) &0xff;
                int green = ( pixel >> 8 ) &0xff;
                int blue = ( pixel ) &0xff;

                int x = i * 32, y = j * 32;

                if ( red == 200 && green == 76 && blue == 12 ) {
                    gameObjectHandler.addItem( factory.create_object( x, y, "ground" ) );
                } else if ( red == 204 && green == 64 && blue == 20 ) {
                    gameObjectHandler.addItem( factory.create_object( x, y, "ground2" ) );
                } else if ( red == 252 && green == 152 && blue == 56 ) {
                    gameObjectHandler.addItem( factory.create_object( x, y, "mystery-block-coin" ) );
                } else if ( red == 254 && green == 154 && blue == 58 ) {
                    gameObjectHandler.addItem( factory.create_object( x, y, "mystery-block-growth-mushroom" ) );
                } else if ( red == 249 && green == 184 && blue == 79 ) {
                    // This one should be hidden
                    gameObjectHandler.addItem( factory.create_object( x, y, "mystery-block" ) );
                } else if ( red == 150 && green == 76 && blue == 12 ) {
                    gameObjectHandler.addItem( factory.create_object( x, y, "brick" ) );
                } else if ( red == 0 && green == 168 && blue == 0 ) {
                    gameObjectHandler.addItem( factory.create_object( x, y, "pipe-head-v" ) );
                } else if ( red == 0 && green == 183 && blue == 0 ) {
                    gameObjectHandler.addItem( factory.create_object( x, y, "pipe-piece" ) );
                } else if ( red == 25 && green == 140 && blue == 25 ) {
                    backgroundItemsHandler.addItem( factory.create_object( x, y, "hill-small" ) );
                } else if ( red == 24 && green == 134 && blue == 24 ) {
                    backgroundItemsHandler.addItem( factory.create_object( x, y, "hill-large" ) );
                } else if ( red == 252 && green == 252 && blue == 252 ) {
                    backgroundItemsHandler.addItem( factory.create_object( x, y, "cloud-small" ) );
                } else if ( red == 247 && green == 247 && blue == 247 ) {
                    backgroundItemsHandler.addItem( factory.create_object( x, y, "cloud-medium" ) );
                } else if ( red == 234 && green == 234 && blue == 234 ) {
                    backgroundItemsHandler.addItem( factory.create_object( x, y, "cloud-large" ) );
                } else if ( red == 125 && green == 198 && blue == 15 ) {
                    backgroundItemsHandler.addItem( factory.create_object( x, y, "bush-small" ) );
                } else if ( red == 127 && green == 201 && blue == 16 ) {
                    backgroundItemsHandler.addItem( factory.create_object( x, y, "bush-medium" ) );
                } else if ( red == 128 && green == 208 && blue == 16 ) {
                    backgroundItemsHandler.addItem( factory.create_object( x, y, "bush-large" ) );
                } else if ( red == 96 && green == 96 && blue == 96 ) {
                    backgroundItemsHandler.addItem( factory.create_object( x, y, "castle-normal" ) );
                    System.out.println(x+" & "+y);
                }  else if ( red == 244 && green == 212 && blue == 180 ) {
                    gameObjectHandler.addItem( factory.create_object( x, y, "goomba" ) );
                } else if ( red == 211 && green == 184 && blue == 156 ) {
                    gameObjectHandler.addItem( factory.create_object( x, y, "koopa-g" ) );
                } else if ( red == 0 && green == 0 && blue == 255 ) {
                    playerIndex = gameObjectHandler.getSize();
                    gameObjectHandler.addItem( factory.create_object( x, y, "mario-small-idle-right-normal" ) );
                }
            }
        }

        cam = new Camera( 0, 32 * w, (Player) gameObjectHandler.getItem(playerIndex) );
    }

    public static void addItem( CanvasItem item ) { gameObjectHandler.addItem( item ); }
    public static void addItemAtStart( CanvasItem item ) { gameObjectHandler.addItemAtStart( item ); }
    public static void removeItem( CanvasItem item ) { gameObjectHandler.removeItem(item); }

    public static boolean itemIsVisible( CanvasItem item ) {
        // I don't know if rendering items outside the camera impacts performance, but later can use it to conditionally render
        return (
            ( item.getX() > -cam.getX() && item.getX() < -cam.getX() + Game.WIDTH ) ||
            ( item.getX() < -cam.getX() && item.getX() + item.getWidth() > -cam.getX() )
        );
    }
}
