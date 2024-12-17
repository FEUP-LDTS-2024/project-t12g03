package projectt12g03.controller.menuController;

import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.GUI;
import projectt12g03.model.menu.CreditsMenu;
import projectt12g03.model.menu.MainMenu;

import java.io.IOException;
import java.util.Objects;

public class CreditsMenuController extends MenuController<CreditsMenu> {

    public CreditsMenuController(CreditsMenu menu, EntriesController entriesController) {
        super(menu, entriesController);
    }
}
