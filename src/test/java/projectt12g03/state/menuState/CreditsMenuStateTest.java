package projectt12g03.state.menuState;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.controller.Controller;
import projectt12g03.controller.menuController.CreditsMenuController;
import projectt12g03.model.menu.CreditsMenu;
import projectt12g03.state.State;
import projectt12g03.view.ScreenDisplayer;
import projectt12g03.view.menuDisplayer.CreditsMenuDisplayer;

import java.io.IOException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreditsMenuStateTest {

    private CreditsMenu mockCreditsMenu;
    private CreditsMenuState creditsMenuState;

    @BeforeEach
    void setUp() throws IOException {
        mockCreditsMenu = mock(CreditsMenu.class);
        creditsMenuState = Mockito.spy(new CreditsMenuState(mockCreditsMenu));
    }

    @Test
    void testGetControllerUsingReflection() throws Exception {
        Method getControllerMethod = State.class.getDeclaredMethod("getController");
        getControllerMethod.setAccessible(true);

        Controller<CreditsMenu> controller = (Controller<CreditsMenu>) getControllerMethod.invoke(creditsMenuState);

        assertNotNull(controller);
        assertTrue(controller instanceof CreditsMenuController);
    }

    @Test
    void testGetScreenDisplayerUsingReflection() throws Exception {
        Method getScreenDisplayerMethod = State.class.getDeclaredMethod("getScreenDisplayer");
        getScreenDisplayerMethod.setAccessible(true);

        ScreenDisplayer<CreditsMenu> screenDisplayer = (ScreenDisplayer<CreditsMenu>) getScreenDisplayerMethod.invoke(creditsMenuState);

        assertNotNull(screenDisplayer);
        assertTrue(screenDisplayer instanceof CreditsMenuDisplayer);
    }

    @Test
    void testAllowInputSpamUsingReflection() throws Exception {
        Method allowInputSpamMethod = State.class.getDeclaredMethod("allowInputSpam");
        allowInputSpamMethod.setAccessible(true);

        boolean allowInputSpam = (boolean) allowInputSpamMethod.invoke(creditsMenuState);

        assertFalse(allowInputSpam);
    }
}
