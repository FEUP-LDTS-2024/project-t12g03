package projectt12g03.view.menuDisplayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.menu.PauseMenu;
import projectt12g03.view.Sprite;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class PauseMenuDisplayerTest {
    private PauseMenu pauseMenu;
    private LanternaGui gui;

    @BeforeEach
    public void setup() {
        this.pauseMenu = mock(PauseMenu.class);
        this.gui = mock(LanternaGui.class);
    }

    @Test
    public void drawTest() throws IOException {
        when(pauseMenu.getCurrentEntryNumber()).thenReturn(0);
        Sprite image = mock(Sprite.class);
        PauseMenuDisplayer pauseMenuDisplayer = spy(new PauseMenuDisplayer(pauseMenu));
        doReturn(image).when(pauseMenuDisplayer).getImage(0);

        pauseMenuDisplayer.draw(gui, 0);

        verify(gui, times(1)).clear();
        verify(pauseMenu, times(1)).getCurrentEntryNumber();
        verify(image, times(1)).draw(gui, 0, 0);
        verify(gui, times(1)).refresh();
    }

    @Test
    public void testMapSize() throws IOException {
        PauseMenuDisplayer pauseMenuDisplayer = spy(new PauseMenuDisplayer(pauseMenu));

        Map<Integer, Sprite> map = pauseMenuDisplayer.createBackGroundMap();

        assertNotEquals(map.size(), 0);
    }

    @Test
    public void getImageTest() throws IOException {
        PauseMenuDisplayer pauseMenuDisplayer = spy(new PauseMenuDisplayer(pauseMenu));

        Sprite image = pauseMenuDisplayer.getImage(0);

        assertNotEquals(image, null);
    }
}