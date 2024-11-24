package projectt12g03.state.menuSate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.GUI;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.game.room.Room;
import projectt12g03.model.menu.MainMenu;
import projectt12g03.model.menu.PauseMenu;
import projectt12g03.view.ScreenDisplayer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PauseMenuStateTest {
    private Game game;
    private LanternaGui gui;
    private PauseMenuState state;
    private PauseMenu pauseMenu;
    private ScreenDisplayer<PauseMenu> pauseMenuDisplayer;
    private Controller<PauseMenu> pauseMenuController;

    @BeforeEach
    public void setup() throws IOException {
        this.pauseMenu = Mockito.mock(PauseMenu.class);
        this.game = Mockito.mock(Game.class);
        this.gui = Mockito.mock(LanternaGui.class);
        this.pauseMenuController = Mockito.mock(Controller.class);
        this.pauseMenuDisplayer = Mockito.mock(ScreenDisplayer.class);

        this.state = new PauseMenuState(pauseMenu){
            @Override
            protected ScreenDisplayer<PauseMenu> getScreenDisplayer() {
                return pauseMenuDisplayer;
            }
            @Override
            protected Controller<PauseMenu> getController() {
                return pauseMenuController;
            }

        };
    }

    @Test
    public void stepTest() throws IOException {
        long time = 0;
        Mockito.when(gui.getNextUserInput()).thenReturn(GUI.userInput.NONE);

        state.step(game, gui, time);

        Mockito.verify(game, Mockito.times(1)).setKeySpam(false);
        Mockito.verify(gui, Mockito.times(1)).getNextUserInput();
        Mockito.verify(state.getController(), Mockito.times(1))
                .step(game, GUI.userInput.NONE, time);
        Mockito.verify(state.getScreenDisplayer(), Mockito.times(1)).draw(gui);
    }

}