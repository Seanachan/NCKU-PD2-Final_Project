package model.prize;

import java.awt.*;
import manager.MapManager;
import manager.GameEngine;
import model.hero.Mario;

public interface Prize {
    
    int getPoint();
    void reveal();
    Rectangle getBounds();
    void onTouch(Mario mario , GameEngine engine);
}
