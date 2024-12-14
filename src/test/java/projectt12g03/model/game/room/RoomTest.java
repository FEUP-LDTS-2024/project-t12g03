package projectt12g03.model.game.room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.stationary.Door;
import projectt12g03.model.game.elements.movable.Monster;
import projectt12g03.model.game.elements.movable.Player;
import projectt12g03.model.game.elements.stationary.Wall;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    private Room room;
    private Player player;
    private List<Wall> walls;
    private List<Monster> monsters;
    private List<Door> doors;

    @BeforeEach
    void setup() {
        room = new Room(10, 10);
        player = mock(Player.class);
        walls = new ArrayList<>();
        monsters = new ArrayList<>();
        doors = new ArrayList<>();

        room.setPlayer(player);
        room.setWalls(walls);
        room.setMonsters(monsters);
        room.setDoors(doors);
    }

    @Test
    void testCheckCollisionsNoCollisionsForPlayer() {

        walls.add(new Wall(3, 3));
        monsters.add(new Monster(1, 2, room));
        List<Door> door = new ArrayList<>();
        door.add(new Door(8, 8, 1));

        assertFalse(room.checkCollisionsForPlayer(new Vector(5, 5)));
    }

    @Test
    void testCheckCollisionsForPlayerWithWall() {
        walls.add(new Wall(3, 3));
        assertTrue(room.checkCollisionsForPlayer(new Vector(3, 3)));
    }

    @Test
    void testCheckCollisionsForPlayerWithMonster() {
        Monster monster = mock(Monster.class);
        when(monster.getPosition()).thenReturn(new Vector(4, 4));
        monsters.add(monster);

        assertTrue(room.checkCollisionsForPlayer(new Vector(4, 4)));
    }

    @Test
    void testCheckCollisionsForPlayerWithTransition() {
        List<Door> door = new ArrayList<>();
        door.add(new Door(8, 8, 1));

        assertTrue(room.checkCollisionsForPlayer(new Vector(8, 8)));
    }

    @Test
    void testCheckCollisionsForMonster() {
        Monster monster1 = mock(Monster.class);
        Monster monster2 = mock(Monster.class);
        when(monster1.getPosition()).thenReturn(new Vector(3, 3));
        when(monster2.getPosition()).thenReturn(new Vector(4, 4));
        monsters.add(monster1);
        monsters.add(monster2);

        assertTrue(room.checkCollisionsForMonster(new Vector(3, 3)));
        assertTrue(room.checkCollisionsForMonster(new Vector(4, 4)));
        assertFalse(room.checkCollisionsForMonster(new Vector(5, 5)));
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
        List<Door> door = new ArrayList<>();
        door.add(new Door(7, 7, 1));

        assertTrue(room.isAtTransition(new Vector(7, 7)));
        assertFalse(room.isAtTransition(new Vector(8, 8)));
    }

    @Test
    void testGetDoor() {
        List<Door> door = new ArrayList<>();
        door.add(new Door(7, 7, 1));

        assertEquals(1, room.getDoorNumber(new Vector(7, 7)));
        assertEquals(0, room.getDoorNumber(new Vector(8, 8)));
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
        List<Door> newDoors = new ArrayList<>();
        room.setDoors(newDoors);
        assertEquals(newDoors, room.getDoors());
    }
}
