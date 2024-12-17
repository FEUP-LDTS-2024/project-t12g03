package projectt12g03.view.menuDisplayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.game.room.Room;
import projectt12g03.model.menu.MainMenu;
import projectt12g03.model.menu.PauseMenu;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PauseMenuDisplayerTest {
    private Room room;
    private LanternaGui gui;
    private PauseMenu pauseMenu;
    private PauseMenuDisplayer pauseMenuDisplayer;

    @BeforeEach
    public void setup() {
        this.room = Mockito.mock(Room.class);
        //this.pauseMenu = new PauseMenu(room);
        //this.pauseMenuDisplayer = new PauseMenuDisplayer(pauseMenu);
        this.gui = Mockito.mock(LanternaGui.class);
    }

    @Test
    public void drawTest() throws IOException {
        long time = 0;
        pauseMenuDisplayer.draw(gui, time);

        verify(gui, times(1)).clear();

        //1 in draw + 2 in drawEntries, if passes mean it only went through drawEntries once as expected
        verify(gui, times(3)).drawText(Mockito.any(), anyString(), Mockito.any());

        verify(gui, times(1)).refresh();
    }
}