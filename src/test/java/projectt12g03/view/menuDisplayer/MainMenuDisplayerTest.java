package projectt12g03.view.menuDisplayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.menu.MainMenu;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class MainMenuDisplayerTest {
    private LanternaGui gui;
    private MainMenu mainMenu;
    private MainMenuDisplayer mainMenuDisplayer;

    @BeforeEach
    public void setup() throws IOException {
        this.mainMenu = new MainMenu();
        this.mainMenuDisplayer = new MainMenuDisplayer(mainMenu);
        this.gui = Mockito.mock(LanternaGui.class);
    }

    @Test
    public void drawTest() throws IOException {
        mainMenuDisplayer.draw(gui, 1);

        verify(gui, times(1)).clear();

        //1 in draw + 4 in drawEntries, if passes mean it only went through drawEntries once as expected
        verify(gui, times(5)).drawText(Mockito.any(), anyString(), Mockito.any());

        verify(gui, times(1)).refresh();
    }
}