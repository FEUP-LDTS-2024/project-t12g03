package projectt12g03.model.menu;

import org.junit.jupiter.api.Test;
import projectt12g03.model.Vector;

import static org.junit.jupiter.api.Assertions.*;

class ControlsMenuTest {
    @Test
    public void testMenuCreation() {
        ControlsMenu controlsMenu = new ControlsMenu();

        assertNotNull(controlsMenu);
        assertEquals(1, controlsMenu.getNumberEntries());
        assertEquals("go back", controlsMenu.getCurrentEntry());
    }

    @Test
    public void getEntryPositionTest() {
        ControlsMenu controlsMenu = new ControlsMenu();

        Vector position1 = controlsMenu.getEntryPosition("go back");

        assertEquals(new Vector(18, 16), position1);

    }

}