package projectt12g03.model.game.elements.stationary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class DoorModelTest {

    private Door door;

    @BeforeEach
    void setUp() {
        door = new Door(5, 10, 'R', 2);
        door.setHitbox(new Rectangle(2, 2, 16, 32));
    }

    @Test
    void testConstructorInitialization() {
        assertEquals(5, door.getPosition().getX());
        assertEquals(10, door.getPosition().getY());
        assertEquals(2, door.getHitbox().getX());
        assertEquals(2, door.getHitbox().getY());
        assertEquals(16, door.getHitbox().getWidth());
        assertEquals(32, door.getHitbox().getHeight());
        assertEquals(3, door.getNumber());
    }

    @Test
    void testGetNumber() {
        Door specialDoor = new Door(5,10,'0',6);
        assertEquals(3, door.getNumber());
        assertEquals(0,specialDoor.getNumber());
    }

    @Test
    void testGetDoorHitbox() {
        Door door1 = new Door(5, 10, 'U', 2);
        Door door2 = new Door(5, 10, 'D', 2);
        Door door3 = new Door(5, 10, 'R', 2);
        Door door4 = new Door(5, 10, 'l', 2);
        Door specialDoor = new Door(5,10,'0',6);

        assertEquals(new Rectangle(0,0,16,1), door1.getHitbox());
        assertEquals(new Rectangle(0,15,16,1), door2.getHitbox());
        assertEquals(new Rectangle(15,0,1,16), door3.getHitbox());
        assertEquals(new Rectangle(0,0,1,16), door4.getHitbox());
        assertEquals(new Rectangle(15,0,1,16), specialDoor.getHitbox());
    }
}
