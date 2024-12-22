package projectt12g03.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class MainMenuTest {

    private MainMenu mainMenu;

    @BeforeEach
    void setUp() {
        mainMenu = new MainMenu();
    }

    @Test
    void testCreateEntriesUsingReflection() throws Exception {
        var method = MainMenu.class.getDeclaredMethod("createEntries");
        method.setAccessible(true);

        @SuppressWarnings("unchecked")
        Map<Integer, Menu.Type> entries = (Map<Integer, Menu.Type>) method.invoke(mainMenu);

        assertNotNull(entries);
        assertEquals(4, entries.size());

        assertEquals(Menu.Type.START, entries.get(0));
        assertEquals(Menu.Type.CONTROLS, entries.get(1));
        assertEquals(Menu.Type.CREDITS, entries.get(2));
        assertEquals(Menu.Type.EXIT, entries.get(3));
    }

    @Test
    void testMove(){
        mainMenu.moveUp();
        assertEquals(Menu.Type.EXIT, mainMenu.getCurrentEntry());
        assertEquals(3, mainMenu.getCurrentEntryNumber());

        mainMenu.moveDown();
        assertEquals(Menu.Type.START, mainMenu.getCurrentEntry());
        assertEquals(0, mainMenu.getCurrentEntryNumber());

        mainMenu.moveDown();
        assertEquals(Menu.Type.CONTROLS, mainMenu.getCurrentEntry());
        assertEquals(1, mainMenu.getCurrentEntryNumber());

        mainMenu.moveUp();
        assertEquals(Menu.Type.START, mainMenu.getCurrentEntry());
        assertEquals(0, mainMenu.getCurrentEntryNumber());
    }
}
