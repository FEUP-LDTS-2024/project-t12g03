package projectt12g03.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import projectt12g03.model.Vector;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static java.awt.event.KeyEvent.*;

public class LanternaGui implements GUI {
    private String title;

    private TerminalSize terminalSize;
    private TerminalScreen screen;

    private final KeyAdapter keyAdapter;
    private KeyEvent keyPressed;
    private KeyEvent priorityKey;
    private boolean keySpam;


    public LanternaGui(int terminalWidth, int terminalHeight, String title) throws IOException, URISyntaxException, FontFormatException {
        this.terminalSize = new TerminalSize(terminalWidth, terminalHeight);
        this.keyPressed = null;
        this.priorityKey = null;
        this.keySpam = false;
        this.title = title;
        this.keyAdapter = createKeyAdapter();
        this.screen = createScreen();
    }

    private TerminalScreen createScreen() throws URISyntaxException, IOException, FontFormatException {
        DefaultTerminalFactory terminal = createTerminal();
        createFrame(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        return screen;
    }

    private DefaultTerminalFactory createTerminal() throws URISyntaxException, IOException, FontFormatException {
        AWTTerminalFontConfiguration fontConfig = loadFont();
        DefaultTerminalFactory terminal = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize)
                .setForceAWTOverSwing(true)
                .setTerminalEmulatorFontConfiguration(fontConfig);

        return terminal;
    }

    private void createFrame(DefaultTerminalFactory terminal) throws IOException {
        screen = terminal.createScreen();

        AWTTerminalFrame terminalFrame = (AWTTerminalFrame) screen.getTerminal();
        terminalFrame.getComponent(0).addKeyListener(keyAdapter);
        terminalFrame.setTitle(title);
    }

    private AWTTerminalFontConfiguration loadFont() throws URISyntaxException, IOException, FontFormatException {
            URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
            File fontFile = new File(resource.toURI());

            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

            Font newfont = font.deriveFont(Font.PLAIN, 25);

            return AWTTerminalFontConfiguration.newInstance(newfont);
    }

    public int getWidth() { return terminalSize.getColumns(); }

    public int getheight() { return terminalSize.getRows(); }

    public KeyAdapter getKeyAdapter() {
        return keyAdapter;
    }

    private KeyAdapter createKeyAdapter() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (keySpam)
                    keyPressed = priorityKey = e;
                else
                    keyPressed = e;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (keySpam)
                    keyPressed = priorityKey = null;
                else
                    keyPressed = priorityKey;
            }
        };
    }

    @Override
    public userInput getNextUserInput() {
        if (keyPressed == null)
            return userInput.NONE;

        int keyCode = keyPressed.getKeyCode();
        keyPressed = priorityKey;

        return switch (keyCode) {
            case VK_A -> userInput.LEFT;
            case VK_D -> userInput.RIGHT;
            case VK_W -> userInput.UP;
            case VK_S -> userInput.DOWN;
            case VK_ESCAPE -> userInput.QUIT;
            case VK_ENTER -> userInput.SELECT;
            default -> userInput.NONE;
        };
    }

    public void setKeySpam(boolean keySpam) {
        if (!keySpam)
            priorityKey = null;
        this.keySpam = keySpam;
    }

    @Override
    public void drawText(Vector position, String text, TextColor color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(color);
        tg.putString((int) position.getX(), (int) position.getY(), text);
    }

    @Override
    public void drawCharacter(Vector position, char c, TextColor color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(color);
        tg.putString((int) position.getX(), (int) position.getX() + 1, "" + c);
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }

}
