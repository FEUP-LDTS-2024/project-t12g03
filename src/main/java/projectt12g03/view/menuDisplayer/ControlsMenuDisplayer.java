package projectt12g03.view.menuDisplayer;

import projectt12g03.model.menu.ControlsMenu;
import projectt12g03.view.Sprite;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControlsMenuDisplayer extends MenuDisplayer<ControlsMenu> {

    public ControlsMenuDisplayer(ControlsMenu model) throws IOException { super(model); }

    @Override
    protected Map<Integer, Sprite> createBackGroundMap() throws IOException {
        Map<Integer, Sprite> backGroundMap = new HashMap<>();
        backGroundMap.put(0, new Sprite("sprites/menu/controlsMenu/controlsmenu.png"));
        return backGroundMap;
    }
}
