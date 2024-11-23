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


    @Test
    void drawCharacter() {
        gui.drawCharacter(new Vector(3, 6),'H', new TextColor.RGB(255, 215, 0));

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(255, 215, 0));
        Mockito.verify(tg, Mockito.times(1)).putString(3, 6, "H");
    }

    @Test
    void drawText() {
        gui.drawText(new Vector(5, 10), "Hello World", new TextColor.RGB(51,102,153));

        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(51, 102, 153));
        Mockito.verify(tg, Mockito.times(1)).putString(5, 10, "Hello World");
    }
}
