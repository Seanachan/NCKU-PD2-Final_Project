package com.mario.game.objects;

import static com.mario.game.framework.Level.removeItem;

import com.mario.game.Game;
import com.mario.game.framework.Texture;

public class Player extends GameObject {

    Texture tex = Game.getTex();

    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean isDead = false;
    private String size = "small";
    private String state = "idle";
    private String style = "normal";
    private String direction = "right";

//    public Player( float x, float y, String color ) {
//        super( x, y, 32, 32, "player", color );
//    }

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
            updateSprites();
        }
    }

    public void jump() {
        if ( isDead ) return;

        if ( isFalling() ) return;
        setVelocityY(-12);
        falling = true;
        state = "jumping";
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
        isDead = true;
        falling = false;
        setVelocityY(0);

        state = "dead";
        size = "";
        style = "";
        direction = "";
        updateSprites();
        // freeze the rest of the level
    }

}
