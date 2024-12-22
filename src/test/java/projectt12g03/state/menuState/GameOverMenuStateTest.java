package projectt12g03.state.menuState;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.controller.Controller;
import projectt12g03.controller.menuController.GameOverMenuController;
import projectt12g03.model.menu.GameOverMenu;
import projectt12g03.state.State;
import projectt12g03.view.ScreenDisplayer;
import projectt12g03.view.menuDisplayer.GameOverMenuDisplayer;
import java.io.IOException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameOverMenuStateTest {

    private GameOverMenu mockGameOverMenu;
    private GameOverMenuState gameOverMenuState;

    @BeforeEach
    void setUp() throws IOException {
        mockGameOverMenu = mock(GameOverMenu.class);
        gameOverMenuState = Mockito.spy(new GameOverMenuState(mockGameOverMenu));
    }

    @Test
    void testGetControllerUsingReflection() throws Exception {
        Method getControllerMethod = State.class.getDeclaredMethod("getController");
        getControllerMethod.setAccessible(true);

        Controller<GameOverMenu> controller = (Controller<GameOverMenu>) getControllerMethod.invoke(gameOverMenuState);

        assertNotNull(controller);
        assertTrue(controller instanceof GameOverMenuController);
    }

    @Test
    void testGetScreenDisplayerUsingReflection() throws Exception {
        Method getScreenDisplayerMethod = State.class.getDeclaredMethod("getScreenDisplayer");
        getScreenDisplayerMethod.setAccessible(true);

        ScreenDisplayer<GameOverMenu> screenDisplayer = (ScreenDisplayer<GameOverMenu>) getScreenDisplayerMethod.invoke(gameOverMenuState);

        assertNotNull(screenDisplayer);
        assertTrue(screenDisplayer instanceof GameOverMenuDisplayer);
    }

    @Test
    void testAllowInputSpamUsingReflection() throws Exception {
        Method allowInputSpamMethod = State.class.getDeclaredMethod("allowInputSpam");
        allowInputSpamMethod.setAccessible(true);

        boolean allowInputSpam = (boolean) allowInputSpamMethod.invoke(gameOverMenuState);

        assertFalse(allowInputSpam);
    }
}
