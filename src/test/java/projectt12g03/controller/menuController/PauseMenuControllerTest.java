package projectt12g03.controller.menuController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.Game;
import projectt12g03.gui.GUI;
import projectt12g03.model.menu.PauseMenu;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.eq;

public class PauseMenuControllerTest {
    private Game game;
    private PauseMenu pauseMenu;
    private EntriesController entriesController;
    private PauseMenuController pauseMenuController;

    @BeforeEach
    public void setup() {
        this.game = Mockito.mock(Game.class);

        this.pauseMenu = Mockito.mock(PauseMenu.class);
        this.entriesController = Mockito.mock(EntriesController.class);

        this.pauseMenuController = new PauseMenuController(pauseMenu, entriesController);
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
    public void stepSelect() throws IOException {
        pauseMenuController.step(game, GUI.userInput.SELECT, 0);
        Mockito.verify(entriesController, Mockito.times(1))
                .step(eq(game), eq(GUI.userInput.SELECT), Mockito.anyLong());
    }
}