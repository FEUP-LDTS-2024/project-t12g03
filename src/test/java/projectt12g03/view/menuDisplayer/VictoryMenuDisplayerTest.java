package projectt12g03.view.menuDisplayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.menu.VictoryMenu;
import projectt12g03.view.Sprite;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.spy;

class VictoryMenuDisplayerTest {
    private VictoryMenu victoryMenu;
    private LanternaGui gui;

    @BeforeEach
    public void setup() {
        this.victoryMenu = mock(VictoryMenu.class);
        this.gui = mock(LanternaGui.class);
    }

    @Test
    public void drawTest() throws IOException {
        when(victoryMenu.getCurrentEntryNumber()).thenReturn(0);
        Sprite image = mock(Sprite.class);
        VictoryMenuDisplayer victoryMenuDisplayer = spy(new VictoryMenuDisplayer(victoryMenu));
        doReturn(image).when(victoryMenuDisplayer).getImage(0);

        victoryMenuDisplayer.draw(gui, 0);

        verify(gui, times(1)).clear();
        verify(victoryMenu, times(1)).getCurrentEntryNumber();
        verify(image, times(1)).draw(gui, 0, 0);
        verify(gui, times(1)).refresh();
    }

    @Test
    public void testMapSize() throws IOException {
        VictoryMenuDisplayer victoryMenuDisplayer = spy(new VictoryMenuDisplayer(victoryMenu));

        Map<Integer, Sprite> map = victoryMenuDisplayer.createBackGroundMap();

        assertNotEquals(map.size(), 0);
    }

    @Test
    public void getImageTest() throws IOException {
        VictoryMenuDisplayer victoryMenuDisplayer = spy(new VictoryMenuDisplayer(victoryMenu));

        Sprite image = victoryMenuDisplayer.getImage(0);

        assertNotEquals(image, null);
    }
}