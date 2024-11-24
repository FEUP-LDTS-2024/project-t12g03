package projectt12g03.controller.menuController;

import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.menu.PauseMenu;

import java.io.IOException;

public class PauseMenuController extends Controller<PauseMenu> {
    public PauseMenuController(PauseMenu pauseMenu) {
        super(pauseMenu);
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
                if (getModel().getCurrentEntry().equals("resume")) game.popState();
                if (getModel().getCurrentEntry().equals("exit")) {
                    game.popState();
                    game.popState();
                }
                break;
        }
    }
}
