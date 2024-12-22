package projectt12g03.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.model.Vector;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class VictoryMenuTest {

    private VictoryMenu victoryMenu;

    @BeforeEach
    void setUp() {
        victoryMenu = new VictoryMenu();
    }

    @Test
    void testCreateEntriesUsingReflection() throws Exception {
        var method = VictoryMenu.class.getDeclaredMethod("createEntries");
        method.setAccessible(true);

        @SuppressWarnings("unchecked")
        Map<Integer, Menu.Type> entries = (Map<Integer, Menu.Type>) method.invoke(victoryMenu);

        assertNotNull(entries);
        assertEquals(2, entries.size());

        assertEquals(Menu.Type.RESTART, entries.get(0));
        assertEquals(Menu.Type.EXIT, entries.get(1));
    }
}
