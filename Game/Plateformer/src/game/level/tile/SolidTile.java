package game.level.tile;

import game.physics.AABoundingRect;

public class SolidTile extends Tile {

    public SolidTile(int x, int y) {
        super(x, y);
        boundingShape = new AABoundingRect(x*128,y*128,128,128);
    }

}
