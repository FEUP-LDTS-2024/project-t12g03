package projectt12g03.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import projectt12g03.types.elements.Player;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static java.awt.event.KeyEvent.*;
import static java.awt.event.KeyEvent.VK_X;

public class LanternaGui {

    public enum userInput { UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT}
    private static final java.util.List<Integer> SPAM_KEYS = List.of(VK_LEFT, VK_RIGHT, VK_UP, VK_DOWN);

    private AWTTerminalFrame terminal;
    private TerminalSize terminalSize;

    private TerminalScreen screen;
    private TextGraphics textGraphics;

    private final KeyAdapter keyAdapter;
    private KeyEvent keyPressed;
    private KeyEvent priorityKey;

    //REMOVE LATER
    private Player player;

    public LanternaGui(int terminalWidth, int terminalHeight, String WindowName) {
        this.keyAdapter = createKeyAdapter();
        try {
            setTerminalSize(terminalWidth, terminalHeight);
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
        //REMOVE PLAYER (ONLY HERE TEMPORARY)
        this.player = new Player(5, 5);
    }

    private AWTTerminalFontConfiguration loadFont() throws URISyntaxException, IOException, FontFormatException {
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

    public int getWidth() { return terminalSize.getColumns(); }

    public int getheight() { return terminalSize.getRows(); }

    public KeyAdapter getKeyAdapter() {
        return keyAdapter;
    }

    public void setTerminalSize(int width,int height){
        terminalSize = new TerminalSize(width,height);
    }

    public TextGraphics getNewTextGraphics(){
        return screen.newTextGraphics();
    }

    private KeyAdapter createKeyAdapter() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (SPAM_KEYS.contains(e.getKeyCode()))
                    keyPressed = priorityKey = e;
                else
                    keyPressed = e;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (SPAM_KEYS.contains(e.getKeyCode()))
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
            case VK_LEFT -> userInput.LEFT;
            case VK_RIGHT -> userInput.RIGHT;
            case VK_UP -> userInput.UP;
            case VK_DOWN -> userInput.DOWN;
            case VK_ESCAPE -> userInput.QUIT;
            case VK_ENTER -> userInput.SELECT;
            default -> userInput.NONE;
        };
    }

    //Temporary function just for testing
    public void drawPlayer() throws IOException {
        screen.clear();
        player.draw(getNewTextGraphics());
        screen.refresh();
    }

    public void update() throws IOException {
        userInput action = getNextUserInput();
        player.move(action);
        drawPlayer();
    }
}
