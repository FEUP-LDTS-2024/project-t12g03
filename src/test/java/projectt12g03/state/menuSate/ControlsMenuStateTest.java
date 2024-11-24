package projectt12g03.state.menuSate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.GUI;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.menu.ControlsMenu;
import projectt12g03.view.ScreenDisplayer;

import java.io.IOException;

class ControlsMenuStateTest {
    private Game game;
    private LanternaGui gui;
    private ControlsMenuState state;
    private ControlsMenu controlsMenu;
    private ScreenDisplayer<ControlsMenu> controlsMenuDisplayer;
    private Controller<ControlsMenu> controlsMenuController;

    @BeforeEach
    public void setup() throws IOException {
        this.controlsMenu = Mockito.mock(ControlsMenu.class);
        this.game = Mockito.mock(Game.class);
        this.gui = Mockito.mock(LanternaGui.class);
        this.controlsMenuController = Mockito.mock(Controller.class);
        this.controlsMenuDisplayer = Mockito.mock(ScreenDisplayer.class);

        this.state = new ControlsMenuState(controlsMenu){
            @Override
            protected ScreenDisplayer<ControlsMenu> getScreenDisplayer() {
                return controlsMenuDisplayer;
            }
            @Override
            protected Controller<ControlsMenu> getController() {
                return controlsMenuController;
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