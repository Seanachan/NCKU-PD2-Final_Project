package com.mario.framework;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.doge.OsUtils;
import com.mario.utils.BufferedImageLoader;
import com.mario.utils.SpriteSheet;

public class Texture {

    SpriteSheet level_items_sprites, block_sprites, object_sprites, player_sprites, enemy_sprites;
    Map<String, List<BufferedImage>> texMap;
    int size = 32; // Standard size for block in sprite sheets

    public Texture(){
        texMap = new HashMap<>();
        BufferedImage image = null;
        if(OsUtils.isWindows()){
            try {
                image = ImageIO.read(new FileInputStream("src/res/level-items.png"));
                level_items_sprites = new SpriteSheet(image);
    
                image = ImageIO.read(new FileInputStream("src/res/blocks.png"));
                block_sprites = new SpriteSheet(image);
    
                image = ImageIO.read(new FileInputStream("src/res/objects.png"));
                object_sprites = new SpriteSheet(image);
    
                image = ImageIO.read(new FileInputStream("src/res/image.png"));
                player_sprites = new SpriteSheet(image);
    
                image = ImageIO.read(new FileInputStream("src/res/newEnemy.png"));
                enemy_sprites = new SpriteSheet(image);
                
            } catch (Exception e){
                e.printStackTrace();
            }
        }else{
            try {
                image = ImageIO.read(new FileInputStream("src/res/level-items.png"));
                level_items_sprites = new SpriteSheet(image);
    
                image = ImageIO.read(new FileInputStream("src/res/blocks.png"));
                block_sprites = new SpriteSheet(image);
    
                image = ImageIO.read(new FileInputStream("src/res/objects.png"));
                object_sprites = new SpriteSheet(image);
    
                image = ImageIO.read(new FileInputStream("src/res/image.png"));
                player_sprites = new SpriteSheet(image);
    
                image = ImageIO.read(new FileInputStream("src/res/newEnemy.png"));
                enemy_sprites = new SpriteSheet(image);                
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        

        getLevelItemTex();
        getBlockTex();
        getObjectTex();
        getPlayerTex();
        getEnemyTex();
    }

    private void getLevelItemTex() {

        List<BufferedImage> smallHill = new ArrayList<>();
        smallHill.add( level_items_sprites.grabImage( 1, 1, size * 2, size ) );
        texMap.put( "hill-small", smallHill );

        List<BufferedImage> largeHill = new ArrayList<>();
        largeHill.add( level_items_sprites.grabImage( 3, 1, size * 3, size * 2 ) );
        texMap.put( "hill-large", largeHill );

        List<BufferedImage> mediumCloud = new ArrayList<>();
        mediumCloud.add( level_items_sprites.grabImage( 6, 1, size * 2, size ) );
        texMap.put( "cloud-medium", mediumCloud );

        List<BufferedImage> pipePieceH = new ArrayList<>();
        pipePieceH.add( level_items_sprites.grabImage( 8, 1, size, size ) );
        texMap.put( "pipe-piece-h", pipePieceH );

        List<BufferedImage> smallBush = new ArrayList<>();
        smallBush.add( level_items_sprites.grabImage( 1, 2, size * 2, size ) );
        texMap.put( "bush-small", smallBush );

        List<BufferedImage> pipeHeadV = new ArrayList<>(); // "v" for vertical pipe
        pipeHeadV.add( level_items_sprites.grabImage( 6, 2, size * 2, size ) );
        texMap.put( "pipe-head-v", pipeHeadV );

        List<BufferedImage> pipePieceV = new ArrayList<>();
        pipePieceV.add( level_items_sprites.grabImage( 8, 2, size, size ) );
        texMap.put( "pipe-piece-v", pipePieceV );

        List<BufferedImage> mediumBush = new ArrayList<>();
        mediumBush.add( level_items_sprites.grabImage( 1, 3, size * 2, size ) );
        texMap.put( "bush-medium", mediumBush );

        List<BufferedImage> largeCloud = new ArrayList<>();
        largeCloud.add( level_items_sprites.grabImage( 3, 3, size * 3, size ) );
        texMap.put( "cloud-large", largeCloud );

        List<BufferedImage> smallCloud = new ArrayList<>();
        smallCloud.add( level_items_sprites.grabImage( 6, 3, size * 2, size ) );
        texMap.put( "cloud-small", smallCloud );

        List<BufferedImage> finishPole = new ArrayList<>();
        finishPole.add( level_items_sprites.grabImage( 8, 3, size, size * 6 ) );
        texMap.put( "finishpole", finishPole );

        List<BufferedImage> largeBush = new ArrayList<>();
        largeBush.add( level_items_sprites.grabImage( 1, 4, size * 3, size ) );
        texMap.put( "bush-large", largeBush );

        List<BufferedImage> pipeConnector = new ArrayList<>();
        pipeConnector.add( level_items_sprites.grabImage( 4, 4, size * 2, size * 2 ) );
        texMap.put( "pipe-connector", pipeConnector );

        List<BufferedImage> pipeHeadH = new ArrayList<>(); // "h" for horizontal pipe
        pipeHeadH.add( level_items_sprites.grabImage( 6, 4, size, size * 2 ) );
        texMap.put( "pipe-head-h", pipeHeadH );

        List<BufferedImage> poleFlag = new ArrayList<>();
        poleFlag.add( level_items_sprites.grabImage( 7, 4, size, size ) );
        texMap.put( "flag-pole", poleFlag );

        List<BufferedImage> castleFlag = new ArrayList<>();
        castleFlag.add( level_items_sprites.grabImage( 7, 5, size, size ) );
        texMap.put( "flag-castle", castleFlag );

        List<BufferedImage> castleNormal = new ArrayList<>();
        castleNormal.add( level_items_sprites.grabImage( 1, 6, size * 3, size * 3 ) );
        texMap.put( "castle-normal", castleNormal );

    }

    private void getBlockTex() {
        

        List<BufferedImage> groundNormal = new ArrayList<>();
        groundNormal.add( block_sprites.grabImage( 1, 1, size, size ) );
        texMap.put( "ground-normal", groundNormal );

        List<BufferedImage> mysteryBlock = new ArrayList<>();
        mysteryBlock.add( block_sprites.grabImage( 2, 1, size, size ) );
        mysteryBlock.add( block_sprites.grabImage( 3, 1, size, size ) );
        mysteryBlock.add( block_sprites.grabImage( 4, 1, size, size ) );
        texMap.put( "mysteryblock-normal", mysteryBlock );

        List<BufferedImage> blockEmptyNormal = new ArrayList<>();
        blockEmptyNormal.add( block_sprites.grabImage( 5, 1, size, size ) );
        texMap.put( "block-empty-normal", blockEmptyNormal );

        List<BufferedImage> ground2Normal = new ArrayList<>();
        ground2Normal.add( block_sprites.grabImage( 6, 1, size, size ) );
        texMap.put( "ground2-normal", ground2Normal );

        List<BufferedImage> blockEmptyNight = new ArrayList<>();
        blockEmptyNight.add( block_sprites.grabImage( 7, 1, size, size ) );
        texMap.put( "block-empty-night", blockEmptyNight );

        List<BufferedImage> groundNight = new ArrayList<>();
        groundNight.add( block_sprites.grabImage( 8, 1, size, size ) );
        texMap.put( "ground-night", groundNight );

        List<BufferedImage> brick = new ArrayList<>();
        brick.add( block_sprites.grabImage( 1, 2, size, size ) );
        texMap.put( "brick-normal", brick );

        List<BufferedImage> brickPiece = new ArrayList<>();
        brickPiece.add( block_sprites.grabImage( 3, 2, size, size ) );
        brickPiece.add( block_sprites.grabImage( 4, 2, size, size ) );
        brickPiece.add( block_sprites.grabImage( 5, 2, size, size ) );
        brickPiece.add( block_sprites.grabImage( 6, 2, size, size ) );
        texMap.put( "brickpiece-normal", brickPiece );

        List<BufferedImage> ground2Night = new ArrayList<>();
        ground2Night.add( block_sprites.grabImage( 8, 2, size, size ) );
        texMap.put( "ground2-night", ground2Night );

        List<BufferedImage> brickNight = new ArrayList<>();
        brickNight.add( block_sprites.grabImage( 1, 3, size, size ) );
        texMap.put( "brick-night", brickNight );

        List<BufferedImage> brickEmptyNight = new ArrayList<>();
        brickEmptyNight.add( block_sprites.grabImage( 2, 3, size, size ) );
        texMap.put( "brick-empty-night", brickEmptyNight );

        List<BufferedImage> brickPieceNight = new ArrayList<>();
        brickPieceNight.add( block_sprites.grabImage( 3, 3, size, size ) );
        brickPieceNight.add( block_sprites.grabImage( 4, 3, size, size ) );
        brickPieceNight.add( block_sprites.grabImage( 5, 3, size, size ) );
        brickPieceNight.add( block_sprites.grabImage( 6, 3, size, size ) );
        texMap.put( "brickpiece-night", brickPieceNight );
    }

    private void getObjectTex() {

        List<BufferedImage> growthMushroom = new ArrayList<>();
        growthMushroom.add( object_sprites.grabImage( 1, 1, size, size ) );
        texMap.put( "mushroom-growth", growthMushroom );

        List<BufferedImage> fireFlowerNormal = new ArrayList<>();
        fireFlowerNormal.add( object_sprites.grabImage( 2, 1, size, size ) );
        fireFlowerNormal.add( object_sprites.grabImage( 3, 1, size, size ) );
        fireFlowerNormal.add( object_sprites.grabImage( 4, 1, size, size ) );
        fireFlowerNormal.add( object_sprites.grabImage( 5, 1, size, size ) );
        texMap.put( "fireflower-normal", fireFlowerNormal );

        List<BufferedImage> coinNight = new ArrayList<>();
        coinNight.add( object_sprites.grabImage( 6, 1, size, size ) );
        coinNight.add( object_sprites.grabImage( 7, 1, size, size ) );
        coinNight.add( object_sprites.grabImage( 8, 1, size, size ) );
        texMap.put( "coin-night", coinNight );

        List<BufferedImage> lifeMushroom = new ArrayList<>();
        lifeMushroom.add( object_sprites.grabImage( 1, 2, size, size ) );
        texMap.put( "mushroom-life", lifeMushroom );

        List<BufferedImage> starNormal = new ArrayList<>();
        starNormal.add( object_sprites.grabImage( 2, 2, size, size ) );
        starNormal.add( object_sprites.grabImage( 3, 2, size, size ) );
        starNormal.add( object_sprites.grabImage( 4, 2, size, size ) );
        starNormal.add( object_sprites.grabImage( 5, 2, size, size ) );
        texMap.put( "star-normal", starNormal );

        List<BufferedImage> coinNormal = new ArrayList<>();
        coinNormal.add( object_sprites.grabImage( 6, 2, size, size ) );
        coinNormal.add( object_sprites.grabImage( 7, 2, size, size ) );
        coinNormal.add( object_sprites.grabImage( 8, 2, size, size ) );
        texMap.put( "coin-normal", coinNormal );

        List<BufferedImage> jumpingCoin = new ArrayList<>();
        jumpingCoin.add( object_sprites.grabImage( 1, 3, size, size ) );
        jumpingCoin.add( object_sprites.grabImage( 2, 3, size, size ) );
        jumpingCoin.add( object_sprites.grabImage( 3, 3, size, size ) );
        jumpingCoin.add( object_sprites.grabImage( 4, 3, size, size ) );
        texMap.put( "jumpingcoin", jumpingCoin );

        List<BufferedImage> fireball = new ArrayList<>();
        fireball.add( object_sprites.grabImage( 1, 4, size, size ) );
        fireball.add( object_sprites.grabImage( 2, 4, size, size ) );
        fireball.add( object_sprites.grabImage( 3, 4, size, size ) );
        fireball.add( object_sprites.grabImage( 4, 4, size, size ) );
        texMap.put( "fireball", fireball );

        List<BufferedImage> explosion = new ArrayList<>();
        explosion.add( object_sprites.grabImage( 5, 4, size, size ) );
        explosion.add( object_sprites.grabImage( 6, 4, size, size ) );
        explosion.add( object_sprites.grabImage( 7, 4, size, size ) );
        texMap.put( "explosion", explosion );

        List<BufferedImage> hatchetNormal = new ArrayList<>();
        hatchetNormal.add( object_sprites.grabImage( 1, 5, size, size ) );
        hatchetNormal.add( object_sprites.grabImage( 2, 5, size, size ) );
        hatchetNormal.add( object_sprites.grabImage( 3, 5, size, size ) );
        hatchetNormal.add( object_sprites.grabImage( 4, 5, size, size ) );
        texMap.put( "hatchet-normal", hatchetNormal );

        List<BufferedImage> bowserFire = new ArrayList<>();
        bowserFire.add( object_sprites.grabImage( 5, 5, size, size ) );
        bowserFire.add( object_sprites.grabImage( 6, 5, size, size ) );
        texMap.put( "bowserfire", bowserFire );

        List<BufferedImage> hatchetNight = new ArrayList<>();
        hatchetNight.add( object_sprites.grabImage( 1, 6, size, size ) );
        hatchetNight.add( object_sprites.grabImage( 2, 6, size, size ) );
        hatchetNight.add( object_sprites.grabImage( 3, 6, size, size ) );
        hatchetNight.add( object_sprites.grabImage( 4, 6, size, size ) );
        texMap.put( "hatchet-night", hatchetNight );
    }

    private void getPlayerTex() {

        List<BufferedImage> marioDead = new ArrayList<>();
        marioDead.add( player_sprites.grabImage( 1, 3, size, size ) );
        texMap.put( "mario-dead", marioDead );

        getPlayerGroupTex( 1, 1, "normal" );
        getPlayerGroupTex( 9, 1, "fire" );
        getPlayerGroupTex( 1, 9, "invincible1" );
        getPlayerGroupTex( 9, 9, "invincible2" );
        getPlayerGroupTex( 1, 17, "invincible3" );

    }

    private void getPlayerGroupTex( int colStart, int rowStart, String id ) {

        List<BufferedImage> smallMarioIdleRight = new ArrayList<>();
        smallMarioIdleRight.add( player_sprites.grabImage( colStart, rowStart, size, size ) );
        texMap.put( "mario-small-idle-right-" + id, smallMarioIdleRight );

        List<BufferedImage> smallMarioRunningRight = new ArrayList<>();
        smallMarioRunningRight.add( player_sprites.grabImage( colStart + 1, rowStart, size, size ) );
        smallMarioRunningRight.add( player_sprites.grabImage( colStart + 2, rowStart, size, size ) );
        smallMarioRunningRight.add( player_sprites.grabImage( colStart + 3, rowStart, size, size ) );
        texMap.put( "mario-small-running-right-" + id, smallMarioRunningRight );

        List<BufferedImage> smallMarioJumpingRight = new ArrayList<>();
        smallMarioJumpingRight.add( player_sprites.grabImage( colStart + 4, rowStart, size, size ) );
        texMap.put( "mario-small-jumping-right-" + id, smallMarioJumpingRight );

        List<BufferedImage> smallMarioHangingRight = new ArrayList<>();
        smallMarioHangingRight.add( player_sprites.grabImage( colStart + 5, rowStart, size, size ) );
        texMap.put( "mario-small-hanging-right-" + id, smallMarioHangingRight );

        List<BufferedImage> smallMarioFiringRight = new ArrayList<>();
        smallMarioFiringRight.add( player_sprites.grabImage( colStart + 3, rowStart, size, size ) );
        texMap.put( "mario-small-firing-right-" + id, smallMarioFiringRight );

        List<BufferedImage> smallMarioIdleLeft = new ArrayList<>();
        smallMarioIdleLeft.add( player_sprites.grabImage( colStart, rowStart + 1, size, size ) );
        texMap.put( "mario-small-idle-left-" + id, smallMarioIdleLeft );

        List<BufferedImage> smallMarioRunningLeft = new ArrayList<>();
        smallMarioRunningLeft.add( player_sprites.grabImage( colStart + 1, rowStart + 1, size, size ) );
        smallMarioRunningLeft.add( player_sprites.grabImage( colStart + 2, rowStart + 1, size, size ) );
        smallMarioRunningLeft.add( player_sprites.grabImage( colStart + 3, rowStart + 1, size, size ) );
        texMap.put( "mario-small-running-left-" + id, smallMarioRunningLeft );

        List<BufferedImage> smallMarioJumpingLeft = new ArrayList<>();
        smallMarioJumpingLeft.add( player_sprites.grabImage( colStart + 4, rowStart + 1, size, size ) );
        texMap.put( "mario-small-jumping-left-" + id, smallMarioJumpingLeft );

        List<BufferedImage> smallMarioHangingLeft = new ArrayList<>();
        smallMarioHangingLeft.add( player_sprites.grabImage( colStart + 5, rowStart + 1, size, size ) );
        texMap.put( "mario-small-hanging-left-" + id, smallMarioHangingLeft );

        List<BufferedImage> smallMarioFiringLeft = new ArrayList<>();
        smallMarioFiringLeft.add( player_sprites.grabImage( colStart + 3, rowStart + 1, size, size ) );
        texMap.put( "mario-small-firing-left-" + id, smallMarioFiringLeft );


        ////
        List<BufferedImage> largeMarioIdleRight = new ArrayList<>();
        largeMarioIdleRight.add( player_sprites.grabImage( colStart, rowStart + 3, size, size * 2 ) );
        texMap.put( "mario-large-idle-right-" + id, largeMarioIdleRight );

        List<BufferedImage> largeMarioRunningRight = new ArrayList<>();
        largeMarioRunningRight.add( player_sprites.grabImage( colStart + 1, rowStart + 3, size, size * 2 ) );
        largeMarioRunningRight.add( player_sprites.grabImage( colStart + 2, rowStart + 3, size, size * 2 ) );
        largeMarioRunningRight.add( player_sprites.grabImage( colStart + 3, rowStart + 3, size, size * 2 ) );
        texMap.put( "mario-large-running-right-" + id, largeMarioRunningRight );

        List<BufferedImage> largeMarioJumpingRight = new ArrayList<>();
        largeMarioJumpingRight.add( player_sprites.grabImage( colStart + 4, rowStart + 3, size, size * 2 ) );
        texMap.put( "mario-large-jumping-right-" + id, largeMarioJumpingRight );

        List<BufferedImage> largeMarioHangingRight = new ArrayList<>();
        largeMarioHangingRight.add( player_sprites.grabImage( colStart + 5, rowStart + 3, size, size * 2 ) );
        texMap.put( "mario-large-hanging-right-" + id, largeMarioHangingRight );

        List<BufferedImage> largeMarioFiringRight = new ArrayList<>();
        largeMarioFiringRight.add( player_sprites.grabImage( colStart + 6, rowStart + 3, size, size * 2 ) );
        texMap.put( "mario-large-firing-right-" + id, largeMarioFiringRight );

        List<BufferedImage> largeMarioIdleLeft = new ArrayList<>();
        largeMarioIdleLeft.add( player_sprites.grabImage( colStart, rowStart + 5, size, size * 2 ) );
        texMap.put( "mario-large-idle-left-" + id, largeMarioIdleLeft );

        List<BufferedImage> largeMarioRunningLeft = new ArrayList<>();
        largeMarioRunningLeft.add( player_sprites.grabImage( colStart + 2, rowStart + 5, size, size * 2 ) );
        largeMarioRunningLeft.add( player_sprites.grabImage( colStart + 3, rowStart + 5, size, size * 2 ) );
        largeMarioRunningLeft.add( player_sprites.grabImage( colStart + 4, rowStart + 5, size, size * 2 ) );
        texMap.put( "mario-large-running-left-" + id, largeMarioRunningLeft );

        List<BufferedImage> largeMarioJumpingLeft = new ArrayList<>();
        largeMarioJumpingLeft.add( player_sprites.grabImage( colStart + 1, rowStart + 5, size, size * 2 ) );
        texMap.put( "mario-large-jumping-left-" + id, largeMarioJumpingLeft );

        List<BufferedImage> largeMarioHangingLeft = new ArrayList<>();
        largeMarioHangingLeft.add( player_sprites.grabImage( colStart + 5, rowStart + 5, size, size * 2 ) );
        texMap.put( "mario-large-hanging-left-" + id, largeMarioHangingLeft );
        List<BufferedImage> largeMarioFiringLeft = new ArrayList<>();
        largeMarioFiringLeft.add( player_sprites.grabImage( colStart + 6, rowStart + 5, size, size * 2 ) );
        texMap.put( "mario-large-firing-left-" + id, largeMarioFiringLeft );
        ////


        List<BufferedImage> bigTransformationRight = new ArrayList<>();
        bigTransformationRight.add( player_sprites.grabImage( colStart, rowStart, size, size ) );
        bigTransformationRight.add( player_sprites.grabImage( colStart + 1, rowStart + 2, size, size ) );
        bigTransformationRight.add( player_sprites.grabImage( colStart, rowStart, size, size ) );
        bigTransformationRight.add( player_sprites.grabImage( colStart + 1, rowStart + 2, size, size ) );
        bigTransformationRight.add( player_sprites.grabImage( colStart, rowStart + 3, size, size * 2 ) );
        bigTransformationRight.add( player_sprites.grabImage( colStart, rowStart, size, size ) );
        bigTransformationRight.add( player_sprites.grabImage( colStart + 1, rowStart + 2, size, size ) );
        bigTransformationRight.add( player_sprites.grabImage( colStart, rowStart + 3, size, size * 2 ) );
        texMap.put( "mario-big-transformation-right-" + id, bigTransformationRight );

        List<BufferedImage> bigTransformationLeft = new ArrayList<>();
        bigTransformationLeft.add( player_sprites.grabImage( colStart, rowStart + 1, size, size ) );
        bigTransformationLeft.add( player_sprites.grabImage( colStart + 2, rowStart + 2, size, size ) );
        bigTransformationLeft.add( player_sprites.grabImage( colStart, rowStart + 1, size, size ) );
        bigTransformationLeft.add( player_sprites.grabImage( colStart + 2, rowStart + 2, size, size ) );
        bigTransformationLeft.add( player_sprites.grabImage( colStart, rowStart + 4, size, size * 2 ) );
        bigTransformationLeft.add( player_sprites.grabImage( colStart, rowStart + 1, size, size ) );
        bigTransformationLeft.add( player_sprites.grabImage( colStart + 2, rowStart + 2, size, size ) );
        bigTransformationLeft.add( player_sprites.grabImage( colStart, rowStart + 4, size, size * 2 ) );
        texMap.put( "mario-big-transformation-left-" + id, bigTransformationLeft );
    }

    private void getEnemyTex() {

        List<BufferedImage> goombaNormal = new ArrayList<>();
        goombaNormal.add( enemy_sprites.grabImage( 1, 1, size, size ) );
        goombaNormal.add( enemy_sprites.grabImage( 2, 1, size, size ) );
        texMap.put( "goomba-normal", goombaNormal );

        List<BufferedImage> goombaDeadNormal = new ArrayList<>();
        goombaDeadNormal.add( enemy_sprites.grabImage( 3, 1, size, size ) );
        texMap.put( "goomba-dead-normal", goombaDeadNormal );

        List<BufferedImage> goombaNight = new ArrayList<>();
        goombaNight.add( enemy_sprites.grabImage( 4, 1, size, size ) );
        goombaNight.add( enemy_sprites.grabImage( 5, 1, size, size ) );
        texMap.put( "goomba-night", goombaNight );

        List<BufferedImage> goombaDeadNight = new ArrayList<>();
        goombaDeadNight.add( enemy_sprites.grabImage( 6, 1, size, size ) );
        texMap.put( "goomba-dead-night", goombaDeadNight );

        List<BufferedImage> piranhaPlantNormal = new ArrayList<>();
        piranhaPlantNormal.add( enemy_sprites.grabImage( 7, 1, size, size ) );
        piranhaPlantNormal.add( enemy_sprites.grabImage( 8, 1, size, size ) );
        texMap.put( "piranhaplant-normal", piranhaPlantNormal );

        List<BufferedImage> piranhaPlantNight = new ArrayList<>();
        piranhaPlantNight.add( enemy_sprites.grabImage( 9, 1, size, size ) );
        piranhaPlantNight.add( enemy_sprites.grabImage( 10, 1, size, size ) );
        texMap.put( "piranhaplant-night", piranhaPlantNight );

        List<BufferedImage> cheepCheepRLeft = new ArrayList<>();
        cheepCheepRLeft.add( enemy_sprites.grabImage( 1, 2, size, size ) );
        cheepCheepRLeft.add( enemy_sprites.grabImage( 2, 2, size, size ) );
        texMap.put( "cheepcheep-r-left", cheepCheepRLeft );

        List<BufferedImage> cheepCheepGLeft = new ArrayList<>();
        cheepCheepGLeft.add( enemy_sprites.grabImage( 3, 2, size, size ) );
        cheepCheepGLeft.add( enemy_sprites.grabImage( 4, 2, size, size ) );
        texMap.put( "cheepcheep-g-left", cheepCheepGLeft );

        List<BufferedImage> lakituLeft = new ArrayList<>();
        lakituLeft.add( enemy_sprites.grabImage( 5, 2, size, size ) );
        lakituLeft.add( enemy_sprites.grabImage( 6, 2, size, size ) );
        texMap.put( "lakitu-left", lakituLeft );

        List<BufferedImage> lakituRight = new ArrayList<>();
        lakituRight.add( enemy_sprites.grabImage( 7, 2, size, size ) );
        lakituRight.add( enemy_sprites.grabImage( 6, 2, size, size ) );
        texMap.put( "lakitu-right", lakituRight );

        List<BufferedImage> cheepCheepGRight = new ArrayList<>();
        cheepCheepGRight.add( enemy_sprites.grabImage( 8, 2, size, size ) );
        cheepCheepGRight.add( enemy_sprites.grabImage( 9, 2, size, size ) );
        texMap.put( "cheepcheep-g-right", cheepCheepGRight );

        List<BufferedImage> cheepCheepRRight = new ArrayList<>();
        cheepCheepRRight.add( enemy_sprites.grabImage( 10, 2, size, size ) );
        cheepCheepRRight.add( enemy_sprites.grabImage( 11, 2, size, size ) );
        texMap.put( "cheepcheep-r-right", cheepCheepRRight );

        List<BufferedImage> koopaFlyingGLeft = new ArrayList<>();
        koopaFlyingGLeft.add( enemy_sprites.grabImage( 1, 3, size, size ) );
        koopaFlyingGLeft.add( enemy_sprites.grabImage( 2, 3, size, size ) );
        texMap.put( "koopa-flying-g-left", koopaFlyingGLeft );

        List<BufferedImage> koopaGLeft = new ArrayList<>();
        koopaGLeft.add( enemy_sprites.grabImage( 3, 3, size, size ) );
        koopaGLeft.add( enemy_sprites.grabImage( 4, 3, size, size ) );
        texMap.put( "koopa-g-left", koopaGLeft );

        List<BufferedImage> koopaGDead = new ArrayList<>();
        koopaGDead.add( enemy_sprites.grabImage( 5, 3, size, size ) );
        texMap.put( "koopa-g-dead", koopaGDead );

        List<BufferedImage> koopaGEmpty = new ArrayList<>();
        koopaGEmpty.add( enemy_sprites.grabImage( 6, 3, size, size ) );
        texMap.put( "koopa-g-empty", koopaGEmpty );

        List<BufferedImage> koopaGRight = new ArrayList<>();
        koopaGRight.add( enemy_sprites.grabImage( 7, 3, size, size ) );
        koopaGRight.add( enemy_sprites.grabImage( 8, 3, size, size ) );
        texMap.put( "koopa-g-right", koopaGRight );

        List<BufferedImage> koopaFlyingGRight = new ArrayList<>();
        koopaFlyingGRight.add( enemy_sprites.grabImage( 9, 3, size, size ) );
        koopaFlyingGRight.add( enemy_sprites.grabImage( 10, 3, size, size ) );
        texMap.put( "koopa-flying-g-right", koopaFlyingGRight );

        List<BufferedImage> koopaFlyingRLeft = new ArrayList<>();
        koopaFlyingRLeft.add( enemy_sprites.grabImage( 1, 4, size, size ) );
        koopaFlyingRLeft.add( enemy_sprites.grabImage( 2, 4, size, size ) );
        texMap.put( "koopa-flying-r-left", koopaFlyingRLeft );

        List<BufferedImage> koopaRLeft = new ArrayList<>();
        koopaRLeft.add( enemy_sprites.grabImage( 3, 4, size, size ) );
        koopaRLeft.add( enemy_sprites.grabImage( 4, 4, size, size ) );
        texMap.put( "koopa-r-left", koopaRLeft );

        List<BufferedImage> koopaRDead = new ArrayList<>();
        koopaRDead.add( enemy_sprites.grabImage( 5, 4, size, size ) );
        texMap.put( "koopa-r-dead", koopaRDead );

        List<BufferedImage> koopaREmpty = new ArrayList<>();
        koopaREmpty.add( enemy_sprites.grabImage( 6, 4, size, size ) );
        texMap.put( "koopa-r-empty", koopaREmpty );

        List<BufferedImage> koopaRRight = new ArrayList<>();
        koopaRRight.add( enemy_sprites.grabImage( 7, 4, size, size ) );
        koopaRRight.add( enemy_sprites.grabImage( 8, 4, size, size ) );
        texMap.put( "koopa-r-right", koopaRRight );

        List<BufferedImage> koopaFlyingRRight = new ArrayList<>();
        koopaFlyingRRight.add( enemy_sprites.grabImage( 9, 4, size, size ) );
        koopaFlyingRRight.add( enemy_sprites.grabImage( 10, 4, size, size ) );
        texMap.put( "koopa-flying-r-right", koopaFlyingRRight );

        List<BufferedImage> hammerBroLeftNormal = new ArrayList<>();
        hammerBroLeftNormal.add( enemy_sprites.grabImage( 1, 5, size, size ) );
        hammerBroLeftNormal.add( enemy_sprites.grabImage( 2, 5, size, size ) );
        hammerBroLeftNormal.add( enemy_sprites.grabImage( 3, 5, size, size ) );
        texMap.put( "hammerbro-left-normal", hammerBroLeftNormal );

        List<BufferedImage> hammerBroRightNormal = new ArrayList<>();
        hammerBroRightNormal.add( enemy_sprites.grabImage( 4, 5, size, size ) );
        hammerBroRightNormal.add( enemy_sprites.grabImage( 5, 5, size, size ) );
        hammerBroRightNormal.add( enemy_sprites.grabImage( 6, 5, size, size ) );
        texMap.put( "hammerbro-right-normal", hammerBroRightNormal );

        List<BufferedImage> spinyLeft = new ArrayList<>();
        spinyLeft.add( enemy_sprites.grabImage( 7, 5, size, size ) );
        spinyLeft.add( enemy_sprites.grabImage( 8, 5, size, size ) );
        texMap.put( "spiny-left", spinyLeft );

        List<BufferedImage> spinyRight = new ArrayList<>();
        spinyRight.add( enemy_sprites.grabImage( 9, 5, size, size ) );
        spinyRight.add( enemy_sprites.grabImage( 10, 5, size, size ) );
        texMap.put( "spiny-right", spinyRight );

        List<BufferedImage> hammerBroLeftNight = new ArrayList<>();
        hammerBroLeftNight.add( enemy_sprites.grabImage( 1, 6, size, size ) );
        hammerBroLeftNight.add( enemy_sprites.grabImage( 2, 6, size, size ) );
        hammerBroLeftNight.add( enemy_sprites.grabImage( 3, 6, size, size ) );
        texMap.put( "hammerbro-left-night", hammerBroLeftNight );

        List<BufferedImage> hammerBroRightNight = new ArrayList<>();
        hammerBroRightNight.add( enemy_sprites.grabImage( 4, 6, size, size ) );
        hammerBroRightNight.add( enemy_sprites.grabImage( 5, 6, size, size ) );
        hammerBroRightNight.add( enemy_sprites.grabImage( 6, 6, size, size ) );
        texMap.put( "hammerbro-right-night", hammerBroRightNight );

        List<BufferedImage> bowser = new ArrayList<>();
        bowser.add( enemy_sprites.grabImage( 1, 7, size * 2, size * 2 ) );
        bowser.add( enemy_sprites.grabImage( 2, 7, size * 2, size * 2 ) );
        bowser.add( enemy_sprites.grabImage( 3, 7, size * 2, size * 2 ) );
        bowser.add( enemy_sprites.grabImage( 4, 7, size * 2, size * 2 ) );
        texMap.put( "bowser", bowser );
    }

    public BufferedImage[] get( String key ) {
        List<BufferedImage> result = texMap.get(key);
        return result.toArray( new BufferedImage[0] );
    }
}
