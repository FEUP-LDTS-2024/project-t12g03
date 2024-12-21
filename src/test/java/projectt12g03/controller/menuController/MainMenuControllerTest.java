package projectt12g03.controller.menuController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import projectt12g03.Game;
import projectt12g03.gui.GUI;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.menu.MainMenu;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.eq;

class MainMenuControllerTest {
    private Game game;
    private MainMenu mainMenu;
    private EntriesController entriesController;
    private MainMenuController mainMenuController;

    @BeforeEach
    public void setup() {
        this.game = Mockito.mock(Game.class);

        this.mainMenu = Mockito.mock(MainMenu.class);
        this.entriesController = Mockito.mock(EntriesController.class);

        this.mainMenuController = new MainMenuController(mainMenu, entriesController);
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
    public void stepSelect() throws IOException {
        mainMenuController.step(game, GUI.userInput.SELECT, 0);
        Mockito.verify(entriesController, Mockito.times(1))
                .step(eq(game), eq(GUI.userInput.SELECT), Mockito.anyLong());
    }
}