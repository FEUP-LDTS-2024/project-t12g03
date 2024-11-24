package projectt12g03.controller.menuController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.Game;
import projectt12g03.gui.GUI;
import projectt12g03.model.menu.MainMenu;
import projectt12g03.model.menu.PauseMenu;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PauseMenuControllerTest {
    private Game game;
    private PauseMenu pauseMenu;
    private PauseMenuController pauseMenuController;

    @BeforeEach
    public void setup() {
        this.game = Mockito.mock(Game.class);
        this.pauseMenu = Mockito.mock(PauseMenu.class);
        this.pauseMenuController = new PauseMenuController(pauseMenu);
    }


    @Test
    public void stepDown() throws IOException {
        for (int counter = 1; counter < 20; counter++){
            pauseMenuController.step(game, GUI.userInput.DOWN, 0);
            Mockito.verify(pauseMenu, Mockito.times(counter)).moveDown();
        }
    }

    @Test
    public void stepUp() throws IOException {
        for (int counter = 1; counter < 20; counter++){
            pauseMenuController.step(game, GUI.userInput.UP, 0);
            Mockito.verify(pauseMenu, Mockito.times(counter)).moveUp();
        }
    }

    @Test
    public void selectResume() throws IOException {
        Mockito.when(pauseMenu.getCurrentEntry()).thenReturn("resume");
        pauseMenuController.step(game, GUI.userInput.SELECT, 0);
        Mockito.verify(game, Mockito.times(1))
                .popState();
    }

    @Test
    public void selectExit() throws IOException {
        Mockito.when(pauseMenu.getCurrentEntry()).thenReturn("exit");
        pauseMenuController.step(game, GUI.userInput.SELECT, 0);
        Mockito.verify(game, Mockito.times(2))
                .popState();
    }
}