package projectt12g03.model.game.elements.stationary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoorModelTest {

    private Door door;

    @BeforeEach
    void setUp() {
        door = new Door(5, 10, 2, 2, 16, 32, 3);
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
        assertEquals(3, door.getNumber());
    }
}
