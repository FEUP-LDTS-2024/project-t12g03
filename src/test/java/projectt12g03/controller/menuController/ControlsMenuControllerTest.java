package projectt12g03.controller.menuController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.Game;
import projectt12g03.gui.GUI;
import projectt12g03.model.menu.ControlsMenu;
import projectt12g03.model.menu.MainMenu;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.eq;

public class ControlsMenuControllerTest {
    private Game game;
    private ControlsMenu controlsMenu;
    private EntriesController entriesController;
    private ControlsMenuController controlsMenuController;

    @BeforeEach
    public void setup() {
        this.game = Mockito.mock(Game.class);

        this.controlsMenu = Mockito.mock(ControlsMenu.class);
        this.entriesController = Mockito.mock(EntriesController.class);

        this.controlsMenuController = new ControlsMenuController(controlsMenu, entriesController);
    }


    @Test
    public void stepDown() throws IOException {
        for (int counter = 1; counter < 20; counter++){
            controlsMenuController.step(game, GUI.userInput.DOWN, 0);
            Mockito.verify(controlsMenu, Mockito.times(counter)).moveDown();
        }
    }

    @Test
    public void stepUp() throws IOException {
        for (int counter = 1; counter < 20; counter++){
            controlsMenuController.step(game, GUI.userInput.UP, 0);
            Mockito.verify(controlsMenu, Mockito.times(counter)).moveUp();
        }
    }

    @Test
    public void stepSelect() throws IOException {
        controlsMenuController.step(game, GUI.userInput.SELECT, 0);
        Mockito.verify(entriesController, Mockito.times(1))
                .step(eq(game), eq(GUI.userInput.SELECT), Mockito.anyLong());
    }
}