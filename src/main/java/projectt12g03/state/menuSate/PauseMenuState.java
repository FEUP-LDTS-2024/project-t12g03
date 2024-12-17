package projectt12g03.state.menuSate;

import projectt12g03.controller.Controller;
import projectt12g03.controller.menuController.EntriesController;
import projectt12g03.controller.menuController.PauseMenuController;
import projectt12g03.model.menu.PauseMenu;
import projectt12g03.state.State;
import projectt12g03.view.ScreenDisplayer;
import projectt12g03.view.menuDisplayer.PauseMenuDisplayer;

import java.io.IOException;

public class PauseMenuState extends State<PauseMenu> {

    public PauseMenuState(PauseMenu model) throws IOException {
        super(model);
    }

    @Override
    protected Controller<PauseMenu> getController() {
        return new PauseMenuController(getModel(), new EntriesController(getModel()));
    }

    @Override
    protected ScreenDisplayer<PauseMenu> getScreenDisplayer() throws IOException { return new PauseMenuDisplayer(getModel()); }

    @Override
    protected boolean allowInputSpam() { return false; }
}