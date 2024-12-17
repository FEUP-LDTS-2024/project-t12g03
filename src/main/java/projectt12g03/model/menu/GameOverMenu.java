package projectt12g03.model.menu;

import java.util.HashMap;
import java.util.Map;

import static projectt12g03.model.menu.Menu.Type.*;

public class GameOverMenu extends Menu {
    private final int type;

    public GameOverMenu(char type) {
        this.type = type;
    }

    @Override
    protected Map<Integer, Menu.Type> createEntries() {
        Map<Integer, Menu.Type> entries = new HashMap<>();

        entries.put(0, RESTART);
        entries.put(1, EXIT);

        return entries;
    }

    public void getType() { return type; }
}
