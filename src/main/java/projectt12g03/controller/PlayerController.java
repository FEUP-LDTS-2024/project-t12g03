package projectt12g03.controller;

import projectt12g03.Game;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.game.elements.Player;
import projectt12g03.model.game.room.Room;

import java.io.IOException;

public class PlayerController extends Controller<Room> {


    public PlayerController(Room model) { super(model); }

    @Override
    public void step(Game game, LanternaGui.userInput action, long time) throws IOException {
        Player player = getModel().getPlayer();
        switch (action) {
            case UP:
                player.setPosition(player.moveUp());
                break;
            case DOWN:
                player.setPosition(player.moveDown());
                break;
            case LEFT:
                player.setPosition(player.moveLeft());
                break;
            case RIGHT:
                player.setPosition(player.moveRight());
                break;
            case QUIT:
                game.setState(null); //temp while pause does not exist
        }
    }
}
