package com.jkai.mario.objects;

import static com.jkai.mario.framework.Level.removeItem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.jkai.mario.Game;
import com.jkai.mario.framework.Texture;
import com.jkai.mario.utils.Window;

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
    private static int scoreCount =0;

//    public Player( float x, float y, String color ) {
//        super( x, y, 32, 32, "player", color );
//    }
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
            //System.out.println(x +" "+ y);
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
    final int CASTLE_X = 6432 ;
    final int CASTLE_Y = 383;
    public Game game = new Game();
    public boolean check = false;
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
        
        if (x <  6549 && x > 6424 &&
            y  <= 385 && y>=256) {
            System.out.println("end");
            Window.score = scoreCount;
            Window.isCompleted = true;
            game.stop();
            //System.out.println(scoreCount);
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
            Window.score = scoreCount;
            Window.isCompleted = true;
            updateSprites();
            game.stop()  ;
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
        gdrawWin(g);
        drawLivesCount(g);
        
    }
    public void gdrawWin(Graphics g){
        //System.out.println("gdrawWin: "+check);
        if(check == true){
            g.setColor(Color.WHITE); // Set the text color
            g.setFont(new Font("Arial", Font.BOLD, 20)); // Set the font
            g.drawString("Win", 450, 650);
        }
    }

    private void drawLivesCount(Graphics g) {
        g.setColor(Color.WHITE); // Set the text color
        g.setFont(new Font("Arial", Font.BOLD, 20)); // Set the font
        if(liveCount>=3) liveCount=3;
        g.drawString("Lives: " + (3 - liveCount), 10, 30); // Adjust the position
        if(liveCount >=3){
            g.setFont(new Font("Arial",Font.BOLD,70));
            g.drawString("Game Over", 450,650);
            // render(g);
            // thread.interrupt();
            game.stop();
        }
        
    }

    

    

    // private void drawEndGameMessage(Graphics g, String message) {
    //     g.setColor(Color.RED); // Set the text color
    //     g.setFont(new Font("Arial", Font.BOLD, 50)); // Set the font
    //     g.drawString(message, Game.WIDTH / 2 - 150, Game.HEIGHT / 2); // Center the text
    // }

    // public void endGameWithWin() {
    //     //gameEnded = true;
    //     state = "idle";
    //     size = "";
    //     style = "";
    //     direction = "";
    //     updateSprites();
    //     System.out.println("win");
    //     // Additional game end handling such as stopping the game loop, showing a win screen, etc.
    // }
}




