package projectt12g03.model.game.room;


import projectt12g03.model.game.elements.stationary.Laser;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.Element;
import projectt12g03.model.game.elements.movable.hostile.Monster;
import projectt12g03.model.game.elements.movable.player.Player;
import projectt12g03.model.game.elements.stationary.*;


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
    private final Player player;

    private Map<Element, Vector> sortedElementsMap = new HashMap<>();
    private Map<Integer, Room> roomsMap = new HashMap<>();
    private List<String> lines;
    private Integer roomCode = 1;
    private Room room;

    public RoomManager() throws IOException {
        player = new Player(0, 0, null);

        loadRoom(roomCode);
    }

    public void loadRoom(Integer roomCode) throws IOException {
        this.roomCode = roomCode;
        if(!roomsMap.containsKey(roomCode)) {
            URL resource = RoomManager.class.getResource("/rooms/room" + roomCode + ".lvl");
            BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));

            this.lines = readLines(br);
            this.room = createRoom();
            if (roomCode != 1) {
                Vector playerPosition = player.calculateNewPosition();
                player.setPosition(playerPosition);
            }
        }
        else {
            this.room = roomsMap.get(roomCode);

            Vector playerPosition = player.calculateNewPosition();
            player.setPosition(playerPosition);
        }

        room.setHeight(getHeight());
        room.setWidth(getWidth());
        player.setRoom(room);
    }

    public Room createRoom() {
        Room room = new Room(getWidth(), getHeight());

        room.setFloors(createFloors());
        room.setWalls(createWalls());
        room.setDoors(createDoors());
        room.setChest(createChest());
        room.setKey(createKey());
        room.setMonsters(createMonsters(room));
        room.setLasers(createLasers(room));
        room.setPlayer(createPlayer());

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

    public Player getPlayer() {
        return player;
    }

    private List<Floor> createFloors() {
        List<Floor> floors = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == ' ' || line.charAt(x) == 'C' || line.charAt(x) == 'K' ||
                        line.charAt(x) == 'M' || line.charAt(x) == 'P' || isDigit(line.charAt(x))) {
                    floors.add(new Floor(x * 16, y * 16));
                }
        }

        return floors;
    }

    protected List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == '#') walls.add(new Wall(x*16, y*16));
        }

        return walls;
    }

    protected List<Door> createDoors() {
        List<Door> doors = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (isDigit(line.charAt(x))) {
                    int n = line.charAt(x) - '0';
                    int[] constructorValues = checkDoorPosition(x,y);
                    doors.add(new Door(x*16, y*16, constructorValues[0], constructorValues[1], constructorValues[2], constructorValues[3], n));
                }
            }
        }
        return doors;
    }

    protected int[] checkDoorPosition(int x, int y) {    //hard coded change when better solution found
        int dx;
        int dy;
        int width;
        int height;

        if (y == 0) {
            dx = 0;
            dy = 0;
            width = 16;
            height = 1;
        }
        else if (y == 9) {
            dx = 0;
            dy = 15;
            width = 16;
            height = 1;
        }
        else if (x == 0) {
            dx = 0;
            dy = 0;
            width = 1;
            height = 16;
        }
        else {
            dx = 15;
            dy = 0;
            width = 1;
            height = 16;
        }
        return new int[]{dx, dy, width, height};
    }

    protected Chest createChest() {
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'C') {
                    return new Chest(x*16, y*16);
                }
            }
        }
        throw new IllegalStateException("Chest not found within the room file!");
    }

    protected Key createKey() {
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'K') {
                    return new Key(x*16, y*16);
                }
            }
        }
        throw new IllegalStateException("Key not found within the room file!");
    }

    protected List<Monster> createMonsters(Room room) {
        List<Monster> monsters = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == 'M') monsters.add(new Monster(x*16,y*16, room));
        }

        return monsters;
    }

    protected Player createPlayer() {
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++){
                if (line.charAt(x) == 'P'){
                    player.setPosition(new Vector(x*16,y*16));
                    return player;
                }
            }
        }
        throw new IllegalStateException("Player not found within the room file!");
    }

    protected List<Laser> createLasers(Room room){
        List<Laser> lasers = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'L') lasers.add(new Laser(x*16, y*16, "RIGHT", room));
                if (line.charAt(x) == 'J') lasers.add(new Laser(x*16, y*16, "LEFT", room ));
            }
        }
        return lasers;
    }
}
