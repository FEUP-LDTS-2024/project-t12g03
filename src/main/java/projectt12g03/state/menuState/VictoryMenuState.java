package projectt12g03.state.menuState;

import projectt12g03.controller.Controller;
import projectt12g03.controller.menuController.VictoryMenuController;
import projectt12g03.controller.menuController.EntriesController;
import projectt12g03.model.menu.VictoryMenu;
import projectt12g03.state.State;
import projectt12g03.view.ScreenDisplayer;
import projectt12g03.view.menuDisplayer.VictoryMenuDisplayer;

import java.io.IOException;

public class VictoryMenuState extends State<VictoryMenu> {

    public VictoryMenuState(VictoryMenu model) throws IOException {
        super(model);
    }

    @Override
    protected Controller<VictoryMenu> getController() {
        return new VictoryMenuController(getModel(), new EntriesController(getModel()));
    }

    @Override
    protected ScreenDisplayer<VictoryMenu> getScreenDisplayer() throws IOException {
        return new VictoryMenuDisplayer(getModel());
    }

    @Override
    protected boolean allowInputSpam() {
        return false;
    }
}
