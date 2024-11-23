package projectt12g03.model.game.room;


import projectt12g03.Game;
import projectt12g03.model.game.elements.Player;
import projectt12g03.model.game.elements.Wall;

import java.util.ArrayList;
import java.util.List;

public class RoomBuilder {
    //private final List<String> lines;
    private final int roomCode;

    public Room createRoom() {
        Room room = new Room(getWidth(), getHeight(), roomCode);

        room.setWalls(createWalls());
        room.setPlayer(new Player(10, 5, room)); //probably going to change in the future;

        return room;
    }

    public RoomBuilder(int roomCode) {
        this.roomCode = roomCode;
    }

    protected int getWidth() { return 40; } //temporary until we read maps

    protected int getHeight() { return 20; } //temporary until we read maps

    protected List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < Game.terminalWidth; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, Game.terminalHeight - 1));
        }
        for (int r = 1; r < Game.terminalHeight - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(Game.terminalWidth - 1, r));
        }
        return walls;
    }
}
