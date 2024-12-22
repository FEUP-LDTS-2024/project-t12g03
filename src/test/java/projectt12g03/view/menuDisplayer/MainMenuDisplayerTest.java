package projectt12g03.view.menuDisplayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.menu.MainMenu;
import projectt12g03.view.Sprite;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class MainMenuDisplayerTest {
    private MainMenu mainMenu;
    private LanternaGui gui;

    @BeforeEach
    public void setup() {
        this.mainMenu = mock(MainMenu.class);
        this.gui = mock(LanternaGui.class);
    }

    @Test
    public void drawTest() throws IOException {
        when(mainMenu.getCurrentEntryNumber()).thenReturn(0);
        Sprite image = mock(Sprite.class);
        MainMenuDisplayer mainMenuDisplayer = spy(new MainMenuDisplayer(mainMenu));
        doReturn(image).when(mainMenuDisplayer).getImage(0);

        mainMenuDisplayer.draw(gui, 0);

        verify(gui, times(1)).clear();
        verify(mainMenu, times(1)).getCurrentEntryNumber();
        verify(image, times(1)).draw(gui, 0, 0);
        verify(gui, times(1)).refresh();
    }

    @Test
    public void testMapSize() throws IOException {
        MainMenuDisplayer mainMenuDisplayer = spy(new MainMenuDisplayer(mainMenu));

        Map<Integer, Sprite> map = mainMenuDisplayer.createBackGroundMap();

        assertNotEquals(map.size(), 0);
    }

    @Test
    public void getImageTest() throws IOException {
        MainMenuDisplayer mainMenuDisplayer = spy(new MainMenuDisplayer(mainMenu));

        Sprite image = mainMenuDisplayer.getImage(0);

        assertNotEquals(image, null);
    }
}