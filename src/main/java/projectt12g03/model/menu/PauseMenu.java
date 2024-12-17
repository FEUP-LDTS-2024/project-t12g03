package projectt12g03.model.menu;

import java.util.*;

import static projectt12g03.model.menu.Menu.Type.*;

public class PauseMenu extends Menu{

    @Override
    protected Map<Integer, Type> createEntries() {
        Map<Integer, Type> entries = new HashMap<>();

        entries.put(0, RESUME);
        entries.put(1, EXIT);

        return entries;
    }
}
