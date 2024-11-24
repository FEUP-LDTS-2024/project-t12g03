package projectt12g03.state.menuSate;

import projectt12g03.controller.Controller;
import projectt12g03.controller.menuController.ControlsMenuController;
import projectt12g03.model.menu.ControlsMenu;
import projectt12g03.state.State;
import projectt12g03.view.menuDisplayer.ControlsMenuDisplayer;
import projectt12g03.view.ScreenDisplayer;

import java.io.IOException;

public class ControlsMenuState extends State<ControlsMenu> {
    public ControlsMenuState(ControlsMenu model) throws IOException {
        super(model);
    }

    @Override
    protected Controller<ControlsMenu> getController() {
        return new ControlsMenuController(getModel());
    }

    @Override
    protected ScreenDisplayer<ControlsMenu> getScreenDisplayer() {
        return new ControlsMenuDisplayer(getModel());
    }

    @Override
    protected boolean allowInputSpam() {
        return false;
    }
}
