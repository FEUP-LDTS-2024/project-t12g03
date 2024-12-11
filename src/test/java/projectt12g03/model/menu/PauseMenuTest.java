package projectt12g03.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.model.Vector;
import projectt12g03.model.game.room.Room;

import static org.junit.jupiter.api.Assertions.*;

class PauseMenuTest {
    private PauseMenu pauseMenu;
    private Room room;

    @BeforeEach
    public void setUp() {
        this.room = Mockito.mock(Room.class);
        this.pauseMenu = new PauseMenu(room);
    }

    @Test
    public void testMenuCreation() {
        assertNotNull(pauseMenu);
        assertEquals(2, pauseMenu.getNumberEntries());
        assertEquals("resume", pauseMenu.getCurrentEntry());
    }

    @Test
    public void moveUpTest() {
        pauseMenu.moveUp();
        assertEquals("exit", pauseMenu.getCurrentEntry());
        pauseMenu.moveUp();
        assertEquals("resume", pauseMenu.getCurrentEntry());
        pauseMenu.moveUp();
        assertNotEquals("resume", pauseMenu.getCurrentEntry());
        pauseMenu.moveUp();
        assertNotEquals("exit", pauseMenu.getCurrentEntry());
    }

    @Test
    public void moveDownTest() {
        pauseMenu.moveDown();
        assertEquals("exit", pauseMenu.getCurrentEntry());
        pauseMenu.moveDown();
        assertEquals("resume", pauseMenu.getCurrentEntry());
        pauseMenu.moveDown();
        assertNotEquals("resume", pauseMenu.getCurrentEntry());
        pauseMenu.moveDown();
        assertNotEquals("exit", pauseMenu.getCurrentEntry());
    }

    @Test
    public void getEntryPositionTest() {
        Vector position1 = pauseMenu.getEntryPosition("resume");
        Vector position2 = pauseMenu.getEntryPosition("exit");


        assertEquals(new Vector(17, 9), position1);
        assertEquals(new Vector(18, 12), position2);

    }
}