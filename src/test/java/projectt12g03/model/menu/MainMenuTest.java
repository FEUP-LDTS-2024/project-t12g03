package projectt12g03.model.menu;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.model.Vector;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuTest {
    @Test
    public void testMenuCreation() {
        MainMenu mainMenu = new MainMenu();

        assertNotNull(mainMenu);
        assertEquals(4, mainMenu.getNumberEntries());
        assertEquals("start", mainMenu.getCurrentEntry());
    }

    @Test
    public void moveUpTest() {
        MainMenu mainMenu = new MainMenu();

        mainMenu.moveUp();
        assertEquals("exit", mainMenu.getCurrentEntry());
        mainMenu.moveUp();
        assertEquals("credits", mainMenu.getCurrentEntry());
        mainMenu.moveUp();
        assertEquals("controls", mainMenu.getCurrentEntry());
        mainMenu.moveUp();
        assertEquals("start", mainMenu.getCurrentEntry());
    }

    @Test
    public void moveDownTest() {
        MainMenu mainMenu = new MainMenu();

        mainMenu.moveDown();
        assertEquals("controls", mainMenu.getCurrentEntry());
        mainMenu.moveDown();
        assertEquals("credits", mainMenu.getCurrentEntry());
        mainMenu.moveDown();
        assertEquals("exit", mainMenu.getCurrentEntry());
        mainMenu.moveDown();
        assertEquals("start", mainMenu.getCurrentEntry());
    }

    @Test
    public void getEntryPositionTest() {
        MainMenu mainMenu = new MainMenu();

        Vector position1 = mainMenu.getEntryPosition("start");
        Vector position2 = mainMenu.getEntryPosition("controls");
        Vector position3 = mainMenu.getEntryPosition("credits");
        Vector position4 = mainMenu.getEntryPosition("exit");


        assertEquals( new Vector(18, 7), position1);
        assertEquals(new Vector(18, 10), position2);
        assertEquals(new Vector(18, 13), position3);
        assertEquals(new Vector(18, 16), position4);

    }

}