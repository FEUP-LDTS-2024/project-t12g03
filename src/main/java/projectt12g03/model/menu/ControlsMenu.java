package projectt12g03.model.menu;

import java.util.HashMap;
import java.util.Map;

import static projectt12g03.model.menu.Menu.Type.GO_BACK;

public class ControlsMenu extends Menu{

    @Override
    protected Map<Integer, Type> createEntries() {
        Map<Integer, Type> entries = new HashMap<>();

        entries.put(0, GO_BACK);

        return entries;
    }
}
