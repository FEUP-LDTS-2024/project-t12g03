package projectt12g03.state.menuState;

import projectt12g03.controller.Controller;
import projectt12g03.controller.menuController.EntriesController;
import projectt12g03.controller.menuController.GameOverMenuController;
import projectt12g03.model.menu.GameOverMenu;
import projectt12g03.state.State;
import projectt12g03.view.ScreenDisplayer;
import projectt12g03.view.menuDisplayer.GameOverMenuDisplayer;

import java.io.IOException;

public class GameOverMenuState extends State<GameOverMenu> {
    public GameOverMenuState(GameOverMenu model) throws IOException {
        super(model);
    }

    @Override
    protected Controller<GameOverMenu> getController() {
        return new GameOverMenuController(getModel(), new EntriesController(getModel()));
    }

    @Override
    protected ScreenDisplayer<GameOverMenu> getScreenDisplayer() throws IOException {
        return new GameOverMenuDisplayer(getModel());
    }

    @Override
    protected boolean allowInputSpam() {
        return false;
    }
}