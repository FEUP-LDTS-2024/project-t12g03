package projectt12g03.state.menuSate;

import projectt12g03.controller.Controller;
import projectt12g03.controller.menuController.ControlsMenuController;
import projectt12g03.model.menu.ControlsMenu;
import projectt12g03.model.menu.CreditsMenu;
import projectt12g03.state.State;
import projectt12g03.view.ScreenDisplayer;
import projectt12g03.view.menuDisplayer.ControlsMenuDisplayer;

import java.io.IOException;

public class CreditsState extends State<CreditsMenu> {
    public CreditsState(CreditsMenu model) throws IOException {
        super(model);
    }

    @Override
    protected Controller<CreditsMenu> getController() {
        return new CreditsMenuController(getModel());
    }

    @Override
    protected ScreenDisplayer<CreditsMenu> getScreenDisplayer() {
        return new CreditsMenuDisplayer(getModel());
    }

    @Override
    protected boolean allowInputSpam() {
        return false;
    }
}
}
