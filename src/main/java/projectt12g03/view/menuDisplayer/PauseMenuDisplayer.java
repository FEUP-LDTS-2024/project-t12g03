package projectt12g03.view.menuDisplayer;

import projectt12g03.model.menu.PauseMenu;
import projectt12g03.view.Sprite;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PauseMenuDisplayer extends MenuDisplayer<PauseMenu> {

    public PauseMenuDisplayer(PauseMenu model) throws IOException { super(model); }

    @Override
    protected Map<Integer, Sprite> createBackGroundMap() throws IOException {
        Map<Integer, Sprite> backGroundMap = new HashMap<>();
        backGroundMap.put(0, new Sprite("sprites/menu/pausedMenu/pausedmenu1.png"));
        backGroundMap.put(1, new Sprite("sprites/menu/pausedMenu/pausedmenu2.png"));
        return backGroundMap;
    }
}
