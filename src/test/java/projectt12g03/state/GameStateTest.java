package projectt12g03.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.GUI;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.game.room.Room;
import projectt12g03.view.ScreenDisplayer;
import projectt12g03.view.game.GameDisplayer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {
    private Game game;
    private LanternaGui gui;
    private GameState state;
    private ScreenDisplayer<Room> gameDisplayer;
    private Controller<Room> gameController;
    private Room room;

    @BeforeEach
    public void setup() throws IOException {
        this.room = Mockito.mock(Room.class);
        this.game = Mockito.mock(Game.class);
        this.gui = Mockito.mock(LanternaGui.class);
        this.gameController = Mockito.mock(Controller.class);
        this.gameDisplayer = Mockito.mock(ScreenDisplayer.class);

        this.state = new GameState(room){
            @Override
            protected ScreenDisplayer<Room> getScreenDisplayer() {
                return gameDisplayer;
            }
            @Override
            protected Controller<Room> getController() {
                return gameController;
            }

        };
    }

    @Test
    public void stepTest() throws IOException {
        long time = 0;
        Mockito.when(gui.getNextUserInput()).thenReturn(GUI.userInput.NONE);

        state.step(game, gui, time);

        Mockito.verify(game, Mockito.times(1))
                .setKeySpam(true);
        Mockito.verify(gui, Mockito.times(1)).getNextUserInput();
        Mockito.verify(state.getController(), Mockito.times(1))
                .step(game, GUI.userInput.NONE, time);
        Mockito.verify(state.getScreenDisplayer(), Mockito.times(1)).draw(gui);
    }
}