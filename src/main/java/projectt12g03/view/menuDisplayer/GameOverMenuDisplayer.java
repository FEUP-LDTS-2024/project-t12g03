package projectt12g03.view.menuDisplayer;

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
    }

    @Override
    protected Map<Integer, Sprite> createBackGroundMap() throws IOException {
        Map<Integer, Sprite> backGroundMap = new HashMap<>();
        backGroundMap.put(0, new Sprite("sprites/menu/background1.png"));
        backGroundMap.put(1, new Sprite("sprites/menu/background2.png"));
        return backGroundMap;
    }
}
