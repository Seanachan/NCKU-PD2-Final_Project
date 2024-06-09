package model.prize;

import java.awt.Rectangle;
import manager.MapManager;
import manager.GameEngine;
import model.hero.Mario;

public class Prize {
    
    int getPoint();
    void reveal();
    Rectangle getBounds();
    void onTouch(Mario mario , GameEngine engine);
}
