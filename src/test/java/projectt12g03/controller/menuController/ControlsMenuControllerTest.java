package projectt12g03.controller.menuController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.Game;
import projectt12g03.gui.GUI;
import projectt12g03.model.menu.ControlsMenu;
import projectt12g03.model.menu.CreditsMenu;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ControlsMenuControllerTest {
    private Game game;
    private ControlsMenu controlsMenu;
    private ControlsMenuController controlsMenuController;

    @BeforeEach
    public void setup() {
        this.game = Mockito.mock(Game.class);
        this.controlsMenu = Mockito.mock(ControlsMenu.class);
        //this.controlsMenuController = new ControlsMenuController(controlsMenu);
    }

    @Test
    public void notSelectGoBack() throws IOException {
        //Mockito.when(controlsMenu.getCurrentEntry()).thenReturn("something");
        controlsMenuController.step(game, GUI.userInput.SELECT, 0);
        Mockito.verify(game, Mockito.times(0))
                .popState();
    }

    @Test
    public void selectGoBack() throws IOException {
        //Mockito.when(controlsMenu.getCurrentEntry()).thenReturn("go back");
        controlsMenuController.step(game, GUI.userInput.SELECT, 0);
        Mockito.verify(game, Mockito.times(1))
                .popState();
    }

}