package projectt12g03.view.menuDisplayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.menu.ControlsMenu;
import projectt12g03.view.Sprite;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.spy;

class ControlsMenuDisplayerTest {
    private ControlsMenu controlsMenu;
    private LanternaGui gui;

    @BeforeEach
    public void setup() {
        this.controlsMenu = mock(ControlsMenu.class);
        this.gui = mock(LanternaGui.class);
    }

    @Test
    public void drawTest() throws IOException {
        when(controlsMenu.getCurrentEntryNumber()).thenReturn(0);
        Sprite image = mock(Sprite.class);
        ControlsMenuDisplayer controlsMenuDisplayer = spy(new ControlsMenuDisplayer(controlsMenu));
        doReturn(image).when(controlsMenuDisplayer).getImage(0);

        controlsMenuDisplayer.draw(gui, 0);

        verify(gui, times(1)).clear();
        verify(controlsMenu, times(1)).getCurrentEntryNumber();
        verify(image, times(1)).draw(gui, 0, 0);
        verify(gui, times(1)).refresh();
    }

    @Test
    public void testMapSize() throws IOException {
        ControlsMenuDisplayer controlsMenuDisplayer = spy(new ControlsMenuDisplayer(controlsMenu));

        Map<Integer, Sprite> map = controlsMenuDisplayer.createBackGroundMap();

        assertNotEquals(map.size(), 0);
    }

    @Test
    public void getImageTest() throws IOException {
        ControlsMenuDisplayer controlsMenuDisplayer = spy(new ControlsMenuDisplayer(controlsMenu));

        Sprite image = controlsMenuDisplayer.getImage(0);

        assertNotEquals(image, null);
    }
}