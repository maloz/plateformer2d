package game.level;

import game.physics.AABoundingRect;
import game.physics.BoundingShape;

public abstract class LevelObject {
    
    protected float x;
    protected float y;
    protected BoundingShape boundingShape;
    
    protected float    x_velocity = 0;
    protected float    y_velocity = 0;
    protected float    maximumFallSpeed = 1;

    protected boolean  onGround = true;
    
    public LevelObject(float x, float y){
        this.x = x;
        this.y = y;
        
        //default bounding shape is a 70 by 70 box
        boundingShape = new AABoundingRect(x,y,128,128);
    }
    
    public void applyGravity(float gravity){
      //if we aren't already moving at maximum speed
        if(y_velocity < maximumFallSpeed){
            //accelerate
            y_velocity += gravity;
            if(y_velocity > maximumFallSpeed){
                //and if we exceed maximum speed, set it to maximum speed
                y_velocity = maximumFallSpeed;
            }
        }
    }
    
    public float getYVelocity() {
        return y_velocity;
    }

    public void setYVelocity(float f){
        y_velocity = f;
    }
    
    public float getXVelocity(){
        return x_velocity;
    }
    
    public void setXVelocity(float f){
        x_velocity = f;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
    
    public void setX(float f){
        x = f;
        updateBoundingShape();
    }
    
    public void setY(float f){
        y = f;
        updateBoundingShape();
    }
    
    public void updateBoundingShape(){
        boundingShape.updatePosition(x,y);
    }
    
    public boolean isOnGround(){
        return onGround;
    }
    
    public void setOnGround(boolean b){
        onGround = b;
    }
    
    public BoundingShape getBoundingShape(){
        return boundingShape;
    }
    public void render(float offset_x, float offset_y) {
        //animation.draw(x-2-offset_x,y-2-offset_y);
    }




    
}
