package projectt12g03.state.menuSate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.GUI;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.menu.MainMenu;
import projectt12g03.view.ScreenDisplayer;

import java.io.IOException;

class MainMenuStateTest {
    private Game game;
    private LanternaGui gui;
    private MainMenuState state;
    private MainMenu mainMenu;
    private ScreenDisplayer<MainMenu> mainMenuDisplayer;
    private Controller<MainMenu> mainMenuController;

    @BeforeEach
    public void setup() throws IOException {
        this.mainMenu = Mockito.mock(MainMenu.class);
        this.game = Mockito.mock(Game.class);
        this.gui = Mockito.mock(LanternaGui.class);
        this.mainMenuController = Mockito.mock(Controller.class);
        this.mainMenuDisplayer = Mockito.mock(ScreenDisplayer.class);

        this.state = new MainMenuState(mainMenu){
            @Override
            protected ScreenDisplayer<MainMenu> getScreenDisplayer() {
                return mainMenuDisplayer;
            }
            @Override
            protected Controller<MainMenu> getController() {
                return mainMenuController;
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