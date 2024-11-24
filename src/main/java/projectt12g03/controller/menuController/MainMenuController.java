package projectt12g03.controller.menuController;

import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.menu.ControlsMenu;
import projectt12g03.model.menu.MainMenu;
import projectt12g03.model.game.room.RoomBuilder;
import projectt12g03.state.menuSate.ControlsMenuState;
import projectt12g03.state.GameState;

import java.io.IOException;

public class MainMenuController extends Controller<MainMenu> {
    public MainMenuController(MainMenu menu) {
        super(menu);
    }

    @Override
    public void step(Game game, LanternaGui.userInput action, long time) throws IOException {
        switch (action) {
            case UP:
                getModel().moveUp();
                break;
            case DOWN:
                getModel().moveDown();
                break;
            case SELECT:
                if (getModel().getCurrentEntry().equals("start")) game.setState(new GameState(new RoomBuilder(0).createRoom()));
                else if(getModel().getCurrentEntry().equals("controls")) game.setState(new ControlsMenuState(new ControlsMenu()));
                else if (getModel().getCurrentEntry().equals("exit")) game.popState();
                break;
        }
    }

}
