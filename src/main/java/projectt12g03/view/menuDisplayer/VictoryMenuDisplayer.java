package projectt12g03.view.menuDisplayer;

import projectt12g03.model.menu.VictoryMenu;
import projectt12g03.view.Sprite;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class VictoryMenuDisplayer extends MenuDisplayer<VictoryMenu> {
    public VictoryMenuDisplayer(VictoryMenu model) throws IOException { super(model); }

    @Override
    protected Map<Integer, Sprite> createBackGroundMap() throws IOException {
        Map<Integer, Sprite> backGroundMap = new HashMap<>();
        backGroundMap.put(0, new Sprite("sprites/menu/victoryMenu/victorymenu1.png"));
        backGroundMap.put(1, new Sprite("sprites/menu/victoryMenu/victorymenu2.png"));
        return backGroundMap;
    }
}
