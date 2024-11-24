package projectt12g03.state.menu;


import projectt12g03.controller.Controller;
import projectt12g03.controller.menu.MenuController;
import projectt12g03.model.menu.Menu;
import projectt12g03.state.State;
import projectt12g03.view.menu.MenuDisplayer;
import projectt12g03.view.ScreenDisplayer;

import java.io.IOException;

public class MenuState extends State<Menu> {

    public MenuState(Menu model) throws IOException {
        super(model);
    }

    @Override
    protected Controller<Menu> getController() {
        return new MenuController(getModel());
    }

    @Override
    protected ScreenDisplayer<Menu> getScreenDisplayer() { return new MenuDisplayer(getModel()); }

    @Override
    protected boolean allowInputSpam() { return false; }
}
