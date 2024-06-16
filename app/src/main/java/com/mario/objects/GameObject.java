package com.mario.objects;

import static com.mario.framework.Level.getQTree;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Set;


public class GameObject extends CanvasItem {

    protected String type;
    protected float velocityX = 0, velocityY = 0;
    protected boolean falling = false;
    protected float defaultSpeed = 0;
    protected final float gravity = 0.5f;

    public GameObject( float x, float y, float width, float height, String type, String color ) {
        super(x, y, width, height, color);

        this.type = type;
    }

    public GameObject( float x, float y, float width, float height, String type, BufferedImage... imgs ) {
        super(x, y, width, height, imgs);
        this.type = type;
    }

    public String getType() { return type; }
    public boolean isFalling() { return falling; }
    public float getVelocityX() { return velocityX; }
    public float getVelocityY() { return velocityY; }
    public void setVelocityX(float x) { this.velocityX = x; }
    public void setVelocityY(float y) { this.velocityY = y; }

    public Rectangle getBounds(){
        return new Rectangle( (int)x, (int)y, (int)width, (int)height );
    }

    public void tick() {
        // run animation
        super.tick();

        x += velocityX;
        y += velocityY;
        checkCollisions();
        checkFalling();
        if ( falling ) velocityY += gravity;
    }

    protected void checkCollisions() {
        if ( velocityX == 0 && velocityY == 0 && !falling ) return;
        List<GameObject> sortedNeighbors = getQTree().sortedQuery( getBounds() );
        sortedNeighbors.removeIf( i -> i == this );
        if ( sortedNeighbors.size() == 0 ) return;

        for ( GameObject neighbor : sortedNeighbors ) {
            Rectangle neighborRect = neighbor.getBounds();
            int contactPoint = rectIntersection( getBounds(), neighborRect );
            if ( contactPoint == 0 ) continue;

            handleCollision(contactPoint, neighbor);
        }
    }

    /**
     * Helper function for calculating collisions
     * 0 => no collision
     * 1 => top collision
     * 2 => right collision
     * 3 => bottom collision
     * 4 => left collision
     **/
    protected int lineIntersection( Line2D line, Rectangle rect ) {
        double[] lineStart = { line.getX1(), line.getY1() };
        double[] lineVector = { line.getX2() - lineStart[0], line.getY2() - lineStart[1] };
        double length = Math.sqrt( (lineVector[0] * lineVector[0]) + (lineVector[1] * lineVector[1]) );
        if ( length == 0 ) return 0;
        // lineEnd = lineStart + 1 * lineVector

        double[] rectStart = { rect.x, rect.y };
        double[] rectEnd = { rect.x + rect.width, rect.y + rect.height };

        // Find at what value of t will I intersect with the rectangle
        double[] t_near = { (rectStart[0] - lineStart[0])/lineVector[0], (rectStart[1] - lineStart[1])/lineVector[1] };
        double[] t_far = { (rectEnd[0] - lineStart[0])/lineVector[0], (rectEnd[1] - lineStart[1])/lineVector[1] };

        // Rules for intersection: t_near[0] < t_far[1], t_near[1] < t_far[0]
        if ( t_near[0] > t_far[0] ) {
            double temp = t_far[0];
            t_far[0] = t_near[0];
            t_near[0] = temp;
        }
        if ( t_near[1] > t_far[1] ) {
            double temp = t_far[1];
            t_far[1] = t_near[1];
            t_near[1] = temp;
        }
        if ( t_near[0] > t_far[1] || t_near[1] > t_far[0] ) return 0;

        double t_hit_near = Math.max( t_near[0], t_near[1] );
        double t_hit_far = Math.min( t_far[0], t_far[1] );

        if ( t_hit_far < 0 || t_hit_near > 1 ) return 0; // Disregard collisions behind the line

        // Collision point
        if ( t_near[0] > t_near[1] ){
            if ( lineVector[0] < 0 )
                return 4;
            return 2;
        } else {
            if ( lineVector[1] < 0 )
                return 1;
            return 3;
        }

    }

    protected int rectIntersection( Rectangle origin, Rectangle rect ) {
        // Assuming that there was no collision unless movement occurred
        Rectangle expanded_target = new Rectangle(
            rect.x - (origin.width/2),
            rect.y - (origin.height/2),
            rect.width + origin.width,
            rect.height + origin.height
        );
        // Setting line from center of the rectangle
        int line_x = origin.x + ( origin.width / 2 );
        int line_y = origin.y + ( origin.height / 2 );
        Line2D testLine = new Line2D.Double();
        testLine.setLine( line_x, line_y, line_x + velocityX, line_y + velocityY );

        return lineIntersection( testLine, expanded_target );
    }

    protected void checkFalling() {
        if ( velocityX == 0 && velocityY == 0 ) return;
        if ( falling ) return;

        if ( velocityY != 0 ) {
            falling = true;
            return;
        }

        Rectangle bounds = getBounds();
        bounds.height += 2;
        Set<GameObject> neighbors = getQTree().query( bounds );
        neighbors.removeIf( i -> i == this );
        if ( neighbors.size() > 0 ) {
            falling = false;
        } else {
            falling = true;
        }
    }

    public void handleCollision( int contactPoint, GameObject neighbor ) {
        Rectangle neighborRect = neighbor.getBounds();
        float diff;

        switch ( contactPoint ){
            case 1:
                diff = neighborRect.y + neighborRect.height - y + 1;
                y += diff; // y -= velocityY;
                falling = true;
                if ( velocityY < 0 ) velocityY = 0;
                break;
            case 2:
                diff = x + width - neighborRect.x + 1;
                x -= diff; // x -= velocityX;
                velocityX = 0;
                break;
            case 3:
                diff = y + height - neighborRect.y + 1;
                y -= diff; // y -= velocityY;
                falling = false;
                if ( velocityY > 0 ) velocityY = 0;
                break;
            case 4:
                diff = neighborRect.x + neighborRect.width - x + 1;
                x += diff; // x -= velocityX;
                velocityX = 0;
                break;
        }
    }

    public void moveLeft() { if ( defaultSpeed != 0 ) setVelocityX( -defaultSpeed ); }
    public void moveRight() { if ( defaultSpeed != 0 ) setVelocityX( defaultSpeed ); }

}
