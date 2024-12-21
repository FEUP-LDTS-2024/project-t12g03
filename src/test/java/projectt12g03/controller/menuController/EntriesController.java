package projectt12g03.controller.menuController;

import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.GUI;
import projectt12g03.model.menu.Menu;
import projectt12g03.model.game.room.RoomManager;
import projectt12g03.model.menu.ControlsMenu;
import projectt12g03.model.menu.CreditsMenu;
import projectt12g03.model.menu.PauseMenu;
import projectt12g03.state.GameState;
import projectt12g03.state.menuSate.ControlsMenuState;
import projectt12g03.state.menuSate.CreditsMenuState;

import java.io.IOException;

public class EntriesController extends Controller<Menu> {

    public EntriesController(Menu menu) {
        super(menu);
    }

    @Override
    public void step(Game game, GUI.userInput action, long frameCount) throws IOException {
        switch (getModel().getCurrentEntry()) {
            case START:
                game.setState(new GameState(new RoomManager()));
                break;
            case RESTART:
                game.popState();
                game.setState(new GameState(new RoomManager()));
                break;
            case CONTROLS:
                game.setState(new ControlsMenuState(new ControlsMenu()));
                break;
            case CREDITS:
                game.setState(new CreditsMenuState(new CreditsMenu()));
                break;
            case EXIT:
                game.popState();
                if (getModel() instanceof PauseMenu) {
                    game.popState();
                }
                break;
            case GO_BACK, RESUME:
                game.popState();
                break;
        }
    }
}
