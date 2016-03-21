package game.state;

import game.WindowGame;
import game.character.Character;
import game.character.Player;
import game.controller.MouseAndKeyBoardPlayerController;
import game.controller.PlayerController;
import game.level.Level;
import game.level.tile.Tile;
import game.objective.Objective;
import game.physics.AABoundingRect;
import game.physics.Physics;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LevelState extends BasicGameState {

    private Level  level;
    private String startinglevel;
    private Player player;
    private PlayerController playerController;
    private Physics physics;
    
    public LevelState(String startingLevel){
        this.startinglevel = startingLevel;
    }
    
    public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
        
        
        //at the start of the game we don't have a player yet
        player = new Player(128,200);
       
      //once we initialize our level, we want to load the right level
        level = new Level(startinglevel, player);
        
        //and we create a controller, for now we use the MouseAndKeyBoardPlayerController
        playerController = new MouseAndKeyBoardPlayerController(player);
        
        physics = new Physics(startinglevel);
        
        Level.addLevelObject(new Objective(200,70));
        Level.addLevelObject(new Objective(800,70));
        Level.addLevelObject(new Objective(1200,70));


    }

    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        //every update we have to handle the input from the player
        playerController.handleInput(container.getInput(), delta);
        physics.handlePhysics(level, delta);
    }
    
    public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
        g.scale(WindowGame.SCALE, WindowGame.SCALE);
        //render the level
        level.render();
        
        
       /* for(Tile t : player.getBoundingShape().getGroundTiles(level.getTiles())){
            g.setColor(new Color(255,130,90,200));
            g.drawRect(t.getX()*32, t.getY()*32, 32, 32);
        }*/
        
        
    }
    
    //this method is overriden from basicgamestate and will trigger once you press any key on your keyboard
    public void keyPressed(int key, char code){
        //if the key is escape, close our application
        if(key == Input.KEY_ESCAPE){
            System.exit(0);
        }
    }

    public int getID() {
        //this is the id for changing states
        return 0;
    }

}