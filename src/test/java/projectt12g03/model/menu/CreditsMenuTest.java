package projectt12g03.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class CreditsMenuTest {

    private CreditsMenu creditsMenu;

    @BeforeEach
    void setUp() {
        creditsMenu = new CreditsMenu();
    }

    @Test
    void testCreateEntriesUsingReflection() throws Exception {
        var method = CreditsMenu.class.getDeclaredMethod("createEntries");
        method.setAccessible(true);

        @SuppressWarnings("unchecked")
        Map<Integer, Menu.Type> entries = (Map<Integer, Menu.Type>) method.invoke(creditsMenu);

        assertNotNull(entries);
        assertEquals(1, entries.size());

        assertEquals(Menu.Type.GO_BACK, entries.get(0));
    }
}
