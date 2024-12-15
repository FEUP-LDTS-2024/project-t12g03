package projectt12g03.controller.gameController;

import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.GUI;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.movable.Player;
import projectt12g03.model.game.room.RoomManager;

import java.io.IOException;

public class PlayerController extends Controller<RoomManager> {
    public PlayerController(RoomManager model) { super(model); }

    @Override
    public void step(Game game, LanternaGui.userInput action, long time) throws IOException {
        Player player = getModel().getPlayer();
        Vector nextPosition = null;

        if (player.getImmunity() > 0) player.reduceImmunity();
        if (player.getIsAttacking() > 0) player.reduceIsAttacking();

        switch (action) {
            case UP:
                nextPosition = player.moveUp();
                player.setDirection("UP");
                break;
            case DOWN:
                nextPosition = player.moveDown();
                player.setDirection("DOWN");
                break;
            case LEFT:
                nextPosition = player.moveLeft();
                player.setDirection("LEFT");
                break;
            case RIGHT:
                nextPosition = player.moveRight();
                player.setDirection("RIGHT");
                break;
            case ATTACK:
                player.attack();
                break;
        }

        if (nextPosition != null) {
            if (player.canMove(nextPosition)) {
                player.setPosition(nextPosition);
            } else if (getModel().getRoom().isAtTransition(nextPosition)) {
                getModel().loadRoom(getModel().getRoom().getDoorNumber(nextPosition)); // Transition to the next room
            }
        }
    }
}
