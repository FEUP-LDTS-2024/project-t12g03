package projectt12g03.model.menu;

import projectt12g03.model.Vector;
import projectt12g03.model.game.room.Room;
import projectt12g03.state.State;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PauseMenu {
    private final Map<String, Vector> entries = new LinkedHashMap<>();
    private final List<String> entriesList = new ArrayList<>();
    private final Room room;
    private int currentEntry = 0;


    public PauseMenu(Room room) {
        this.room = room;

        entries.put("resume", new Vector(18, 9));
        entries.put("exit", new Vector(18, 12));

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

    public Room getRoom() { return room; }

    public String getCurrentEntry() {
        return entriesList.get(currentEntry);
    }

    public int getNumberEntries() { return entriesList.size(); }

    public List<String> getEntries() { return entriesList; }

    public Vector getEntryPosition(String entryName) { return entries.get(entryName); }
}
