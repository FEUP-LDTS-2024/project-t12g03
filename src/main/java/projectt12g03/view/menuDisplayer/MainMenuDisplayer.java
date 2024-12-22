package projectt12g03.view.menuDisplayer;

import projectt12g03.gui.GUI;
import projectt12g03.view.Sprite;
import projectt12g03.model.menu.MainMenu;
import projectt12g03.view.ScreenDisplayer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainMenuDisplayer extends MenuDisplayer<MainMenu> {

    public MainMenuDisplayer(MainMenu model) throws IOException {
        super(model);
    }

    @Override
    protected Map<Integer, Sprite> createBackGroundMap() throws IOException {
        Map<Integer, Sprite> backGroundMap = new HashMap<>();
        backGroundMap.put(0, new Sprite("sprites/menu/mainMenu/mainmenu1.png"));
        backGroundMap.put(1, new Sprite("sprites/menu/mainMenu/mainmenu2.png"));
        backGroundMap.put(2, new Sprite("sprites/menu/mainMenu/mainmenu3.png"));
        backGroundMap.put(3, new Sprite("sprites/menu/mainMenu/mainmenu4.png"));
        return backGroundMap;
    }
}
