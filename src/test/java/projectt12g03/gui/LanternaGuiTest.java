package projectt12g03.gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.model.Vector;

import javax.swing.text.Position;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class LanternaGuiTest {
    private TerminalScreen screen;
    private LanternaGui gui;
    private TextGraphics tg;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException {
        screen = Mockito.mock(TerminalScreen.class);
        tg = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(tg);

        gui = new LanternaGui(20,20,"Test");

        gui.setScreen(screen);
    }
}
