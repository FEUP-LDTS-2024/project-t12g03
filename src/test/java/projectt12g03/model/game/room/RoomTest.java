package projectt12g03.model.game.room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.Monster;
import projectt12g03.model.game.elements.Player;
import projectt12g03.model.game.elements.Wall;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    private Room room;
    private Player player;
    private List<Wall> walls;
    private List<Monster> monsters;
    private Map<Integer, List<Wall>> doors;

    @BeforeEach
    void setup() {
        room = new Room(10, 10, 1);
        player = mock(Player.class);
        walls = new ArrayList<>();
        monsters = new ArrayList<>();
        doors = new HashMap<>();

        room.setPlayer(player);
        room.setWalls(walls);
        room.setMonsters(monsters);
        room.setDoors(doors);
    }

    @Test
    void testCheckCollisionsNoCollisions() {

        walls.add(new Wall(3, 3));
        monsters.add(new Monster(1, 2, room));
        List<Wall> doorWalls = new ArrayList<>();
        doorWalls.add(new Wall(8, 8));
        doors.put(1, doorWalls);


        assertFalse(room.checkCollisions(new Vector(5, 5)));
    }

    @Test
    void testCheckCollisionsWithWall() {
        walls.add(new Wall(3, 3));
        assertTrue(room.checkCollisions(new Vector(3, 3)));
    }

    @Test
    void testCheckCollisionsWithMonster() {
        Monster monster = mock(Monster.class);
        when(monster.getPosition()).thenReturn(new Vector(4, 4));
        monsters.add(monster);

        assertTrue(room.checkCollisions(new Vector(4, 4)));
    }

    @Test
    void testCheckCollisionsWithTransition() {
        List<Wall> doorWalls = new ArrayList<>();
        doorWalls.add(new Wall(8, 8));
        doors.put(1, doorWalls);

        assertTrue(room.checkCollisions(new Vector(8, 8)));
    }

    @Test
    void testCheckMonsterCollisions() {
        Monster monster1 = mock(Monster.class);
        Monster monster2 = mock(Monster.class);
        when(monster1.getPosition()).thenReturn(new Vector(3, 3));
        when(monster2.getPosition()).thenReturn(new Vector(4, 4));
        monsters.add(monster1);
        monsters.add(monster2);

        assertTrue(room.checkMonsterCollisions(new Vector(3, 3)));
        assertTrue(room.checkMonsterCollisions(new Vector(4, 4)));
        assertFalse(room.checkMonsterCollisions(new Vector(5, 5)));
    }

    @Test
    void testCheckPlayerCollision() {
        when(player.getPosition()).thenReturn(new Vector(3, 3));
        assertTrue(room.checkPlayerCollision(new Vector(3, 3)));
        assertFalse(room.checkPlayerCollision(new Vector(4, 4)));
    }

    @Test
    void testCheckWallCollision() {
        Wall wall = mock(Wall.class);
        when(wall.getPosition()).thenReturn(new Vector(5, 5));
        walls.add(wall);

        assertTrue(room.checkWallCollision(new Vector(5, 5)));
        assertFalse(room.checkWallCollision(new Vector(6, 6)));
    }

    @Test
    void testIsAtTransition() {
        List<Wall> doorWalls = new ArrayList<>();
        doorWalls.add(new Wall(7, 7));
        doors.put(1, doorWalls);

        assertTrue(room.isAtTransition(new Vector(7, 7)));
        assertFalse(room.isAtTransition(new Vector(8, 8)));
    }

    @Test
    void testGetDoor() {
        List<Wall> doorWalls = new ArrayList<>();
        doorWalls.add(new Wall(7, 7));
        doors.put(1, doorWalls);

        assertEquals(1, room.getDoor(new Vector(7, 7)));
        assertEquals(0, room.getDoor(new Vector(8, 8)));
    }

    @Test
    void testSetAndGetPlayer() {
        Player newPlayer = mock(Player.class);
        room.setPlayer(newPlayer);
        assertEquals(newPlayer, room.getPlayer());
    }

    @Test
    void testSetAndGetWalls() {
        List<Wall> newWalls = new ArrayList<>();
        room.setWalls(newWalls);
        assertEquals(newWalls, room.getWalls());
    }

    @Test
    void testSetAndGetMonsters() {
        List<Monster> newMonsters = new ArrayList<>();
        room.setMonsters(newMonsters);
        assertEquals(newMonsters, room.getMonsters());
    }

    @Test
    void testSetAndGetDoors() {
        Map<Integer, List<Wall>> newDoors = new HashMap<>();
        room.setDoors(newDoors);
        assertEquals(newDoors, room.getDoors());
    }
}
