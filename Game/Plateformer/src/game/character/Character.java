package game.character;

import java.util.HashMap;

import game.enums.Facing;
import game.level.Level;
import game.level.LevelObject;
import game.projectile.Projectile;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Character extends LevelObject {
    
    protected HashMap<Facing,Image>     sprites;
    
    protected HashMap<Facing,Animation> movingAnimations;
    protected Facing                    facing;
    protected boolean                   moving = false;
    protected float                     accelerationSpeed = 1;
    protected float                     decelerationSpeed = 1;
    protected float                     maximumSpeed = 1;
    
    public Character(float x, float y) throws SlickException{
        super(x,y);
        //in case we forget to set the image, we don't want the game to crash, but it still has to be obvious that something was forgotten
        setSprite(new Image("ressources/sprites/p2_walk01.png"));
        
        //default direction will be right
        facing = Facing.RIGHT;
    }
    
    protected void setMovingAnimation(Image[] images, int frameDuration){
        movingAnimations = new HashMap<Facing,Animation>();
        
        //we can just put the right facing in with the default images
        movingAnimations.put(Facing.RIGHT, new Animation(images,frameDuration));
        
        Animation facingLeftAnimation = new Animation();
        for(Image i : images){
            facingLeftAnimation.addFrame(i.getFlippedCopy(true, false), frameDuration);
        }
        movingAnimations.put(Facing.LEFT, facingLeftAnimation);
        
    }
    
    protected void setSprite(Image i){
        sprites = new HashMap<Facing,Image>();
        sprites.put(Facing.RIGHT, i);
        sprites.put(Facing.LEFT , i.getFlippedCopy(true, false));
    }
    
    public boolean isMoving(){
        return moving;
    }
    
    public void setMoving(boolean b){
        moving = b;
    }
    
    //move towards x_velocity = 0
    public void decelerate(int delta) {
        if(x_velocity > 0){
            x_velocity -= decelerationSpeed * delta;
            if(x_velocity < 0)
                x_velocity = 0;
        }else if(x_velocity < 0){
            x_velocity += decelerationSpeed * delta;
            if(x_velocity > 0)
                x_velocity = 0;
        }
    }
    
    public void jump(){
        if(onGround)
            y_velocity = -0.8f;
    }
    public void shoot(float f, float g) throws SlickException{
    	Level.addLevelObject(new Projectile((float) f, (float) g, this.facing));
    }
    
    public void moveLeft(int delta){
        //if we aren't already moving at maximum speed
        if(x_velocity > -maximumSpeed){
            //accelerate
            x_velocity -= accelerationSpeed*delta;
            if(x_velocity < -maximumSpeed){
                //and if we exceed maximum speed, set it to maximum speed
                x_velocity = -maximumSpeed;
            }
        }
        moving = true;
        facing = Facing.LEFT;
    }
    
    public void moveRight(int delta){
        if(x_velocity < maximumSpeed){
            x_velocity += accelerationSpeed*delta;
            if(x_velocity > maximumSpeed){
                x_velocity = maximumSpeed;
            }
        }
        moving = true;
        facing = Facing.RIGHT;
    }
    
    public void render(int offset_x, int offset_y){
        
        //draw a moving animation if we have one and we moved within the last 150 miliseconds
    	if(movingAnimations != null && moving){
            movingAnimations.get(facing).draw(x-2-offset_x,y-2-offset_y);                
        }else{            
            sprites.get(facing).draw(x-2-offset_x, y-2-offset_y);          
        }
    }

}
