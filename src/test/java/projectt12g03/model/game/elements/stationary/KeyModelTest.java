package projectt12g03.model.game.elements.stationary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KeyModelTest {
    private Key key;

    @Test
    void testConstructorInitialization() {
        key = new Key(0,0);
        assertEquals(0, key.getPosition().getX());
        assertEquals(0, key.getPosition().getY());
        assertEquals(0, key.getHitbox().getX());
        assertEquals(4, key.getHitbox().getY());
        assertEquals(16, key.getHitbox().getWidth());
        assertEquals(9, key.getHitbox().getHeight());
    }

}
