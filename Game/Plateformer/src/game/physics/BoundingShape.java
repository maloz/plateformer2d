package game.physics;

import game.level.tile.Tile;

import java.util.ArrayList;

import org.newdawn.slick.tiled.TiledMap;

public abstract class BoundingShape {
    
    public boolean checkCollision(BoundingShape bv, TiledMap map){
        if(bv instanceof AABoundingRect)
            return checkCollision((AABoundingRect) bv, map);
        return false;
    }
    
    public boolean checkCollision(BoundingShape bv){
        if(bv instanceof AABoundingRect)
            return checkCollision((AABoundingRect) bv);
        return false;
    }
    
    public abstract boolean checkCollision(AABoundingRect box);
    public abstract boolean checkCollision(AABoundingRect box, TiledMap map);
    
    public abstract void updatePosition(float newX, float newY);
    
    public abstract void movePosition(float x, float y);
    
    public abstract ArrayList<Tile> getTilesOccupying(Tile[][] tiles);
    
    public abstract ArrayList<Tile> getGroundTiles(Tile[][] tiles);

}
