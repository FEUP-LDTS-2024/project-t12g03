package projectt12g03.state.menuState;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.controller.Controller;
import projectt12g03.controller.gameController.GameController;
import projectt12g03.model.game.room.RoomManager;
import projectt12g03.state.GameState;
import projectt12g03.state.State;
import projectt12g03.view.ScreenDisplayer;
import projectt12g03.view.gameDisplayer.GameDisplayer;

import java.io.IOException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameStateTest {

    private RoomManager mockRoomManager;
    private GameState gameState;

    @BeforeEach
    void setUp() throws IOException {
        mockRoomManager = mock(RoomManager.class);
        gameState = Mockito.spy(new GameState(mockRoomManager));
    }

    @Test
    void testGetControllerUsingReflection() throws Exception {
        Method getControllerMethod = State.class.getDeclaredMethod("getController");
        getControllerMethod.setAccessible(true);

        Controller<RoomManager> controller = (Controller<RoomManager>) getControllerMethod.invoke(gameState);

        assertNotNull(controller);
        assertTrue(controller instanceof GameController);
    }

    @Test
    void testGetScreenDisplayerUsingReflection() throws Exception {
        Method getScreenDisplayerMethod = State.class.getDeclaredMethod("getScreenDisplayer");
        getScreenDisplayerMethod.setAccessible(true);

        ScreenDisplayer<RoomManager> screenDisplayer = (ScreenDisplayer<RoomManager>) getScreenDisplayerMethod.invoke(gameState);

        assertNotNull(screenDisplayer);
        assertTrue(screenDisplayer instanceof GameDisplayer);
    }

    @Test
    void testAllowInputSpamUsingReflection() throws Exception {
        Method allowInputSpamMethod = State.class.getDeclaredMethod("allowInputSpam");
        allowInputSpamMethod.setAccessible(true);

        boolean allowInputSpam = (boolean) allowInputSpamMethod.invoke(gameState);

        assertTrue(allowInputSpam);
    }
}
