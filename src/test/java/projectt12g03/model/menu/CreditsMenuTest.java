package projectt12g03.model.menu;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.model.Vector;

import static org.junit.jupiter.api.Assertions.*;
class CreditsMenuTest {
    @Test
    public void testMenuCreation() {
        CreditsMenu creditsMenu = new CreditsMenu();

        assertNotNull(creditsMenu);
        assertEquals(1, creditsMenu.getNumberEntries());
        assertEquals("go back", creditsMenu.getCurrentEntry());
    }

    @Test
    public void getEntryPositionTest() {
        CreditsMenu creditsMenu = new CreditsMenu();

        Vector position1 = creditsMenu.getEntryPosition("go back");

        assertEquals(new Vector(16, 17), position1);

    }
  
}