package projectt12g03.state.menuState;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.controller.Controller;
import projectt12g03.controller.menuController.PauseMenuController;
import projectt12g03.model.menu.PauseMenu;
import projectt12g03.state.State;
import projectt12g03.view.ScreenDisplayer;
import projectt12g03.view.menuDisplayer.PauseMenuDisplayer;

import java.io.IOException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PauseMenuStateTest {

    private PauseMenu mockPauseMenu;
    private PauseMenuState pauseMenuState;

    @BeforeEach
    void setUp() throws IOException {
        mockPauseMenu = mock(PauseMenu.class);
        pauseMenuState = Mockito.spy(new PauseMenuState(mockPauseMenu));
    }

    @Test
    void testGetControllerUsingReflection() throws Exception {
        Method getControllerMethod = State.class.getDeclaredMethod("getController");
        getControllerMethod.setAccessible(true);

        Controller<PauseMenu> controller = (Controller<PauseMenu>) getControllerMethod.invoke(pauseMenuState);

        assertNotNull(controller);
        assertTrue(controller instanceof PauseMenuController);
    }

    @Test
    void testGetScreenDisplayerUsingReflection() throws Exception {
        Method getScreenDisplayerMethod = State.class.getDeclaredMethod("getScreenDisplayer");
        getScreenDisplayerMethod.setAccessible(true);

        ScreenDisplayer<PauseMenu> screenDisplayer = (ScreenDisplayer<PauseMenu>) getScreenDisplayerMethod.invoke(pauseMenuState);

        assertNotNull(screenDisplayer);
        assertTrue(screenDisplayer instanceof PauseMenuDisplayer);
    }

    @Test
    void testAllowInputSpamUsingReflection() throws Exception {
        Method allowInputSpamMethod = State.class.getDeclaredMethod("allowInputSpam");
        allowInputSpamMethod.setAccessible(true);

        boolean allowInputSpam = (boolean) allowInputSpamMethod.invoke(pauseMenuState);

        assertFalse(allowInputSpam);
    }
}
