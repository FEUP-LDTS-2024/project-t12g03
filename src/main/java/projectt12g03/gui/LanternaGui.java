package projectt12g03.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LanternaGui {

    private Terminal terminal;
    private TerminalSize terminalSize;

    private Screen screen;
    private TextGraphics textGraphics;

    public LanternaGui(int width, int height, String WindowName) {
        try {
            setTerminalSize(width,height);
            DefaultTerminalFactory dfTerminal = new DefaultTerminalFactory();
            dfTerminal.setTerminalEmulatorFontConfiguration(loadFont()).setTerminalEmulatorTitle(WindowName).setInitialTerminalSize(terminalSize);
            terminal = dfTerminal.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary
            textGraphics = screen.newTextGraphics();
            terminalSize = screen.getTerminalSize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AWTTerminalFontConfiguration loadFont() throws FontFormatException, URISyntaxException, IOException {
            URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
            File fontFile = new File(resource.toURI());

            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

            Font newfont = font.deriveFont(Font.PLAIN, 4);

            return AWTTerminalFontConfiguration.newInstance(newfont);
    }

    public TerminalSize getTerminalSize(){
        return terminalSize;
    }

    public void setTerminalSize(int width,int height){
        terminalSize = new TerminalSize(width,height);
    }

    public TextGraphics getTextGraphics(){
        return textGraphics;
    }



}
