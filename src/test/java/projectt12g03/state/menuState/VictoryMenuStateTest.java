package projectt12g03.state.menuState;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.controller.Controller;
import projectt12g03.controller.menuController.VictoryMenuController;
import projectt12g03.model.menu.VictoryMenu;
import projectt12g03.state.State;
import projectt12g03.view.ScreenDisplayer;
import projectt12g03.view.menuDisplayer.VictoryMenuDisplayer;

import java.io.IOException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VictoryMenuStateTest {

    private VictoryMenu mockVictoryMenu;
    private VictoryMenuState victoryMenuState;

    @BeforeEach
    void setUp() throws IOException {
        mockVictoryMenu = mock(VictoryMenu.class);
        victoryMenuState = Mockito.spy(new VictoryMenuState(mockVictoryMenu));
    }

    @Test
    void testGetControllerUsingReflection() throws Exception {
        Method getControllerMethod = State.class.getDeclaredMethod("getController");
        getControllerMethod.setAccessible(true);

        Controller<VictoryMenu> controller = (Controller<VictoryMenu>) getControllerMethod.invoke(victoryMenuState);

        assertNotNull(controller);
        assertTrue(controller instanceof VictoryMenuController);
    }

    @Test
    void testGetScreenDisplayerUsingReflection() throws Exception {
        Method getScreenDisplayerMethod = State.class.getDeclaredMethod("getScreenDisplayer");
        getScreenDisplayerMethod.setAccessible(true);

        ScreenDisplayer<VictoryMenu> screenDisplayer = (ScreenDisplayer<VictoryMenu>) getScreenDisplayerMethod.invoke(victoryMenuState);

        assertNotNull(screenDisplayer);
        assertTrue(screenDisplayer instanceof VictoryMenuDisplayer);
    }

    @Test
    void testAllowInputSpamUsingReflection() throws Exception {
        Method allowInputSpamMethod = State.class.getDeclaredMethod("allowInputSpam");
        allowInputSpamMethod.setAccessible(true);

        boolean allowInputSpam = (boolean) allowInputSpamMethod.invoke(victoryMenuState);

        assertFalse(allowInputSpam);
    }
}
