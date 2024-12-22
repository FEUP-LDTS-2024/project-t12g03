package projectt12g03.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class PauseMenuTest {

    private PauseMenu pauseMenu;

    @BeforeEach
    void setUp() {
        pauseMenu = new PauseMenu();
    }

    @Test
    void testCreateEntriesUsingReflection() throws Exception {
        var method = PauseMenu.class.getDeclaredMethod("createEntries");
        method.setAccessible(true);

        @SuppressWarnings("unchecked")
        Map<Integer, Menu.Type> entries = (Map<Integer, Menu.Type>) method.invoke(pauseMenu);

        assertNotNull(entries);
        assertEquals(2, entries.size());

        assertEquals(Menu.Type.RESUME, entries.get(0));
        assertEquals(Menu.Type.EXIT, entries.get(1));
    }
}
