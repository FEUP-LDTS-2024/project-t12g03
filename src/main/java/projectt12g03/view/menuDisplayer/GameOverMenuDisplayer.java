package projectt12g03.view.menuDisplayer;

import projectt12g03.gui.GUI;
import projectt12g03.model.menu.GameOverMenu;
import projectt12g03.model.menu.MainMenu;
import projectt12g03.view.ScreenDisplayer;
import projectt12g03.view.Sprite;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GameOverMenuDisplayer extends MenuDisplayer<GameOverMenu> {
    public GameOverMenuDisplayer(GameOverMenu model) throws IOException { super(model); }

    @Override
    protected Map<Integer, Sprite> createBackGroundMap() throws IOException {
        Map<Integer, Sprite> backGroundMap = new HashMap<>();
        backGroundMap.put(0, new Sprite("sprites/menu/background1.png"));
        backGroundMap.put(1, new Sprite("sprites/menu/background2.png"));
        backGroundMap.put(2, new Sprite("sprites/menu/background3.png"));
        return backGroundMap;
    }
}
