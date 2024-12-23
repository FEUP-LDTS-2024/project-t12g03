package projectt12g03.model.menu;

import java.util.HashMap;
import java.util.Map;

import static projectt12g03.model.menu.Menu.Type.*;

public class GameOverMenu extends Menu {
    private String type;

    @Override
    protected Map<Integer, Menu.Type> createEntries() {
        Map<Integer, Menu.Type> entries = new HashMap<>();

        entries.put(0, RESTART);
        entries.put(1, EXIT);

        return entries;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
