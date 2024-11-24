package projectt12g03.controller.menuController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import projectt12g03.Game;
import projectt12g03.gui.GUI;
import projectt12g03.model.menu.CreditsMenu;
import projectt12g03.model.menu.MainMenu;

import java.io.IOException;

class CreditsMenuControllerTest {
    private Game game;
    private CreditsMenu creditsMenu;
    private CreditsMenuController creditsMenuController;

    @BeforeEach
    public void setup() {
        this.game = Mockito.mock(Game.class);
        this.creditsMenu = Mockito.mock(CreditsMenu.class);
        this.creditsMenuController = new CreditsMenuController(creditsMenu);
    }

    @Test
    public void notSelectGoBack() throws IOException {
        Mockito.when(creditsMenu.getCurrentEntry()).thenReturn("something");
        creditsMenuController.step(game, GUI.userInput.SELECT, 0);
        Mockito.verify(game, Mockito.times(0))
                .popState();
    }

    @Test
    public void selectGoBack() throws IOException {
        Mockito.when(creditsMenu.getCurrentEntry()).thenReturn("go back");
        creditsMenuController.step(game, GUI.userInput.SELECT, 0);
        Mockito.verify(game, Mockito.times(1))
                .popState();
    }
}