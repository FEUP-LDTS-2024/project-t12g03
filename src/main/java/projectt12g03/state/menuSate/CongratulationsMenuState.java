package projectt12g03.state.menuSate;

import projectt12g03.controller.Controller;
import projectt12g03.controller.menuController.CongratulationsMenuController;
import projectt12g03.controller.menuController.EntriesController;
import projectt12g03.model.menu.CongratulationsMenu;
import projectt12g03.state.State;
import projectt12g03.view.ScreenDisplayer;
import projectt12g03.view.menuDisplayer.CongratulationsMenuDisplayer;

import java.io.IOException;

public class CongratulationsMenuState extends State<CongratulationsMenu> {
    public CongratulationsMenuState(CongratulationsMenu model) throws IOException {
        super(model);
    }

    @Override
    protected Controller<CongratulationsMenu> getController() {
        return new CongratulationsMenuController(getModel(), new EntriesController(getModel()));
    }

    @Override
    protected ScreenDisplayer<CongratulationsMenu> getScreenDisplayer() {
        return new CongratulationsMenuDisplayer(getModel());
    }

    @Override
    protected boolean allowInputSpam() {
        return false;
    }
}
