package projectt12g03.controller.gameController;

import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.Player;
import projectt12g03.model.game.room.Room;
import projectt12g03.model.game.room.RoomManager;
import projectt12g03.model.menu.PauseMenu;
import projectt12g03.state.GameState;
import projectt12g03.state.menuSate.PauseMenuState;

import java.io.IOException;

public class PlayerController extends Controller<RoomManager> {


    public PlayerController(RoomManager model) { super(model); }

    //A LOT OF HARDCODE NEEDS TO BE REDONE
    @Override
    public void step(Game game, LanternaGui.userInput action, long time) throws IOException {
        Player player = getModel().getRoom().getPlayer();
        switch (action) {
            case UP:
                player.setPosition(player.moveUp());
                if (getModel().getRoom().isAtTransition(new Vector(player.getPosition().getX(), player.getPosition().getY()-1))) {
                    getModel().roomBuilder(getModel().getRoom().getDoor(new Vector(player.getPosition().getX(), player.getPosition().getY()-1)));
                }
                break;
            case DOWN:
                player.setPosition(player.moveDown());
                if (getModel().getRoom().isAtTransition(new Vector(player.getPosition().getX(), player.getPosition().getY()+1))) {
                    getModel().roomBuilder(getModel().getRoom().getDoor(new Vector(player.getPosition().getX(), player.getPosition().getY()+1)));
                }
                break;
            case LEFT:
                player.setPosition(player.moveLeft());
                if (getModel().getRoom().isAtTransition(new Vector(player.getPosition().getX()-1, player.getPosition().getY()))) {
                    getModel().roomBuilder(getModel().getRoom().getDoor(new Vector(player.getPosition().getX()-1, player.getPosition().getY())));
                }
                break;
            case RIGHT:
                player.setPosition(player.moveRight());
                if (getModel().getRoom().isAtTransition(new Vector(player.getPosition().getX()+1, player.getPosition().getY()))) {
                    getModel().roomBuilder(getModel().getRoom().getDoor(new Vector(player.getPosition().getX()+1, player.getPosition().getY())));
                }
                break;
        }
    }
}
