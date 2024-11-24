package projectt12g03.controller.menuController;

import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.GUI;
import projectt12g03.model.menu.ControlsMenu;
import projectt12g03.model.menu.MainMenu;

import java.io.IOException;
import java.util.Objects;

public class ControlsMenuController extends Controller<ControlsMenu> {
    public ControlsMenuController(ControlsMenu model) { super(model); }

    @Override
    public void step(Game game, GUI.userInput action, long time) throws IOException {
        if (Objects.requireNonNull(action) == GUI.userInput.SELECT) {
            if (getModel().getCurrentEntry().equals("go back")) game.popState();
        }
    }
}
