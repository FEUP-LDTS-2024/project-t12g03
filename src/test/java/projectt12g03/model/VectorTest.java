package projectt12g03.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.model.game.elements.movable.hostile.Monster;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class VectorTest {
    Vector vector;

    @BeforeEach
    void setUp(){
        vector = new Vector(5,5);
    }

    @Test
    void testEquals() {
        Vector samePositionVector = new Vector(5, 5);
        Vector differentXPositionVector = new Vector(10, 5);
        Vector differentYPositionVector = new Vector(5, -10);

        assertEquals(vector, samePositionVector);
        assertNotEquals(vector,differentXPositionVector);
        assertNotEquals(vector,differentYPositionVector);
        assertNotEquals(vector, new Object());
    }
}
