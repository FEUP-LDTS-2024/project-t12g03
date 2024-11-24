package projectt12g03.model.menu;

import projectt12g03.model.Vector;
import projectt12g03.model.game.room.Room;
import projectt12g03.state.State;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PauseMenu extends Menu{
    private final Room room;


    public PauseMenu(Room room) {
        this.room = room;

        entries.clear();
        entries.put("resume", new Vector(17, 9));
        entries.put("exit", new Vector(18, 12));

        entriesList.addAll(entries.keySet());
        currentEntry = 0;
    }

    public Room getRoom() { return room; }
}
