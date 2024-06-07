package model;

import model.hero.Mario;
import model.brick.Brick;
import model.enemy.Enemy;
import model.prize.Prize;
import model.hero.FireBall;
import model.hero.Coin;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class Map{
    private double remainingTime ;
    private Mario mario;
    private ArrayList<Brick> bricks = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Brick> groundBricks = new ArrayList<>();
    private ArrayList<Prize> revealedPrizes = new ArrayList<>();
    private ArrayList<Brick> revealedBrick = new ArrayList<>();
    private ArrayList<FireBall> fireBalls = new ArrayList<>();
    private EndFlag endPoint; 
    private BufferedImage backgroundImage;
    private double bottomBorder = 720-96;
    private String path;

    public Map(double remainingTime , BufferedImage backgroundImage){
        this.backgroundImage = backgroundImage;
        this.remainingTime = remainingTime;
    }

    public Mario getMario(){
        return mario;
    }

    public void setMario(Mario mario){
        this.mario = mario;
    }

    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }

    public ArrayList<FireBall> gerFireBalls(){
        return fireBalls;
    }

    public ArrayList<Prize> getRevealPrizes(){
        return revealedPrizes;
    }

    public ArrayList<Brick> getAllBricks(){
        ArrayList<Brick> allBricks = new ArrayList<>();

        allBricks.addAll(bricks);
        allBricks.addAll(groundBricks);
        return allBricks;
    }

    public void addBricks(Brick brick){
        this.bricks.add(brick);
    }

    public void addGroundBrick(Brick brick){
        this.groundBricks.add(brick);
    }

    public void addEnemy(Enemy enemy){
        this.enemies.add(enemy);
    }

    public void drawMap(Graphics2D g2){ //要拿來畫地圖
        drawBackground(g2);
        drawPrizes(g2);
        drawBricks(g2);
        drawEnermies(g2);
        drawEnemies(g2);
        drawFireBalls(g2);
        drawMario(g2);
        endPoint.draw(g2);
    }

    private void drawFireBalls(Graphics2D g2){
        for(FireBall fireball : fireBalls){
            fireball.draw(g2);
        }
    }

    private void drawPrizes(Graphics2D g2){
        for(Prize prize : revealedPrizes){
            if(prize instanceof Coin){
                ((Coin) prize).draw(g2);
            }
            else if (prize instanceof BoostItem){
                ((BoostItem) prize).draw(g2);
            }
        }
    }

    private void drawBackground(Graphics2D g2){
        for(Brick brick : bricks){
            if(brick != null){
                brick.draw(g2);
            }   
        }
        for(Brick brick : groundBricks){
            brick.draw(g2);
        }
    }

    private void drawEnemies(Graphics2D g2){
        for(Enemy enemy : enemies){
            if(enemy != null){
                enemy.draw(g2);
            }
        }
    }

    private void drawMario(Graphics2D g2){
        mario.draw(g2);
    }

    public void updateLocations(){
        mario.updateLocations();
        for(Enemy enemy : enemies){
            enemy.updateLocations();
        }

        for(Iterator<Prize> prizeIterator = revealedPrizes.iterator(); prizeIterator.hasNext();){
            Prize prize = prizeIterator.next();
            if(prize instanceof Coin){
                ((Coin) prize).updateLocations();
                if(((Coin) prize).getRevealBoundary() > ((Coin) prize).getY()){
                    
                }
            }
        }
    }

}