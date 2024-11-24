package projectt12g03.model.menu;

import projectt12g03.model.Vector;

public class MainMenu extends Menu {


    public MainMenu() {
        entries.clear();
        currentEntry = 0;
        entries.put("start", new Vector(18, 7));
        entries.put("controls", new Vector(18, 10));
        entries.put("credits", new Vector(18, 13));
        entries.put("exit", new Vector(18, 16));

        entriesList.addAll(entries.keySet());
    }
}
