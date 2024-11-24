package projectt12g03.model.menu;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.model.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PauseMenuTest {
    @Test
    public void testMenuCreation() {
        PauseMenu pauseMenu = new PauseMenu(Mockito.any());

        assertNotNull(pauseMenu);
        assertEquals(2, pauseMenu.getNumberEntries());
        assertEquals("resume", pauseMenu.getCurrentEntry());
    }

    @Test
    public void moveUpTest() {
        PauseMenu pauseMenu = new PauseMenu(Mockito.any());

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
        PauseMenu pauseMenu = new PauseMenu(Mockito.any());

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
        PauseMenu pauseMenu = new PauseMenu(Mockito.any());

        Vector position1 = pauseMenu.getEntryPosition("resume");
        Vector position2 = pauseMenu.getEntryPosition("exit");


        assertEquals(new Vector(17, 9), position1);
        assertEquals(new Vector(18, 12), position2);

    }
}