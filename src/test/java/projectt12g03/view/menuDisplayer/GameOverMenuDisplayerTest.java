package projectt12g03.view.menuDisplayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.menu.GameOverMenu;
import projectt12g03.view.Sprite;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.spy;

class GameOverMenuDisplayerTest {
    private GameOverMenu gameOverMenu;
    private LanternaGui gui;

    @BeforeEach
    public void setup() {
        this.gameOverMenu = mock(GameOverMenu.class);
        this.gui = mock(LanternaGui.class);
    }

    @Test
    public void drawTest() throws IOException {
        when(gameOverMenu.getCurrentEntryNumber()).thenReturn(0);
        when(gameOverMenu.getType()).thenReturn("LIVED_KILLED");

        Sprite image = mock(Sprite.class);
        Sprite layer = mock(Sprite.class);

        GameOverMenuDisplayer gameOverMenuDisplayer = spy(new GameOverMenuDisplayer(gameOverMenu));

        doReturn(image).when(gameOverMenuDisplayer).getImage(0);
        doReturn(layer).when(gameOverMenuDisplayer).getLayer("LIVED_KILLED");

        gameOverMenuDisplayer.draw(gui, 0);

        verify(gui, times(1)).clear();
        verify(gameOverMenu, times(1)).getCurrentEntryNumber();
        verify(image, times(1)).draw(gui, 0, 0);
        verify(layer, times(1)).draw(gui, 0, 0);
        verify(gui, times(1)).refresh();
    }


    @Test
    public void testMapSize() throws IOException {
        GameOverMenuDisplayer gameOverMenuDisplayer = spy(new GameOverMenuDisplayer(gameOverMenu));

        Map<Integer, Sprite> map = gameOverMenuDisplayer.createBackGroundMap();

        assertNotEquals(map.size(), 0);
    }

    @Test
    public void getImageTest() throws IOException {
        GameOverMenuDisplayer gameOverMenuDisplayer = spy(new GameOverMenuDisplayer(gameOverMenu));

        Sprite image = gameOverMenuDisplayer.getImage(0);

        assertNotEquals(image, null);
    }

    @Test
    public void getLayerTest() throws IOException {
        GameOverMenuDisplayer gameOverMenuDisplayer = spy(new GameOverMenuDisplayer(gameOverMenu));

        Sprite image = gameOverMenuDisplayer.getLayer("LIVED_KILLED");

        assertNotEquals(image, null);
    }
}