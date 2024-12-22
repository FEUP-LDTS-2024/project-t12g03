package projectt12g03.model.game.elements.stationary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloorModelTest {

    private Floor floor;

    @BeforeEach
    void setUp() {
        floor = new Floor(10, 20, 'A');
    }

    @Test
    void testConstructorInitialization() {
        assertEquals(10, floor.getPosition().getX());
        assertEquals(20, floor.getPosition().getY());
        assertEquals(0, floor.getHitbox().getX());
        assertEquals(0, floor.getHitbox().getY());
        assertEquals(16, floor.getHitbox().getWidth());
        assertEquals(16, floor.getHitbox().getHeight());
        assertEquals('A', floor.getType());
    }

    @Test
    void testGetType() {
        assertEquals('A', floor.getType());
    }
}
