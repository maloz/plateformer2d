package game.objective;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import game.level.LevelObject;
import game.physics.AABoundingRect;

public class Objective extends LevelObject {
	 
    protected Animation animation;
 
    public Objective(float x, float y) throws SlickException {
        super(x, y);
 
        //add the right animation for this objective
        animation = new Animation(new Image[]{new Image("ressources/map/tuiles/platformerGraphicsDeluxe_Updated/Tiles/tochLit.png"),
        				new Image("ressources/map/tuiles/platformerGraphicsDeluxe_Updated/Tiles/tochLit2.png")}
                                   ,125);
        boundingShape = new AABoundingRect(x, y, 40, 70);
       // animation.setPingPong(true);
 
        //we will just keep the default boundingrect of 70x70 for the objective
    }
 
    public void render(float offset_x, float offset_y) {
        animation.draw(x-2-offset_x,y-2-offset_y);
    }
    
    
 
}
