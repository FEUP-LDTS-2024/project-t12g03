package projectt12g03.view.menuDisplayer;

import projectt12g03.gui.GUI;
import projectt12g03.model.menu.GameOverMenu;
import projectt12g03.view.Sprite;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GameOverMenuDisplayer extends MenuDisplayer<GameOverMenu> {
    private final Map<String, Sprite> layers; //Used for different Game Over Menu types

    public GameOverMenuDisplayer(GameOverMenu model) throws IOException {
        super(model);
        layers = new HashMap<>();
        layers.put("LIVED_KILLED", new Sprite("sprites/menu/gameoverMenu/ends_kills_gameovermenu.png"));
        layers.put("DIED_KILLED", new Sprite("sprites/menu/gameoverMenu/killed_kills_gameovermenu.png"));
        layers.put("DIED_!KILLED", new Sprite("sprites/menu/gameoverMenu/killed_nokills_gameovermenu.png"));
    }

    @Override
    public void draw(GUI gui, long framecount) throws IOException {
        gui.clear();
        int entry = getModel().getCurrentEntryNumber();
        Sprite image = backGroundMap.get(entry);
        Sprite layer = layers.get(getModel().getType());
        image.draw(gui, 0, 0);
        layer.draw(gui, 0,0);
        gui.refresh();
    }

    @Override
    protected Map<Integer, Sprite> createBackGroundMap() throws IOException {
        Map<Integer, Sprite> backGroundMap = new HashMap<>();
        backGroundMap.put(0, new Sprite("sprites/menu/gameoverMenu/gameovermenu1.png"));
        backGroundMap.put(1, new Sprite("sprites/menu/gameoverMenu/gameovermenu2.png"));
        return backGroundMap;
    }
}
