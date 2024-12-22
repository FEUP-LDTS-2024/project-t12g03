package projectt12g03.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.Game;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class GameOverMenuTest {

    private GameOverMenu gameOverMenu;

    @BeforeEach
    void setUp() {
        gameOverMenu = new GameOverMenu();
    }

    @Test
    void testCreateEntriesUsingReflection() throws Exception {
        var method = GameOverMenu.class.getDeclaredMethod("createEntries");
        method.setAccessible(true);

        @SuppressWarnings("unchecked")
        Map<Integer, Menu.Type> entries = (Map<Integer, Menu.Type>) method.invoke(gameOverMenu);

        assertNotNull(entries);
        assertEquals(2, entries.size());

        assertEquals(Menu.Type.RESTART, entries.get(0));
        assertEquals(Menu.Type.EXIT, entries.get(1));
    }
}
