package projectt12g03.state.menuState;

import projectt12g03.controller.Controller;

import projectt12g03.controller.menuController.CreditsMenuController;
import projectt12g03.controller.menuController.EntriesController;
import projectt12g03.model.menu.CreditsMenu;
import projectt12g03.state.State;
import projectt12g03.view.ScreenDisplayer;
import projectt12g03.view.menuDisplayer.CreditsMenuDisplayer;

import java.io.IOException;

public class CreditsMenuState extends State<CreditsMenu> {
    public CreditsMenuState(CreditsMenu model) throws IOException {
        super(model);
    }

    @Override
    protected Controller<CreditsMenu> getController() { return new CreditsMenuController(getModel(), new EntriesController(getModel())); }

    @Override
    protected ScreenDisplayer<CreditsMenu> getScreenDisplayer() throws IOException { return new CreditsMenuDisplayer(getModel()); }

    @Override
    protected boolean allowInputSpam() {
        return false;
    }
}

