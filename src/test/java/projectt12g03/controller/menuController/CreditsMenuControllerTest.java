package projectt12g03.controller.menuController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.Game;
import projectt12g03.gui.GUI;
import projectt12g03.model.menu.CreditsMenu;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.eq;

public class CreditsMenuControllerTest {
    private Game game;
    private CreditsMenu creditsMenu;
    private EntriesController entriesController;
    private CreditsMenuController creditsMenuController;

    @BeforeEach
    public void setup() {
        this.game = Mockito.mock(Game.class);

        this.creditsMenu = Mockito.mock(CreditsMenu.class);
        this.entriesController = Mockito.mock(EntriesController.class);

        this.creditsMenuController = new CreditsMenuController(creditsMenu, entriesController);
    }


    @Test
    public void stepDown() throws IOException {
        for (int counter = 1; counter < 20; counter++){
            creditsMenuController.step(game, GUI.userInput.DOWN, 0);
            Mockito.verify(creditsMenu, Mockito.times(counter)).moveDown();
        }
    }

    @Test
    public void stepUp() throws IOException {
        for (int counter = 1; counter < 20; counter++){
            creditsMenuController.step(game, GUI.userInput.UP, 0);
            Mockito.verify(creditsMenu, Mockito.times(counter)).moveUp();
        }
    }

    @Test
    public void stepSelect() throws IOException {
        creditsMenuController.step(game, GUI.userInput.SELECT, 0);
        Mockito.verify(entriesController, Mockito.times(1))
                .step(eq(game), eq(GUI.userInput.SELECT), Mockito.anyLong());
    }
}
