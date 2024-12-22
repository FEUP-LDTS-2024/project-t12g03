package projectt12g03.model.game.elements.stationary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WallModelTest {

    private Wall wall;

    @BeforeEach
    void setUp() {
        wall = new Wall(15, 25);
    }

    @Test
    void testConstructorInitialization() {
        assertEquals(15, wall.getPosition().getX());
        assertEquals(25, wall.getPosition().getY());
        assertEquals(0, wall.getHitbox().getX());
        assertEquals(0, wall.getHitbox().getY());
        assertEquals(16, wall.getHitbox().getWidth());
        assertEquals(16, wall.getHitbox().getHeight());
    }
}
