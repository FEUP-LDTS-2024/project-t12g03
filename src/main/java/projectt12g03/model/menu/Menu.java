package projectt12g03.model.menu;

import projectt12g03.model.Vector;

import java.util.*;

public class Menu {
    private final Map<String, Vector> entries = new LinkedHashMap<>();
    private final List<String> entriesList = new ArrayList<>();
    private int currentEntry = 0;


    public Menu() {
        entries.put("start", new Vector(18, 7));
        entries.put("controls", new Vector(18, 10));
        entries.put("credits", new Vector(18, 13));
        entries.put("exit", new Vector(18, 16));

        entriesList.addAll(entries.keySet());
    }

    public void moveUp() {
        currentEntry--;
        if (currentEntry < 0)
            currentEntry = 0;
    }

    public void moveDown() {
        currentEntry++;
        if (currentEntry > getNumberEntries() - 1)
            currentEntry = getNumberEntries() - 1;
    }

    public String getCurrentEntry() {
        return entriesList.get(currentEntry);
    }

    public int getNumberEntries() { return entriesList.size(); }

    public List<String> getEntries() { return entriesList; }

    public Vector getEntryPosition(String entryName) { return entries.get(entryName); }
}
