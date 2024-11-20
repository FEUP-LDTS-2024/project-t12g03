package projectt12g03.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import projectt12g03.model.elements.Player;

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

    //REMOVE LATER
    //private Player player;

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
        //this.player = new Player(5, 5);
    }

    private AWTTerminalFontConfiguration loadFont() throws URISyntaxException, IOException, FontFormatException {
            URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
            File fontFile = new File(resource.toURI());

            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

            Font newfont = font.deriveFont(Font.PLAIN, 18);

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
                keyPressed = priorityKey = e;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keyPressed = priorityKey = null;
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

    //Temporary function just for testing
    //public void drawPlayer() throws IOException {
    //    screen.clear();
    //    player.draw(getNewTextGraphics());
    //    screen.refresh();
    //}

}
