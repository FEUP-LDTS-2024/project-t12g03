package projectt12g03.view.menuDisplayer;

import projectt12g03.gui.GUI;
import projectt12g03.view.Sprite;
import projectt12g03.model.menu.MainMenu;
import projectt12g03.view.ScreenDisplayer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainMenuDisplayer extends ScreenDisplayer<MainMenu> {
    private final Map<Integer, Sprite> backGroundMap;

    public MainMenuDisplayer(MainMenu model) throws IOException {
        super(model);
        backGroundMap = new HashMap<>();
        backGroundMap.put(0, new Sprite("sprites/menu/background1.png"));
        backGroundMap.put(1, new Sprite("sprites/menu/background2.png"));
        backGroundMap.put(2, new Sprite("sprites/menu/background3.png"));
    }

    @Override
    public void draw(GUI gui, long frameCount) throws IOException {
        gui.clear();
        int entry = getModel().getCurrentEntryNumber();
        Sprite image = backGroundMap.get(entry);
        image.draw(gui, 0, 0);
        gui.refresh();
    }

}
