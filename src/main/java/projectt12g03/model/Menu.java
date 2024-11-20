package projectt12g03.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {
    private final int xAlignment = 18;

    private Map<String, Vector> entries = new HashMap<>();
    private List<String> entriesList = new ArrayList<>();
    private int currentEntry = 0;


    public Menu() {
        entries.put("Start", new Vector(xAlignment, 10));
        entries.put("Exit", new Vector(xAlignment, 13));

        entriesList.addAll(entries.keySet());
    }

    public void moveUp() {
        currentEntry--;
        if (currentEntry < 0)
            currentEntry = getNumberEntries() - 1;
    }

    public void moveDown() {
        currentEntry++;
        if (currentEntry > getNumberEntries() -1)
            currentEntry = 0;
    }

    public String getCurrentEntry() {
        return entriesList.get(currentEntry);
    }

    public int getNumberEntries() { return entriesList.size(); }

    public List<String> getEntries() { return entriesList; }

    public Vector getEntryPosition(String entryName) { return entries.get(entryName); }

    public int getxAlignment() { return xAlignment; }
}
