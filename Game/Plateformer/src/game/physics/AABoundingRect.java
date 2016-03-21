package game.physics;

import game.level.tile.Tile;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class AABoundingRect extends BoundingShape {

	public float x;
	public float y;
	public float width;
	public float height;

	public AABoundingRect(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void updatePosition(float newX, float newY) {
		this.x = newX;
		this.y = newY;
	}

	public void movePosition(float x, float y) {
		this.x += x;
		this.y += y;
	}

	public boolean checkCollision(AABoundingRect rect, TiledMap map){

		return !(rect.x > this.x + width || rect.x + rect.width < this.x ||
				rect.y > this.y + height || rect.y + rect.height < this.y);

	}

	public boolean checkCollision(AABoundingRect rect) {
		return !(rect.x > this.x + width || rect.x + rect.width < this.x || rect.y > this.y + height
				|| rect.y + rect.height < this.y);
	}

	public ArrayList<Tile> getTilesOccupying(Tile[][] tiles) {
		ArrayList<Tile> occupiedTiles = new ArrayList<Tile>();

		// we go from the left of the rect towards to right of the rect, making
		// sure we round upwards to a multiple of 128 or we might miss a few
		// tiles
		for (int i = (int) x; i <= x + width + (128 - width % 128); i += 128) {
			for (int j = (int) y; j <= y + height + (128 - height % 128); j += 128) {
				occupiedTiles.add(tiles[i / 128][j / 128]);
			}
		}
		return occupiedTiles;
	}

	public ArrayList<Tile> getGroundTiles(Tile[][] tiles) {
		ArrayList<Tile> tilesUnderneath = new ArrayList<Tile>();
		int j = (int) (y + height + 1);

		for (int i = (int) x; i <= x + width + (128 - width % 128); i += 128) {
			tilesUnderneath.add(tiles[i / 128][j / 128]);
		}

		return tilesUnderneath;
	}

}
