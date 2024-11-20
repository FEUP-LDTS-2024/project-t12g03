package projectt12g03.model;

import java.util.Arrays;
import java.util.List;

public class Menu {
    private final List<String> entries;
    private int currentEntry = 0;

    public Menu() {
        this.entries = Arrays.asList("Start", "Exit");
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
        return entries.get(currentEntry);
    }

    public int getNumberEntries() { return this.entries.size(); }

}
