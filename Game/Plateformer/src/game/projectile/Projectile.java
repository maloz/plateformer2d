package game.projectile;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import game.enums.Facing;
import game.level.LevelObject;
import game.physics.AABoundingRect;

public class Projectile extends LevelObject {
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	protected Animation animation;

	public Projectile(float x, float y, Facing facing) throws SlickException {
		super(x, y);
		x_velocity = (float) 1.1;
		y_velocity = (float) 0;
		if (facing == Facing.LEFT)
			x_velocity *= -1;
		// add the right animation for this objective
		animation = new Animation(
				new Image[] { new Image("ressources/map/tuiles/platformerGraphicsDeluxe_Updated/Tiles/tochLit.png"),
						new Image("ressources/map/tuiles/platformerGraphicsDeluxe_Updated/Tiles/tochLit2.png") },
				125);
		boundingShape = new AABoundingRect(x, y, 40, 70);
		// animation.setPingPong(true);

		// we will just keep the default boundingrect of 70x70 for the objective
	}

	public void render(float offset_x, float offset_y) {
		animation.draw(x - 2 - offset_x, y - 2 - offset_y);
	}

	// Tools

}
