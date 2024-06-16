package com.mario.utils;

import com.mario.Game;
import com.mario.framework.Texture;
import com.mario.objects.*;

public class ItemFactory {

    Texture tex = Game.getTex();

    public CanvasItem create_object( int x, int y, String itemType ) {
        CanvasItem object;
        //y = y+100;
        switch ( itemType ){
            case "ground":
                object = new GameObject( x, y, 32, 32, itemType, tex.get( "ground-normal" ) );
                //System.out.println("x: "+x +"y: " + y);
                break;
            case "ground2":
                object = new GameObject( x, y, 32, 32, itemType, tex.get( "ground2-normal" ) );
                break;
            case "mystery-block-coin":
                object = new MysteryBlock( x, y, "normal", "coin" );
                break;
            case "mystery-block-growth-mushroom":
                object = new MysteryBlock( x, y, "normal", "growth-mushroom" );
                break;
            case "brick":
                object = new BrickBlock( x, y, "normal" );
                break;
            case "pipe-head-v":
                object = new PipeHead( x, y, true );
                break;
            case "pipe-piece":
                object = new GameObject( x + 4, y, 56, 32, itemType, tex.get( "pipe-piece-v" ) );
                break;
            case "hill-small":
                object = new CanvasItem( x, y - 6, 96, 38, tex.get( itemType ) );
                break;
            case "hill-large":
                object = new CanvasItem( x, y, 160, 64, tex.get( itemType ) );
                break;
            case "cloud-small":
                object = new CanvasItem( x, y, 64, 48, tex.get( itemType ) );
                break;
            case "cloud-medium":
                object = new CanvasItem( x, y, 96, 48, tex.get( itemType ) );
                break;
            case "cloud-large":
                object = new CanvasItem( x, y, 128, 48, tex.get( itemType ) );
                break;
            case "bush-small":
                object = new CanvasItem( x, y, 64, 32, tex.get( itemType ) );
                break;
            case "bush-medium":
                object = new CanvasItem( x + 9, y, 96, 32, tex.get( itemType ) );
                break;
            case "bush-large":
                object = new CanvasItem( x + 9, y, 128, 32, tex.get( itemType ) );
                break;
            case "castle-normal":
                object = new CanvasItem( x, y, 160, 160, tex.get( itemType ) );
                break;
            case "goomba":
                object = new Enemy( x, y, 32, 32, tex.get( "goomba-normal" ) );
                break;
            case "koopa-g":
                object = new Enemy( x, y - 10, 32, 42, tex.get( "koopa-g-left" ) );
                break;
            case "mario-small-idle-right-normal":
                object = new Player( x, y );
                break;
            default:
                object = new CanvasItem( x, y, 64, 64, "red" );
        }

        return object;
    }

}
