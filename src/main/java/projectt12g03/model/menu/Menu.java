package projectt12g03.model.menu;


import java.util.*;

public abstract class Menu {
    public enum Type { START, CONTROLS, CREDITS, EXIT, GO_BACK, RESUME }

    private final Map<Integer, Type> entries;
    private int currentEntry = 0;

    public Menu() {
        this.entries = createEntries();
    }

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

    protected abstract Map<Integer, Type> createEntries();

    public Type getCurrentEntry() {
        return entries.get(currentEntry);
    }

    public int getNumberEntries() { return entries.size(); }
}
