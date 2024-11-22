package projectt12g03.controller;

import projectt12g03.Game;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.Vector;
import projectt12g03.model.elements.Player;
import projectt12g03.model.room.Room;

import java.io.IOException;

public class PlayerController extends Controller<Player> {


    public PlayerController(Player model) { super(model); }

    @Override
    public void step(Game game, LanternaGui.userInput action, long time) throws IOException {
        switch (action) {
            case UP:
                Vector up = new Vector(getModel().getPosition().getX() , getModel().getPosition().getY() - 1 );
                if(canMove(up)) { getModel().move(up); }
                break;
            case DOWN:
                Vector down = new Vector(getModel().getPosition().getX() , getModel().getPosition().getY() + 1 );
                if(canMove(down)) { getModel().move(down); }
                break;
            case LEFT:
                Vector left = new Vector(getModel().getPosition().getX() - 1 , getModel().getPosition().getY() );
                if(canMove(left)) { getModel().move(left); }
                break;
            case RIGHT:
                Vector right = new Vector(getModel().getPosition().getX() + 1 , getModel().getPosition().getY() );
                if(canMove(right)) { getModel().move(right); }
                break;
            case QUIT:
                game.setState(null); //temp while pause does not exist
        }
    }

    public boolean canMove(Vector position) {
        return true;
    }
}
