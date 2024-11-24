package projectt12g03.model.menu;

import projectt12g03.model.Vector;

public class MainMenu extends Menu {


    public MainMenu() {
        entries.clear();

        entries.put("start", new Vector(18, 7));
        entries.put("controls", new Vector(17, 10));
        entries.put("credits", new Vector(17, 13));
        entries.put("exit", new Vector(18, 16));

        entriesList.addAll(entries.keySet());
        currentEntry = 0;
    }
}
