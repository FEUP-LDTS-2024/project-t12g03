package projectt12g03.state.menuState;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.controller.Controller;
import projectt12g03.controller.menuController.MainMenuController;
import projectt12g03.model.menu.MainMenu;
import projectt12g03.state.State;
import projectt12g03.view.ScreenDisplayer;
import projectt12g03.view.menuDisplayer.MainMenuDisplayer;

import java.io.IOException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MainMenuStateTest {

    private MainMenu mockMainMenu;
    private MainMenuState mainMenuState;

    @BeforeEach
    void setUp() throws IOException {
        mockMainMenu = mock(MainMenu.class);
        mainMenuState = Mockito.spy(new MainMenuState(mockMainMenu));
    }

    @Test
    void testGetControllerUsingReflection() throws Exception {
        Method getControllerMethod = State.class.getDeclaredMethod("getController");
        getControllerMethod.setAccessible(true);

        @SuppressWarnings("unchecked")
        Controller<MainMenu> controller = (Controller<MainMenu>) getControllerMethod.invoke(mainMenuState);

        assertNotNull(controller);
        assertTrue(controller instanceof MainMenuController);
    }

    @Test
    void testGetScreenDisplayerUsingReflection() throws Exception {
        Method getScreenDisplayerMethod = State.class.getDeclaredMethod("getScreenDisplayer");
        getScreenDisplayerMethod.setAccessible(true);

        @SuppressWarnings("unchecked")
        ScreenDisplayer<MainMenu> screenDisplayer = (ScreenDisplayer<MainMenu>) getScreenDisplayerMethod.invoke(mainMenuState);

        assertNotNull(screenDisplayer);
        assertTrue(screenDisplayer instanceof MainMenuDisplayer);
    }

    @Test
    void testAllowInputSpamUsingReflection() throws Exception {
        Method allowInputSpamMethod = State.class.getDeclaredMethod("allowInputSpam");
        allowInputSpamMethod.setAccessible(true);

        boolean allowInputSpam = (boolean) allowInputSpamMethod.invoke(mainMenuState);

        assertFalse(allowInputSpam);
    }
}
