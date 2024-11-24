package projectt12g03.view.menuDisplayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.menu.ControlsMenu;
import projectt12g03.model.menu.CreditsMenu;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ControlsMenuDisplayerTest {
    private LanternaGui gui;
    private ControlsMenu controlsMenu;
    private ControlsMenuDisplayer controlsMenuDisplayer;

    @BeforeEach
    public void setup() {
        this.controlsMenu = new ControlsMenu();
        this.controlsMenuDisplayer = new ControlsMenuDisplayer(controlsMenu);
        this.gui = Mockito.mock(LanternaGui.class);
    }

    @Test
    public void drawTest() throws IOException {
        controlsMenuDisplayer.draw(gui);

        verify(gui, times(1)).clear();

        //1 in draw + 1 in drawEntries + 4 in drawControls, if passes mean it only went through drawEntries and drawControls once as expected
        verify(gui, times(6)).drawText(Mockito.any(), anyString(), Mockito.any());

        verify(gui, times(1)).refresh();
    }

}