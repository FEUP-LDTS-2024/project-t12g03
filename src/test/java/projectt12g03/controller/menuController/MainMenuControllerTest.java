package projectt12g03.controller.menuController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import projectt12g03.Game;
import projectt12g03.gui.GUI;
import projectt12g03.model.menu.MainMenu;

import java.io.IOException;

class MainMenuControllerTest {
    private Game game;
    private MainMenu mainMenu;
    private MainMenuController mainMenuController;

    @BeforeEach
    public void setup() {
        this.game = Mockito.mock(Game.class);
        this.mainMenu = Mockito.mock(MainMenu.class);
        this.mainMenuController = new MainMenuController(mainMenu);
    }


    @Test
    public void stepDown() throws IOException {
        for (int counter = 1; counter < 20; counter++){
            mainMenuController.step(game, GUI.userInput.DOWN, 0);
            Mockito.verify(mainMenu, Mockito.times(counter)).moveDown();
        }
    }

    @Test
    public void stepUp() throws IOException {
        for (int counter = 1; counter < 20; counter++){
            mainMenuController.step(game, GUI.userInput.UP, 0);
            Mockito.verify(mainMenu, Mockito.times(counter)).moveUp();
        }
    }

    @Test
    public void selectStart() throws IOException {
        Mockito.when(mainMenu.getCurrentEntry()).thenReturn("start");
        mainMenuController.step(game, GUI.userInput.SELECT, 0);
        Mockito.verify(game, Mockito.times(1))
                .setState(Mockito.any());
    }

    @Test
    public void selectControls() throws IOException {
        Mockito.when(mainMenu.getCurrentEntry()).thenReturn("controls");
        mainMenuController.step(game, GUI.userInput.SELECT, 0);
        Mockito.verify(game, Mockito.times(1))
                .setState(Mockito.any());
    }

    @Test
    public void selectCredits() throws IOException {
        Mockito.when(mainMenu.getCurrentEntry()).thenReturn("credits");;
        mainMenuController.step(game, GUI.userInput.SELECT, 0);
        Mockito.verify(game, Mockito.times(1))
                .setState(Mockito.any());
    }

    @Test
    public void selectExit() throws IOException {
        Mockito.when(mainMenu.getCurrentEntry()).thenReturn("exit");;
        mainMenuController.step(game, GUI.userInput.SELECT, 0);
        Mockito.verify(game, Mockito.times(1)).popState();
    }
}