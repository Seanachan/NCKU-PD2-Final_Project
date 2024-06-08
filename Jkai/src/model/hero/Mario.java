package model.hero;

import manager.Camera;
import manager.GameEngine;
import view.Animation;
import model.GameObject;
import view.ImageLoader;


import java.awt.*;
import java.awt.image.BufferedImage;
public class Mario {
    private int remainingLives;
    private int coins;
    private int points;
    private double invincibilityTimer;
    private MarioForm marioForm;
    private boolean toRight = true;
}
