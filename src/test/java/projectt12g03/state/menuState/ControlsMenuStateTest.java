package projectt12g03.state.menuState;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.controller.Controller;
import projectt12g03.controller.menuController.ControlsMenuController;
import projectt12g03.model.menu.ControlsMenu;
import projectt12g03.state.State;
import projectt12g03.view.menuDisplayer.ControlsMenuDisplayer;
import projectt12g03.view.ScreenDisplayer;

import java.io.IOException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControlsMenuStateTest {

    private ControlsMenu mockControlsMenu;
    private ControlsMenuState controlsMenuState;

    @BeforeEach
    void setUp() throws IOException {
        mockControlsMenu = mock(ControlsMenu.class);
        controlsMenuState = Mockito.spy(new ControlsMenuState(mockControlsMenu));
    }

    @Test
    void testGetControllerUsingReflection() throws Exception {
        Method getControllerMethod = State.class.getDeclaredMethod("getController");
        getControllerMethod.setAccessible(true);

        Controller<ControlsMenu> controller = (Controller<ControlsMenu>) getControllerMethod.invoke(controlsMenuState);

        assertNotNull(controller);
        assertTrue(controller instanceof ControlsMenuController);
    }

    @Test
    void testGetScreenDisplayerUsingReflection() throws Exception {
        Method getScreenDisplayerMethod = State.class.getDeclaredMethod("getScreenDisplayer");
        getScreenDisplayerMethod.setAccessible(true);

        ScreenDisplayer<ControlsMenu> screenDisplayer = (ScreenDisplayer<ControlsMenu>) getScreenDisplayerMethod.invoke(controlsMenuState);

        assertNotNull(screenDisplayer);
        assertTrue(screenDisplayer instanceof ControlsMenuDisplayer);
    }

    @Test
    void testAllowInputSpamUsingReflection() throws Exception {
        Method allowInputSpamMethod = State.class.getDeclaredMethod("allowInputSpam");
        allowInputSpamMethod.setAccessible(true);

        boolean allowInputSpam = (boolean) allowInputSpamMethod.invoke(controlsMenuState);

        assertFalse(allowInputSpam);
    }
}
