package projectt12g03.model.menu;

import projectt12g03.model.Vector;

public class CreditsMenu extends Menu{
    public CreditsMenu(){
        entries.clear();
        entries.put("go back", new Vector(16, 18));

        entriesList.addAll(entries.keySet());
        currentEntry = 0;
    }
}