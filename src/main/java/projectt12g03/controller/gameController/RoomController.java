package projectt12g03.controller.gameController;

import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.GUI;
import projectt12g03.model.game.room.Room;
import projectt12g03.model.menu.PauseMenu;
import projectt12g03.state.menuSate.PauseMenuState;

import java.io.IOException;

public class RoomController extends Controller<Room> {
    private final PlayerController playerController;

    public RoomController(Room room, PlayerController playerController) {
        super(room);

        this.playerController = playerController;
    }

    @Override
    public void step(Game game, GUI.userInput action, long time) throws IOException {
        switch (action) {
            case QUIT:
                game.setState(new PauseMenuState(new PauseMenu(getModel())));
                break;
            default:
                playerController.step(game, action, time);
                break;
        }
    }
}
