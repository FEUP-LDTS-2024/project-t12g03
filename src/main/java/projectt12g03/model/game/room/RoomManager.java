package projectt12g03.model.game.room;

import projectt12g03.model.game.elements.Monster;
import projectt12g03.model.game.elements.Player;
import projectt12g03.model.game.elements.Wall;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Character.isDigit;

public class RoomManager {
    private Map<Integer, Room> roomsMap = new HashMap<>();
    private List<String> lines;
    private Integer roomCode = 1;
    private Room room;

    public RoomManager() throws IOException {

        URL resource = RoomBuilder.class.getResource("/rooms/room" + roomCode + ".lvl");
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));

        this.lines = readLines(br);

        roomBuilder(roomCode); //change in the future
    }

    public Room roomBuilder(Integer roomCode) throws IOException {
        this.roomCode = roomCode;
        if(!roomsMap.containsKey(roomCode)) {
            URL resource = RoomBuilder.class.getResource("/rooms/room" + roomCode + ".lvl");
            BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));

            this.lines = readLines(br);

            this.room = createRoom(new Player(0, 0, null));
        }
        else {
            this.room = roomsMap.get(roomCode);
        }

        return room;
    }

    public Room createRoom(Player player) {
        Room room = new Room(getWidth(), getHeight(), roomCode);

        room.setWalls(createWalls());
        room.setDoors(createDoors());
        room.setMonsters(createMonsters(room));
        room.setPlayer(createPlayer(room));

        roomsMap.put(roomCode, room);

        return room;
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

    public Room getRoom() { return room; }

    protected List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == '#') walls.add(new Wall(x, y));
        }

        return walls;
    }

    protected Map<Integer, List<Wall>> createDoors() {
        Map<Integer, List<Wall>> doorsMap = new HashMap<>();
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            int n;
            for (int x = 0; x < line.length(); x++) {
                if (isDigit(line.charAt(x))) {
                    n = line.charAt(x) - '0';
                    if(doorsMap.containsKey(n) && n != 0) doorsMap.get(n).add(new Wall(x,y));
                    else if ((!doorsMap.containsKey(n)) && n != 0) {
                        doorsMap.put(n, new ArrayList<>());
                        doorsMap.get(n).add(new Wall(x,y));
                    }
                }
            }
        }

        return doorsMap;
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
        for (int y = 0; y < lines.size(); y++) {
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
