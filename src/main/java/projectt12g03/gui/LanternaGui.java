package projectt12g03.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import projectt12g03.model.Vector;
import projectt12g03.model.elements.Player;

import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static java.awt.event.KeyEvent.*;

public class LanternaGui {

    public enum userInput { UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT}

    private AWTTerminalFrame terminal;
    private TerminalSize terminalSize;

    private TerminalScreen screen;
    private TextGraphics textGraphics;

    private final KeyAdapter keyAdapter;
    private KeyEvent keyPressed;
    private KeyEvent priorityKey;
    private boolean keySpam;

    //REMOVE LATER
    //private Player player;

    public LanternaGui(int terminalWidth, int terminalHeight, String WindowName) {
        this.keyAdapter = createKeyAdapter();
        try {
            setTerminalSize(terminalWidth, terminalHeight + 1);
            DefaultTerminalFactory dfTerminal = new DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize)
                    .setForceAWTOverSwing(true);
            AWTTerminalFontConfiguration fontConfig = loadFont();
            dfTerminal.setTerminalEmulatorFontConfiguration(fontConfig);

            screen = dfTerminal.createScreen();
            terminal = (AWTTerminalFrame) screen.getTerminal();
            terminal.getComponent(0).addKeyListener(getKeyAdapter());

            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.keyPressed = null;
        this.priorityKey = null;
        this.keySpam = false;
        //REMOVE PLAYER (ONLY HERE TEMPORARY)
        //this.player = new Player(5, 5);
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

    public TerminalSize getTerminalSize(){
        return terminalSize;
    }

    public int getWidth() { return terminalSize.getColumns(); }

    public int getheight() { return terminalSize.getRows(); }

    public KeyAdapter getKeyAdapter() {
        return keyAdapter;
    }

    public void setTerminalSize(int width,int height){
        terminalSize = new TerminalSize(width,height);
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

    public void drawText(Vector position, String text, TextColor color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(color);
        tg.putString((int) position.getX(), (int) position.getY(), text);
    }

    private void drawCharacter(Vector position, char c, TextColor color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(color);
        tg.putString((int) position.getX(), (int) position.getX() + 1, "" + c);
    }

    public void clear() {
        screen.clear();
    }

    public void refresh() throws IOException {
        screen.refresh();
    }

    public void close() throws IOException {
        screen.close();
    }

}
