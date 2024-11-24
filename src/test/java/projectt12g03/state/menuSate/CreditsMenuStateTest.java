package projectt12g03.state.menuSate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.GUI;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.menu.ControlsMenu;
import projectt12g03.model.menu.CreditsMenu;
import projectt12g03.view.ScreenDisplayer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CreditsMenuStateTest {
    private Game game;
    private LanternaGui gui;
    private CreditsMenuState state;
    private CreditsMenu creditsMenu;
    private ScreenDisplayer<CreditsMenu> creditsMenuDisplayer;
    private Controller<CreditsMenu> creditsMenuController;

    @BeforeEach
    public void setup() throws IOException {
        this.creditsMenu = Mockito.mock(CreditsMenu.class);
        this.game = Mockito.mock(Game.class);
        this.gui = Mockito.mock(LanternaGui.class);
        this.creditsMenuController = Mockito.mock(Controller.class);
        this.creditsMenuDisplayer = Mockito.mock(ScreenDisplayer.class);

        this.state = new CreditsMenuState(creditsMenu){
            @Override
            protected ScreenDisplayer<CreditsMenu> getScreenDisplayer() {
                return creditsMenuDisplayer;
            }
            @Override
            protected Controller<CreditsMenu> getController() {
                return creditsMenuController;
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