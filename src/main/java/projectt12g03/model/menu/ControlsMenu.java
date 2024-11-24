package projectt12g03.model.menu;

import projectt12g03.model.Vector;

public class ControlsMenu extends Menu{

    public ControlsMenu(){
        entries.clear();
        entries.put("go back", new Vector(18, 16));

        entriesList.addAll(entries.keySet());
        currentEntry = 0;
    }
}
