package projectt12g03.model.game.elements.stationary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.model.game.room.Room;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LaserModelTest {

    private Laser laserL;
    private Laser laserR;
    private Room mockRoom;

    @BeforeEach
    void setUp() {
        mockRoom = mock(Room.class);
        laserL = new Laser(10, 10, mockRoom, true);
        laserR = new Laser(10, 10, mockRoom, false);
    }

    @Test
    void testConstructorInitialization() {
        assertEquals("RESTING", laserL.getMode());
        assertEquals(45, laserL.getResting());
        assertEquals(0, laserL.getActive());
        assertTrue(laserL.isPointingLeft());
        assertEquals(new Rectangle(-4, 5, 4, 6), laserL.getLaserBeamHitbox());

        assertEquals("RESTING", laserR.getMode());
        assertEquals(45, laserR.getResting());
        assertEquals(0, laserR.getActive());
        assertFalse(laserR.isPointingLeft());
        assertEquals(new Rectangle(16, 5, 4, 6), laserR.getLaserBeamHitbox());
    }

    @Test
    void testChangeModeToActive() {
        laserL.changeMode();
        assertEquals("ACTIVE", laserL.getMode());
        assertEquals(15, laserL.getActive());

        laserR.changeMode();
        assertEquals("ACTIVE", laserR.getMode());
        assertEquals(15, laserR.getActive());
    }

    @Test
    void testChangeModeToResting() {
        laserL.setMode("ACTIVE");
        laserL.changeMode();
        assertEquals("RESTING", laserL.getMode());
        assertEquals(45, laserL.getResting());

        laserR.setMode("ACTIVE");
        laserR.changeMode();
        assertEquals("RESTING", laserR.getMode());
        assertEquals(45, laserR.getResting());
    }

    @Test
    void testChangeModeWhileModeUnknown() {
        laserL.setMode("UNKNOWN");
        laserL.changeMode();
        assertEquals("UNKNOWN", laserL.getMode());

        laserR.setMode("UNKNOWN");
        laserR.changeMode();
        assertEquals("UNKNOWN", laserR.getMode());
    }

    @Test
    void testReduceResting() {
        laserL.reduceResting();
        assertEquals(44, laserL.getResting());

        laserR.reduceResting();
        assertEquals(44, laserR.getResting());
    }

    @Test
    void testReduceActive() {
        laserL.changeMode();
        laserL.reduceActive();
        assertEquals(14, laserL.getActive());

        laserR.changeMode();
        laserR.reduceActive();
        assertEquals(14, laserR.getActive());
    }

    @Test
    void testGetRange() {
        when(mockRoom.getLaserRange(laserL)).thenReturn(20);
        assertEquals(20, laserL.getRange());

        when(mockRoom.getLaserRange(laserR)).thenReturn(10);
        assertEquals(10, laserR.getRange());
    }
}