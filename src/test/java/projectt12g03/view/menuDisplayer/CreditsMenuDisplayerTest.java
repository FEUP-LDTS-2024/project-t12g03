package projectt12g03.view.menuDisplayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.menu.CreditsMenu;
import projectt12g03.view.Sprite;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.spy;

class CreditsMenuDisplayerTest {
    private CreditsMenu creditsMenu;
    private LanternaGui gui;

    @BeforeEach
    public void setup() {
        this.creditsMenu = mock(CreditsMenu.class);
        this.gui = mock(LanternaGui.class);
    }

    @Test
    public void drawTest() throws IOException {
        when(creditsMenu.getCurrentEntryNumber()).thenReturn(0);
        Sprite image = mock(Sprite.class);
        CreditsMenuDisplayer creditsMenuDisplayer = spy(new CreditsMenuDisplayer(creditsMenu));
        doReturn(image).when(creditsMenuDisplayer).getImage(0);

        creditsMenuDisplayer.draw(gui, 0);

        verify(gui, times(1)).clear();
        verify(creditsMenu, times(1)).getCurrentEntryNumber();
        verify(image, times(1)).draw(gui, 0, 0);
        verify(gui, times(1)).refresh();
    }

    @Test
    public void testMapSize() throws IOException {
        CreditsMenuDisplayer creditsMenuDisplayer = spy(new CreditsMenuDisplayer(creditsMenu));

        Map<Integer, Sprite> map = creditsMenuDisplayer.createBackGroundMap();

        assertNotEquals(map.size(), 0);
    }

    @Test
    public void getImageTest() throws IOException {
        CreditsMenuDisplayer creditsMenuDisplayer = spy(new CreditsMenuDisplayer(creditsMenu));

        Sprite image = creditsMenuDisplayer.getImage(0);

        assertNotEquals(image, null);
    }
}