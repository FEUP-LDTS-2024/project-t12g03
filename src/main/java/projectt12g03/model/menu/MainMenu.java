package projectt12g03.model.menu;

import java.util.HashMap;
import java.util.Map;

import static projectt12g03.model.menu.Menu.Type.*;

public class MainMenu extends Menu {

    @Override
    protected Map<Integer, Type> createEntries() {
        Map<Integer, Type> entries = new HashMap<>();

        entries.put(0, START);
        entries.put(1, CONTROLS);
        entries.put(2, CREDITS);
        entries.put(3, EXIT);

        return entries;
    }
}
