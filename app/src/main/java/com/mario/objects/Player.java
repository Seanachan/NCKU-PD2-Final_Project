package com.mario.objects;

import static com.mario.framework.Level.removeItem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.mario.Game;
import com.mario.framework.Texture;
import com.mario.utils.Window;

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
    boolean win=false;
    private static int scoreCount =0;


    public static int getScoreCount(){
        return scoreCount;
    }
    public static void setScoreCount(int setScore){
        scoreCount += setScore;
    }
    public Player( float x, float y ) {
        super( x, y, 32, 32, "player", "white" );
        updateSprites();
    }

    public void tick() {
        super.tick();
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
            scoreCount -=4;
            updateSprites();
        }
    }

    public void moveRight() {
        if ( isDead ) return;

        setVelocityX(5);
        if ( !movingRight ) {
            //System.out.println(x +" "+ y);
            movingRight = true;
            direction = "right";
            if ( !isFalling() ) state = "running";
            scoreCount +=2;
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
    final int CASTLE_X = 6432 ;
    final int CASTLE_Y = 383;
    public Game game = new Game();
    public boolean check = false;
    public void handleCollision( int contactPoint, GameObject neighbor ) {
        super.handleCollision( contactPoint, neighbor );
        //System.out.println(neighbor.getType());
        if ( neighbor.getType() == "mushroom" ) {
            scoreCount +=10;
            liveCount-=1;
            size="large";
            removeItem(neighbor);
            return;
        } else if ( neighbor.getType() == "block" ) {
            if ( contactPoint == 1 ) neighbor.handleCollision( 3, this );
        } else if ( neighbor.getType() == "enemy" ) {
            if ( contactPoint == 3 ) {
                scoreCount +=5;
                jump(5);
                removeItem(neighbor);
            } else {
                die();
            }
        }
        
        if (x <  6549 && x > 6424 &&
            y  <= 385 && y>=256) {
            check = true;            
            game.stop(scoreCount,true);
            Window.isComplete = true;
            return;
        }
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
        
        isDead = true;
        falling = false;
        setVelocityY(0);

        liveCount +=1;
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
            scoreCount =1;
            updateSprites();
            game.stop(scoreCount,win);
            return ;
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
        g.setColor(Color.WHITE); // Set the text color
        g.setFont(new Font("Arial", Font.BOLD, 20)); // Set the font
        if(liveCount>=3) liveCount=3;
        g.drawString("Lives: " + (3 - liveCount), 10, 30); // Adjust the position
        if(liveCount >=3){
            g.setFont(new Font("Arial",Font.BOLD,70));
            g.drawString("Game Over", 450,650);
            g.drawString("Score: "+String.format("%d", scoreCount),(int) getWidth()/2,(int) getHeight()/2+30);
            game.stop(scoreCount,win);
        }
        
    }

}


/*
move right score+=3
move left score -=3
beat enemy +=5;
eat the mushroom socre +=10
dead one time score -= 10;
dead trhee time score =0;
*/
