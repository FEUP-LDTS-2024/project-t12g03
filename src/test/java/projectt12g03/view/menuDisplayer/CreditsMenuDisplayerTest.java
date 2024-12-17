package projectt12g03.view.menuDisplayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.menu.CreditsMenu;
import projectt12g03.model.menu.MainMenu;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CreditsMenuDisplayerTest {
    private LanternaGui gui;
    private CreditsMenu creditsMenu;
    private CreditsMenuDisplayer creditsMenuDisplayer;

    @BeforeEach
    public void setup() {
        this.creditsMenu = new CreditsMenu();
        //this.creditsMenuDisplayer = new CreditsMenuDisplayer(creditsMenu);
        this.gui = Mockito.mock(LanternaGui.class);
    }

    @Test
    public void drawTest() throws IOException {
        long time = 0;
        creditsMenuDisplayer.draw(gui, time);

        verify(gui, times(1)).clear();

        //1 in draw + 6 in drawNames + 1 in drawEntries, if passes mean it only went through drawEntries and drawNames once as expected
        verify(gui, times(8)).drawText(Mockito.any(), anyString(), Mockito.any());

        verify(gui, times(1)).refresh();
    }

}