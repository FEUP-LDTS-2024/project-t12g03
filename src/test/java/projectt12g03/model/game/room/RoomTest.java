package projectt12g03.model.game.room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.Player;
import projectt12g03.model.game.elements.Wall;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    private Player player;
    private Room room;

    @BeforeEach
    public void setup() {
        room = new Room(5, 5, 5);
        List<Wall> walls = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            walls.add(new Wall(i, 0));
            walls.add(new Wall(i, 4));
            walls.add(new Wall(0, i));
            walls.add(new Wall(4, i));
        }
        room.setWalls(walls);
        player = new Player(0,0, null);
        room.setPlayer(player);
    }

    @Test
    public void checkWallCollisionTest() {
        Vector playerPosition1 = new Vector(1, 3);
        Vector playerPosition2 = new Vector(3, 1);
        Vector playerPosition3 = new Vector(0, 2);
        Vector playerPosition4 = new Vector(2, 0);

        assertTrue(room.checkWallCollision(playerPosition1));
        assertTrue(room.checkWallCollision(playerPosition2));
        assertFalse(room.checkWallCollision(playerPosition3));
        assertFalse(room.checkWallCollision(playerPosition4));
    }

}