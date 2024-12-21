package projectt12g03.controller.menuController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.Game;
import projectt12g03.gui.GUI;
import projectt12g03.model.menu.GameOverMenu;
import projectt12g03.model.menu.PauseMenu;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.eq;

class GameOverMenuControllerTest {
    private Game game;
    private GameOverMenu gameOverMenu;
    private EntriesController entriesController;
    private GameOverMenuController gameOverMenuController;

    @BeforeEach
    public void setup() {
        this.game = Mockito.mock(Game.class);

        this.gameOverMenu = Mockito.mock(GameOverMenu.class);
        this.entriesController = Mockito.mock(EntriesController.class);

        this.gameOverMenuController = new GameOverMenuController(gameOverMenu, entriesController);
    }


    @Test
    public void stepDown() throws IOException {
        for (int counter = 1; counter < 20; counter++){
            gameOverMenuController.step(game, GUI.userInput.DOWN, 0);
            Mockito.verify(gameOverMenu, Mockito.times(counter)).moveDown();
        }
    }

    @Test
    public void stepUp() throws IOException {
        for (int counter = 1; counter < 20; counter++){
            gameOverMenuController.step(game, GUI.userInput.UP, 0);
            Mockito.verify(gameOverMenu, Mockito.times(counter)).moveUp();
        }
    }

    @Test
    public void stepSelect() throws IOException {
        gameOverMenuController.step(game, GUI.userInput.SELECT, 0);
        Mockito.verify(entriesController, Mockito.times(1))
                .step(eq(game), eq(GUI.userInput.SELECT), Mockito.anyLong());
    }
}