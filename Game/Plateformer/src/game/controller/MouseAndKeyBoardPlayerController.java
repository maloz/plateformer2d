package game.controller;

import game.character.Player;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class MouseAndKeyBoardPlayerController extends PlayerController {

    public MouseAndKeyBoardPlayerController(Player player) {
        super(player);
    }

    public void handleInput(Input i, int delta) {
        //handle any input from the keyboard
        handleKeyboardInput(i,delta);
        
        try {
			handleMouseInput(i, delta);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void handleKeyboardInput(Input i, int delta){
        //we can both use the WASD or arrow keys to move around, obviously we can't move both left and right simultaneously
        if(i.isKeyDown(Input.KEY_A) || i.isKeyDown(Input.KEY_LEFT)){
            player.moveLeft(delta);
        }else if(i.isKeyDown(Input.KEY_D) || i.isKeyDown(Input.KEY_RIGHT)){
            player.moveRight(delta);
        }else{
            //we dont move if we don't press left or right, this will have the effect that our player decelerates
            player.setMoving(false);
        }
        
        if(i.isKeyDown(Input.KEY_SPACE)){
            player.jump();
        }
    }
    
    private void handleMouseInput(Input i, int delta) throws SlickException
    {
    	//pas 1920 et 1080 mais map.height et map.width
    //	int charScreenCenterX = (int) (1920/2 - player.getX()); 
    //	int charScreenCenterY = (int) (1080/2 - player.getY());
    	
    	if(i.isMousePressed(Input.MOUSE_LEFT_BUTTON))
    	{
    		player.shoot(player.getX() , player.getY()+120);
    	}
    }

}
