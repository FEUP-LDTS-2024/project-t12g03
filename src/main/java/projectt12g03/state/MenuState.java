package projectt12g03.state;


import projectt12g03.controller.Controller;
import projectt12g03.controller.MenuController;
import projectt12g03.model.Menu;

import java.io.IOException;

public class MenuState extends State<Menu> {

    public MenuState(Menu model) throws IOException {
        super(model);
    }

    @Override
    protected Controller<Menu> getController() {
        return new MenuController(getModel());
    }

}
