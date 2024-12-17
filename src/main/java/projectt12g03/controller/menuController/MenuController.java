package projectt12g03.controller.menuController;

import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.GUI;
import projectt12g03.model.menu.Menu;

import java.io.IOException;

public abstract class MenuController<T extends Menu> extends Controller<T> {
    private final EntriesController entriesController;

    public MenuController(T menu, EntriesController entriesController) {
        super(menu);
        this.entriesController = entriesController;
    }

    @Override
    public void step(Game game, GUI.userInput action, long frameCount) throws IOException {
        switch (action) {
            case UP:
                getModel().moveUp();
                break;
            case DOWN:
                getModel().moveDown();
                break;
            case SELECT:
                entriesController.step(game, action, frameCount);
        }
    }
}
