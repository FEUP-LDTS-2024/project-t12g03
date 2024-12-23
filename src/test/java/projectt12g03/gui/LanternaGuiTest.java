package projectt12g03.gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.model.Vector;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LanternaGuiTest {

    private LanternaGui gui;
    private TerminalScreen mockScreen;
    private TextGraphics mockGraphics;

    @BeforeEach
    void setUp() throws Exception {
        mockScreen = mock(TerminalScreen.class);
        mockGraphics = mock(TextGraphics.class);

        gui = spy(new LanternaGui(80, 24, "Test Title"));
        gui.setScreen(mockScreen);

        when(mockScreen.newTextGraphics()).thenReturn(mockGraphics);
    }

    @Test
    void testKeyPressedAndReleasedUsingReflection() throws Exception {
        Field keyPressedField = LanternaGui.class.getDeclaredField("keyPressed");
        Field priorityKeyField = LanternaGui.class.getDeclaredField("priorityKey");

        keyPressedField.setAccessible(true);
        priorityKeyField.setAccessible(true);

        KeyEvent keyPressEvent = mock(KeyEvent.class);
        when(keyPressEvent.getKeyCode()).thenReturn(KeyEvent.VK_A);

        KeyEvent keyReleaseEvent = mock(KeyEvent.class);
        when(keyReleaseEvent.getKeyCode()).thenReturn(KeyEvent.VK_A);

        Method createKeyAdapterMethod = LanternaGui.class.getDeclaredMethod("createKeyAdapter");
        createKeyAdapterMethod.setAccessible(true);
        KeyAdapter keyAdapter = (KeyAdapter) createKeyAdapterMethod.invoke(gui);

        keyAdapter.keyPressed(keyPressEvent);
        assertEquals(keyPressEvent, keyPressedField.get(gui));

        gui.setKeySpam(false);
        keyAdapter.keyReleased(keyReleaseEvent);
        assertNull(keyPressedField.get(gui));

        gui.setKeySpam(true);
        keyAdapter.keyPressed(keyPressEvent);
        keyAdapter.keyReleased(keyReleaseEvent);
        assertNull(keyPressedField.get(gui));
    }

    @Test
    void testGetNextUserInput() throws Exception {
        Field keyPressedField = LanternaGui.class.getDeclaredField("keyPressed");
        keyPressedField.setAccessible(true);


        //NULL
        keyPressedField.set(gui, null);
        assertEquals(GUI.userInput.NONE, gui.getNextUserInput());


        //LEFT1
        KeyEvent leftKeyEvent = mock(KeyEvent.class);
        when(leftKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_LEFT);

        keyPressedField.set(gui, leftKeyEvent);
        assertEquals(GUI.userInput.LEFT, gui.getNextUserInput());

        //LEFT2
        KeyEvent left2KeyEvent = mock(KeyEvent.class);
        when(left2KeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_A);

        keyPressedField.set(gui, left2KeyEvent);
        assertEquals(GUI.userInput.LEFT, gui.getNextUserInput());

        //RIGHT1
        KeyEvent rightKeyEvent = mock(KeyEvent.class);
        when(rightKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_RIGHT);

        keyPressedField.set(gui, rightKeyEvent);
        assertEquals(GUI.userInput.RIGHT, gui.getNextUserInput());

        //RIGHT2
        KeyEvent right2KeyEvent = mock(KeyEvent.class);
        when(right2KeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_D);

        keyPressedField.set(gui, right2KeyEvent);
        assertEquals(GUI.userInput.RIGHT, gui.getNextUserInput());

        //UP1
        KeyEvent upKeyEvent = mock(KeyEvent.class);
        when(upKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_UP);

        keyPressedField.set(gui, upKeyEvent);
        assertEquals(GUI.userInput.UP, gui.getNextUserInput());

        //UP2
        KeyEvent up2KeyEvent = mock(KeyEvent.class);
        when(up2KeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_W);

        keyPressedField.set(gui, up2KeyEvent);
        assertEquals(GUI.userInput.UP, gui.getNextUserInput());

        //DOWN1
        KeyEvent downKeyEvent = mock(KeyEvent.class);
        when(downKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_DOWN);

        keyPressedField.set(gui, downKeyEvent);
        assertEquals(GUI.userInput.DOWN, gui.getNextUserInput());

        //DOWN2
        KeyEvent down2KeyEvent = mock(KeyEvent.class);
        when(down2KeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_S);

        keyPressedField.set(gui, down2KeyEvent);
        assertEquals(GUI.userInput.DOWN, gui.getNextUserInput());

        //ESCAPE
        KeyEvent escapeKeyEvent = mock(KeyEvent.class);
        when(escapeKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_ESCAPE);

        keyPressedField.set(gui, escapeKeyEvent);
        assertEquals(GUI.userInput.PAUSE, gui.getNextUserInput());

        //ENTER
        KeyEvent enterKeyEvent = mock(KeyEvent.class);
        when(enterKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_ENTER);

        keyPressedField.set(gui, enterKeyEvent);
        assertEquals(GUI.userInput.SELECT, gui.getNextUserInput());

        //SPACE
        KeyEvent spaceKeyEvent = mock(KeyEvent.class);
        when(spaceKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_SPACE);

        keyPressedField.set(gui, spaceKeyEvent);
        assertEquals(GUI.userInput.ATTACK, gui.getNextUserInput());

        //SHIFT
        KeyEvent shiftKeyEvent = mock(KeyEvent.class);
        when(shiftKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_SHIFT);

        keyPressedField.set(gui, shiftKeyEvent);
        assertEquals(GUI.userInput.SPRINT, gui.getNextUserInput());

        //H
        KeyEvent hKeyEvent = mock(KeyEvent.class);
        when(hKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_H);

        keyPressedField.set(gui, hKeyEvent);
        assertEquals(GUI.userInput.HEAL, gui.getNextUserInput());

        //1
        KeyEvent oneKeyEvent = mock(KeyEvent.class);
        when(oneKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_1);

        keyPressedField.set(gui, oneKeyEvent);
        assertEquals(GUI.userInput.ONE, gui.getNextUserInput());

        //2
        KeyEvent twoKeyEvent = mock(KeyEvent.class);
        when(twoKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_2);

        keyPressedField.set(gui, twoKeyEvent);
        assertEquals(GUI.userInput.TWO, gui.getNextUserInput());

        //3
        KeyEvent threeKeyEvent = mock(KeyEvent.class);
        when(threeKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_3);

        keyPressedField.set(gui, threeKeyEvent);
        assertEquals(GUI.userInput.THREE, gui.getNextUserInput());

        //4
        KeyEvent fourKeyEvent = mock(KeyEvent.class);
        when(fourKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_4);

        keyPressedField.set(gui, fourKeyEvent);
        assertEquals(GUI.userInput.FOUR, gui.getNextUserInput());

        //UNDEFINED
        KeyEvent unknownKeyEvent = mock(KeyEvent.class);
        when(unknownKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_UNDEFINED);

        keyPressedField.set(gui, unknownKeyEvent);
        assertEquals(GUI.userInput.NONE, gui.getNextUserInput());
    }

    @Test
    void testKeySpamBehavior() throws Exception {
        Field keySpamField = LanternaGui.class.getDeclaredField("keySpam");
        keySpamField.setAccessible(true);

        gui.setKeySpam(true);
        assertTrue((boolean) keySpamField.get(gui)); // Ensure keySpam is enabled

        gui.setKeySpam(false);
        assertFalse((boolean) keySpamField.get(gui)); // Ensure keySpam is disabled
    }

    @Test
    void testDrawText() {
        Vector position = new Vector(5, 10);
        String text = "Hello World";
        TextColor color = TextColor.ANSI.BLUE;

        gui.drawText(position, text, color);

        verify(mockGraphics).setForegroundColor(color);
        verify(mockGraphics).putString(position.getX(), position.getY(), text);
    }

    @Test
    void testDrawPixel() {
        double x = 15.0, y = 20.0;
        TextColor color = TextColor.ANSI.RED;

        gui.drawPixel(x, y, color);

        verify(mockGraphics).setBackgroundColor(color);
        verify(mockGraphics).setCharacter((int) x, (int) y, ' ');
    }

    @Test
    void testClear() {
        gui.clear();
        verify(mockScreen).clear();
    }

    @Test
    void testRefresh() throws Exception {
        gui.refresh();
        verify(mockScreen).refresh();
    }

    @Test
    void testClose() throws Exception {
        gui.close();
        verify(mockScreen).close();
    }
}
