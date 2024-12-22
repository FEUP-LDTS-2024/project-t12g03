package projectt12g03.model.game.elements.movable.hostile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.model.Vector;
import projectt12g03.model.game.room.Room;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MonsterModelTest {

    private Monster monster;
    private Room mockRoom;

    @BeforeEach
    void setUp() {
        mockRoom = Mockito.mock(Room.class);
        monster = new Monster(5, 5, mockRoom);
    }

    @Test
    void testConstructorInitialValues() {
        assertEquals(5, monster.getPosition().getX());
        assertEquals(5, monster.getPosition().getY());
        assertEquals(1, monster.getVelocity());
        assertEquals(1, monster.getHealth());
        assertEquals("DOWN", monster.getDirection());
        assertEquals("IDLE", monster.getState());
        assertTrue(monster.getDirections().isEmpty());
    }

    @Test
    void testSetAndGetDirections() {
        List<String> directions = Arrays.asList("UP", "DOWN", "LEFT", "RIGHT");
        monster.setDirections(directions);
        assertEquals(directions, monster.getDirections());
    }

    @Test
    void testCanMoveWhenNoCollision() {
        Vector newPosition = new Vector(6, 5);
        Mockito.when(mockRoom.checkCollisionsForMonster(Mockito.eq(monster), Mockito.eq(newPosition))).thenReturn(false);

        assertTrue(monster.canMove(newPosition));
    }

    @Test
    void testCanMoveWhenCollision() {
        Vector newPosition = new Vector(6, 5);
        Mockito.when(mockRoom.checkCollisionsForMonster(Mockito.eq(monster), Mockito.eq(newPosition))).thenReturn(true);

        assertFalse(monster.canMove(newPosition));
    }

    @Test
    void testEquals() {
        Monster samePositionMonster = new Monster(5, 5, mockRoom);
        Monster differentPositionMonster = new Monster(10, 10, mockRoom);

        assertEquals(monster, samePositionMonster);
        assertNotEquals(monster, differentPositionMonster);
        assertNotEquals(monster, new Object());
    }
}