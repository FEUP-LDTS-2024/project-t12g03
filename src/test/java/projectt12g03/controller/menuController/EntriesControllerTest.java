package projectt12g03.controller.menuController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import projectt12g03.Game;
import projectt12g03.gui.GUI;
import projectt12g03.model.menu.Menu;
import projectt12g03.model.menu.PauseMenu;
import projectt12g03.state.GameState;
import projectt12g03.state.menuState.ControlsMenuState;
import projectt12g03.state.menuState.CreditsMenuState;

import java.io.IOException;

import static org.mockito.Mockito.*;

class EntriesControllerTest {
    private EntriesController entriesController;
    private Menu mockMenu;
    private PauseMenu mockPauseMenu;
    private Game mockGame;

    @BeforeEach
    void setUp() {
        mockMenu = mock(Menu.class);
        mockPauseMenu = mock(PauseMenu.class);
        mockGame = mock(Game.class);
        entriesController = new EntriesController(mockMenu);
    }

    @Test
    void testStartEntry() throws IOException {
        when(mockMenu.getCurrentEntry()).thenReturn(Menu.Type.START);

        entriesController.step(mockGame, GUI.userInput.NONE, 0);

        ArgumentCaptor<GameState> captor = ArgumentCaptor.forClass(GameState.class);
        verify(mockGame).setState(captor.capture());
    }

    @Test
    void testRestartEntry() throws IOException {
        when(mockMenu.getCurrentEntry()).thenReturn(Menu.Type.RESTART);

        entriesController.step(mockGame, GUI.userInput.NONE, 0);

        verify(mockGame, times(1)).popState();
        ArgumentCaptor<GameState> captor = ArgumentCaptor.forClass(GameState.class);
        verify(mockGame).setState(captor.capture());
    }

    @Test
    void testControlsEntry() throws IOException {
        when(mockMenu.getCurrentEntry()).thenReturn(Menu.Type.CONTROLS);

        entriesController.step(mockGame, GUI.userInput.NONE, 0);

        ArgumentCaptor<ControlsMenuState> captor = ArgumentCaptor.forClass(ControlsMenuState.class);
        verify(mockGame).setState(captor.capture());
    }

    @Test
    void testCreditsEntry() throws IOException {
        when(mockMenu.getCurrentEntry()).thenReturn(Menu.Type.CREDITS);

        entriesController.step(mockGame, GUI.userInput.NONE, 0);

        ArgumentCaptor<CreditsMenuState> captor = ArgumentCaptor.forClass(CreditsMenuState.class);
        verify(mockGame).setState(captor.capture());
    }

    @Test
    void testExitEntryFromPauseMenu() throws IOException {
        entriesController = new EntriesController(mockPauseMenu);

        when(mockPauseMenu.getCurrentEntry()).thenReturn(Menu.Type.EXIT);

        entriesController.step(mockGame, GUI.userInput.NONE, 0);

        verify(mockGame, times(2)).popState();
    }

    @Test
    void testExitEntryFromOtherMenu() throws IOException {
        when(mockMenu.getCurrentEntry()).thenReturn(Menu.Type.EXIT);

        entriesController.step(mockGame, GUI.userInput.NONE, 0);

        verify(mockGame, times(1)).popState();
    }

    @Test
    void testGoBackOrResumeEntry() throws IOException {
        when(mockMenu.getCurrentEntry()).thenReturn(Menu.Type.GO_BACK);

        entriesController.step(mockGame, GUI.userInput.NONE, 0);

        verify(mockGame, times(1)).popState();

        when(mockMenu.getCurrentEntry()).thenReturn(Menu.Type.RESUME);

        entriesController.step(mockGame, GUI.userInput.NONE, 0);

        verify(mockGame, times(2)).popState();
    }
}
