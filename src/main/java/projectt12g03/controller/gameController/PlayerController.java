package projectt12g03.controller.gameController;

import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.GUI;
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

        if (player.getImmunity() > 0) player.reduceImmunity();
        if (player.getIsAttacking() > 0) player.reduceIsAttacking();
        switch (action) {
            case UP:
                player.setPosition(player.moveUp());
                if (getModel().getRoom().isAtTransition(new Vector(player.getPosition().getX(), player.getPosition().getY()-1))) {
                    getModel().roomBuilder(getModel().getRoom().getDoor(new Vector(player.getPosition().getX(), player.getPosition().getY()-1)));
                }
                player.setDirection("UP");
                break;
            case DOWN:
                player.setPosition(player.moveDown());
                if (getModel().getRoom().isAtTransition(new Vector(player.getPosition().getX(), player.getPosition().getY()+1))) {
                    getModel().roomBuilder(getModel().getRoom().getDoor(new Vector(player.getPosition().getX(), player.getPosition().getY()+1)));
                }
                player.setDirection("DOWN");
                break;
            case LEFT:
                player.setPosition(player.moveLeft());
                if (getModel().getRoom().isAtTransition(new Vector(player.getPosition().getX()-1, player.getPosition().getY()))) {
                    getModel().roomBuilder(getModel().getRoom().getDoor(new Vector(player.getPosition().getX()-1, player.getPosition().getY())));
                }
                player.setDirection("LEFT");
                break;
            case RIGHT:
                player.setPosition(player.moveRight());
                if (getModel().getRoom().isAtTransition(new Vector(player.getPosition().getX()+1, player.getPosition().getY()))) {
                    getModel().roomBuilder(getModel().getRoom().getDoor(new Vector(player.getPosition().getX()+1, player.getPosition().getY())));
                }
                player.setDirection("RIGHT");
                break;
            case ATTACK:
                player.attack();
                break;
        }
    }
}
