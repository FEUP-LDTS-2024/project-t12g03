package projectt12g03.model.menu;

import java.util.HashMap;
import java.util.Map;

import static projectt12g03.model.menu.Menu.Type.*;

public class VictoryMenu extends Menu {

    @Override
    protected Map<Integer, Type> createEntries() {
        Map<Integer, Menu.Type> entries = new HashMap<>();

        entries.put(0, RESTART);
        entries.put(1, EXIT);

        return entries;
    }
}
