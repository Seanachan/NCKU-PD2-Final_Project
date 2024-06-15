package com.tngo.mario.objects;

import static com.tngo.mario.framework.Level.removeItem;

import java.awt.Graphics;

import com.tngo.mario.Game;
import com.tngo.mario.framework.Texture;

public class Player extends GameObject {

    Texture tex = Game.getTex();

    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean isDead = false;
    private String size = "small";
    private String state = "idle";
    private String style = "normal";
    private String direction = "right";
    public int liveCount =0;
    private int scoreCount =0;

//    public Player( float x, float y, String color ) {
//        super( x, y, 32, 32, "player", color );
//    }
    public int getScoreCount(){
        return scoreCount;
    }
    public Player( float x, float y ) {
        super( x, y, 32, 32, "player", "white" );
        updateSprites();
    }

    public void tick() {
        super.tick();
        // Right now, player does not fall automatically if what object beneath it moves, but it doesn't
        // This can be tweaked, but I doubt this use case will happen naturally in the game

        if ( x < 0 ) {
            x = 0;
            stopMoveLeft();
        }
        if ( y + height >= Game.HEIGHT ) die();
    }

    public boolean canDestroyBrick() { return size == "big"; }

    public void moveLeft() {
        if ( isDead ) return;

        setVelocityX(-5);
        if ( !movingLeft ) {
            movingLeft = true;
            direction = "left";
            if ( !isFalling() ) state = "running";
            scoreCount -=3;
            updateSprites();
        }
    }

    public void moveRight() {
        if ( isDead ) return;

        setVelocityX(5);
        if ( !movingRight ) {
            movingRight = true;
            direction = "right";
            if ( !isFalling() ) state = "running";
            scoreCount +=3;
            updateSprites();
        }
    }

    public void jump() {
        if ( isDead ) return;

        if ( isFalling() ) return;
        setVelocityY(-12);
        falling = true;
        state = "jumping";
        scoreCount +=1;
        updateSprites();
    }

    public void jump( float speed ) {
        if ( isFalling() ) return;
        setVelocityY(-speed);
        falling = true;
        state = "jumping";
        updateSprites();
    }

    public void stopMoveLeft() {
        if ( isDead ) return;

        movingLeft = false;
        if ( movingRight ) {
            setVelocityX(5);
            direction = "right";
        } else {
            setVelocityX(0);
            if ( !isFalling() ) state = "idle";
        }
        updateSprites();
    }

    public void stopMoveRight() {
        if ( isDead ) return;

        movingRight = false;
        if ( movingLeft ) {
            setVelocityX(-5);
            direction = "left";
        } else {
            setVelocityX(0);
            if ( !isFalling() ) state = "idle";
        }
        updateSprites();
    }

    private void updateSprites() {
        String search = "mario";
        if ( size != "" ) search += "-" + size;
        if ( state != "" ) search += "-" + state;
        if ( direction != "" ) search += "-" + direction;
        if ( style != "" ) search += "-" + style;
        setSprites( tex.get( search ) );
        setAnimationSpeed(2);
    }

    public void handleCollision( int contactPoint, GameObject neighbor ) {
        super.handleCollision( contactPoint, neighbor );
        //System.out.println(neighbor.getType());
        if ( neighbor.getType() == "mushroom" ) {
            removeItem(neighbor);
            return;
        } else if ( neighbor.getType() == "block" ) {
            if ( contactPoint == 1 ) neighbor.handleCollision( 3, this );
        } else if ( neighbor.getType() == "enemy" ) {
            if ( contactPoint == 3 ) {
                jump(5);
                removeItem(neighbor);
            } else {
                die();
            }
        }
        // if(neighbor.getType() == "castle-normal"){
        //     System.out.println("shit man");
        //     EndGame end = new EndGame();
        //     end.endGame();
            
        // }
        // Player movements
        if ( contactPoint == 2 ) {
            stopMoveRight();
        } else if ( contactPoint == 3 ) {
            if ( velocityX == 0 ) {
                state = "idle";
            } else {
                state = "running";
            }
            updateSprites();
        } else {
            stopMoveLeft();
        }

    }
    
    public void die() {
        liveCount +=1;
        isDead = true;
        falling = false;
        setVelocityY(0);

        
        if(liveCount <3){
            this.x = 32;
            this.y = 384;
            scoreCount -=10;
            resetPlayer();
        }
        else {
            state = "dead";
            size = "";
            style = "";
            direction = "";
            scoreCount =0;
            updateSprites();
            return ;
            //freeze the rest of the level
        }
        
        
        
    }
    public void resetPlayer(){
        isDead = false;
        size = "small";
        style = "normal";
        direction = "right";
        state = "idle";
        updateSprites();
    }
    public void render(Graphics g) {
        super.render(g);
        drawLivesCount(g);
    }
    
    private void drawLivesCount(Graphics g) {
        g.drawString("Lives: " + (3 - liveCount), 10, 10);
        //g.setFont("Ariel");

    }
}



// class EndGame{
//     private boolean running = true;
//     public void endGame(){
//         running = false;
//         showEndScreen();
//     }
//     private void showEndScreen(){
//         System.out.println("shut the fuck up");
//     }

// }
