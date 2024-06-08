package model.hero;

import view.Animation;
import view.ImageLoader;

import java.awt.image.BufferedImage;
public class MarioForm {
    public static final int SMALLMARIO =0 ,SUPERMARIO =1 , FIREMARIO =2;

    private Animation animation;
    private boolean isSuper , isFire;
    private BufferedImage fireballStyle;

    public MarioForm(Animation animation , boolean isSuper , boolean isFire){ //初始化馬力歐位置叫他出來
        this.animation = animation;
        this.isSuper = isSuper;
        this.isFire = isFire;

        ImageLoader imageLoader = new ImageLoader();
        BufferedImage fireball = imageLoader.loadImage("/sprite.png");
        fireballStyle = imageLoader.getSubImage(fireball , 3, 4,24,24);
    }

    public BufferedImage getCurrentStyle(boolean toRight , boolean movingInX , boolean movingInY){ //前後左右
        BufferedImage style;
        if(movingInY && toRight){
            style = animation.getRightFrames()[0];
        }
        else if(movingInY){
            style = animation.getLeftFrames()[0];
        }
        else if(movingInX){
            style = animation.animate(5, toRight);
        }
        else {
            if(toRight){
                style = animation.getRightFrames()[1];
            }
            else
                style = animation.getLeftFrames()[1];
        }

        return style;
    }
    
    public MarioForm onTouchEnemy(ImageLoader imageLoader) { //碰到敵人
        BufferedImage[] leftFrames = imageLoader.getLeftFrames(0);
        BufferedImage[] rightFrames= imageLoader.getRightFrames(0);

        Animation newAnimation = new Animation(leftFrames, rightFrames);

        return new MarioForm(newAnimation, false, false);
    }

    public FireBall fire(boolean toRight, double x, double y) {
        if(isFire){
            return new FireBall(x, y + 48, fireballStyle, toRight);
        }
        return null;
    }
    public boolean isSuper(){ //判斷是否有能力變成SuperMario
        return isSuper();
    }
    public void setSuper(boolean aSuper){ //變成SuperMario
        isSuper = aSuper;
    }
    
    public boolean isFire(){
        return isFire;
    }

}