package com.mario.utils;

import java.awt.*;
import java.util.*;
import java.util.List;

import com.mario.objects.GameObject;

public class QuadTree {

    private int capacity; // determines the max number of objects that can be stored in a single node before it is split
    private Rectangle boundary;
    boolean isDivided = false;
    private QuadTree[] children;
    private List<GameObject> objects;

    public QuadTree( Rectangle boundary, int n ) {
        this.boundary = boundary;
        this.capacity = n;
        objects = new ArrayList<>();
    }

    public boolean insert( GameObject object ) {
        if ( !boundary.intersects( object.getBounds() ) ) return false;

        if ( !isDivided ) {
            objects.add(object);
            if ( objects.size() > capacity ) subDivide();

        } else {
            boolean inserted = false;
            for ( int i = 0; i < 4; i++ ) {
                if ( children[i].insert(object) ) inserted = true;
            }
            return inserted;
        }

        return true;
    }

    public void subDivide() {
        int subWidth = boundary.width / 2;
        int subHeight = boundary.height / 2;
        int x = boundary.x;
        int y = boundary.y;

        children = new QuadTree[4];
        children[0] = new QuadTree( new Rectangle( x, y, subWidth, subHeight ), capacity );
        children[1] = new QuadTree( new Rectangle( x + subWidth, y, subWidth, subHeight ), capacity );
        children[2] = new QuadTree( new Rectangle( x, y + subHeight, subWidth, subHeight ), capacity );
        children[3] = new QuadTree( new Rectangle( x + subWidth, y + subHeight, subWidth, subHeight ), capacity );

        for ( int i = 0; i <= capacity; i++ ) {
            GameObject obj = objects.get(i);

            // Allow children to decide whether to add object (may have been overlapping)
            children[0].insert( obj );
            children[1].insert( obj );
            children[2].insert( obj );
            children[3].insert( obj );
        }

        objects.clear();
        isDivided = true;
    }

    public Set<GameObject> query( Rectangle range ) {
        Set<GameObject> found = new HashSet<>();

        if ( !boundary.intersects(range) ) return found;
        if ( !isDivided ) {
            for ( int i = 0; i < objects.size(); i++ ) {
                GameObject object = objects.get(i);
                if ( object.getBounds().intersects(range) ) found.add( object );
            }
        } else {
            found.addAll( children[0].query(range) );
            found.addAll( children[1].query(range) );
            found.addAll( children[2].query(range) );
            found.addAll( children[3].query(range) );
        }

        return found;
    }

    public List<GameObject> sortedQuery( Rectangle range ) {
        Set<GameObject> query = query( range );
        List<GameObject> sorted = new ArrayList<>(query);
        if ( sorted.size() == 0 ) return sorted;

        sorted.sort((o1, o2) -> {
            Rectangle i1 = range.intersection(o1.getBounds());
            Rectangle i2 = range.intersection(o2.getBounds());
            return (i2.width * i2.height) - (i1.width * i1.height);
        });

        return sorted;
    }

    public void display( Graphics g ) {
        g.setColor( Color.RED );
        g.drawRect( boundary.x, boundary.y, boundary.width, boundary.height );

        if ( isDivided ) {
            for ( int i = 0; i < 4; i++ ) {
                children[i].display(g);
            }
        }
    }

    public void flush() {
        objects.clear();
        isDivided = false;
        children = null;
    }

}
