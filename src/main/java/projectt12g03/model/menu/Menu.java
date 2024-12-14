package projectt12g03.model.menu;

import projectt12g03.model.Vector;

import java.util.*;

public abstract class Menu {
    protected final Map<String, Vector> entries = new LinkedHashMap<>();
    protected final List<String> entriesList = new ArrayList<>();
    protected int currentEntry = 0;
    
    public void moveUp() {
        currentEntry--;
        if (currentEntry < 0)
            currentEntry = getNumberEntries() - 1;
    }

    public void moveDown() {
        currentEntry++;
        if (currentEntry > getNumberEntries() - 1)
            currentEntry = 0;
    }

    public int getCurrentEntryNumber() {
        return currentEntry;
    }

    public String getCurrentEntry() {
        return entriesList.get(currentEntry);
    }

    public int getNumberEntries() { return entriesList.size(); }

    public List<String> getEntries() { return entriesList; }

    public Vector getEntryPosition(String entryName) { return entries.get(entryName); }


}
