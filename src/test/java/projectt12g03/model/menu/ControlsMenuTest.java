package projectt12g03.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class ControlsMenuTest {

    private ControlsMenu controlsMenu;

    @BeforeEach
    void setUp() {
        controlsMenu = new ControlsMenu();
    }

    @Test
    void testCreateEntriesUsingReflection() throws Exception {
        var method = ControlsMenu.class.getDeclaredMethod("createEntries");
        method.setAccessible(true);

        @SuppressWarnings("unchecked")
        Map<Integer, Menu.Type> entries = (Map<Integer, Menu.Type>) method.invoke(controlsMenu);

        assertNotNull(entries);
        assertEquals(1, entries.size());

        assertEquals(Menu.Type.GO_BACK, entries.get(0));
    }
}
