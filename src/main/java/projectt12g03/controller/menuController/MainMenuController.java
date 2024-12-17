package projectt12g03.controller.menuController;

import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.game.room.RoomManager;
import projectt12g03.model.menu.ControlsMenu;
import projectt12g03.model.menu.CreditsMenu;
import projectt12g03.model.menu.MainMenu;
import projectt12g03.state.menuSate.ControlsMenuState;
import projectt12g03.state.GameState;
import projectt12g03.state.menuSate.CreditsMenuState;

import java.io.IOException;

public class MainMenuController extends MenuController<MainMenu> {

    public MainMenuController(MainMenu menu, EntriesController entriesController) {
        super(menu, entriesController);
    }
}
