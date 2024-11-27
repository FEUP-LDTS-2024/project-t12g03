package projectt12g03.model.game.room;


import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.Monster;
import projectt12g03.model.game.elements.Player;
import projectt12g03.model.game.elements.Wall;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RoomBuilder {
    private final List<String> lines;
    private final int roomCode;

    public Room createRoom(Player player) {
        Room room = new Room(getWidth(), getHeight(), roomCode);

        room.setWalls(createWalls());
        room.setMonsters(createMonsters(room));
        room.setPlayer(createPlayer(room)); //probably going to change in the future;

        return room;
    }

    public RoomBuilder(int roomCode) throws IOException {
        this.roomCode = roomCode;

        URL resource = RoomBuilder.class.getResource("/rooms/room" + roomCode + ".lvl");
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));


        this.lines = readLines(br);
    }

    private List<String> readLines(BufferedReader br) throws IOException {
        List<String> lines = new ArrayList<>();
        for (String line; (line = br.readLine()) != null; )
            lines.add(line);
        return lines;
    }

    protected int getWidth() {
        int width = 0;
        for (String line : lines)
            width = Math.max(width, line.length());
        return width;
    }

    protected int getHeight() { return lines.size(); }

    protected List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == '#') walls.add(new Wall(x, y));
        }

        return walls;
    }

    protected List<Monster> createMonsters(Room room) {
        List<Monster> monsters = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'M') monsters.add(new Monster(x,y, room));
        }

        return monsters;
    }

    protected Player createPlayer(Room room) {
        for (int y = 0; y < lines.size() - 4; y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++){
                if (line.charAt(x) == 'P'){
                    return new Player(x, y, room);
                }
            }
        }
        throw new IllegalStateException("Player not found within the level file!");
    }
}
