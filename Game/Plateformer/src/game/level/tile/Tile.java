package game.level.tile;

import game.physics.BoundingShape;

public class Tile {
    
    protected int x;
    protected int y;
    
    protected BoundingShape boundingShape;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        boundingShape = null;
    }
    
    public BoundingShape getBoundingShape(){
        return boundingShape;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }

}
