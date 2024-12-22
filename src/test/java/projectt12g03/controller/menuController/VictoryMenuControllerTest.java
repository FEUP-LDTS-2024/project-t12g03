package projectt12g03.controller.menuController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.Game;
import projectt12g03.gui.GUI;
import projectt12g03.model.menu.GameOverMenu;
import projectt12g03.model.menu.VictoryMenu;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.eq;

class VictoryMenuControllerTest {
    private Game game;
    private VictoryMenu victoryMenu;
    private EntriesController entriesController;
    private VictoryMenuController victoryMenuController;

    @BeforeEach
    public void setup() {
        this.game = Mockito.mock(Game.class);

        this.victoryMenu = Mockito.mock(VictoryMenu.class);
        this.entriesController = Mockito.mock(EntriesController.class);

        this.victoryMenuController = new VictoryMenuController(victoryMenu, entriesController);
    }


    @Test
    public void stepDown() throws IOException {
        for (int counter = 1; counter < 20; counter++){
            victoryMenuController.step(game, GUI.userInput.DOWN, 0);
            Mockito.verify(victoryMenu, Mockito.times(counter)).moveDown();
        }
    }

    @Test
    public void stepUp() throws IOException {
        for (int counter = 1; counter < 20; counter++){
            victoryMenuController.step(game, GUI.userInput.UP, 0);
            Mockito.verify(victoryMenu, Mockito.times(counter)).moveUp();
        }
    }

    @Test
    public void stepSelect() throws IOException {
        victoryMenuController.step(game, GUI.userInput.SELECT, 0);
        Mockito.verify(entriesController, Mockito.times(1))
                .step(eq(game), eq(GUI.userInput.SELECT), Mockito.anyLong());
    }
}