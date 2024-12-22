package projectt12g03.state.menuState;


import projectt12g03.controller.Controller;
import projectt12g03.controller.menuController.EntriesController;
import projectt12g03.controller.menuController.MainMenuController;
import projectt12g03.model.menu.MainMenu;
import projectt12g03.state.State;
import projectt12g03.view.menuDisplayer.MainMenuDisplayer;
import projectt12g03.view.ScreenDisplayer;

import java.io.IOException;

public class MainMenuState extends State<MainMenu> {

    public MainMenuState(MainMenu model) throws IOException {
        super(model);
    }

    @Override
    protected Controller<MainMenu> getController() {
        return new MainMenuController(getModel(), new EntriesController(getModel()));
    }

    @Override
    protected ScreenDisplayer<MainMenu> getScreenDisplayer() throws IOException { return new MainMenuDisplayer(getModel()); }

    @Override
    protected boolean allowInputSpam() { return false; }
}
