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

    @Override
    public void step(Game game, LanternaGui.userInput action, long time) throws IOException {
        Player player = getModel().getRoom().getPlayer();
        Vector checkPosition = null;

        if (player.getImmunity() > 0) player.reduceImmunity();

        switch (action) {
            case UP:
                if(player.canMove(player.moveUp()))
                    player.setPosition(player.moveUp());

                else checkPosition = player.moveUp();

                break;
            case DOWN:
                if(player.canMove(player.moveDown()))
                    player.setPosition(player.moveDown());

                else checkPosition = player.moveDown();

                break;
            case LEFT:
                if(player.canMove(player.moveLeft()))
                    player.setPosition(player.moveLeft());

                else checkPosition = player.moveLeft();

                break;
            case RIGHT:
                if(player.canMove(player.moveRight()))
                    player.setPosition(player.moveRight());

                else checkPosition = player.moveRight();

                break;
        }

        if (checkPosition != null) {
            if (getModel().getRoom().isAtTransition(checkPosition)) {
                getModel().roomBuilder(getModel().getRoom().getDoorNumber(checkPosition));
            }
        }
    }
}
