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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.awt.event.KeyEvent.*;

public class LanternaGui implements GUI {
    private String title;

    private TerminalSize terminalSize;
    private TerminalScreen screen;

    //remove "//" after adding player
    //private Set<Integer> pressedKeys;
    private final KeyAdapter keyAdapter;
    private KeyEvent keyPressed;
    private KeyEvent priorityKey;
    private boolean keySpam;


    public LanternaGui(int terminalWidth, int terminalHeight, String title) throws IOException, URISyntaxException, FontFormatException {
        this.terminalSize = new TerminalSize(terminalWidth, terminalHeight);
        this.keyPressed = null;
        this.priorityKey = null;
        this.keySpam = false;
        //remove "//" after adding player
        //this.pressedKeys = new HashSet<>();
        this.keyAdapter = createKeyAdapter();
        this.screen = createScreen();
        this.title = title;
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
                .setTerminalEmulatorFontConfiguration(fontConfig)
                .setTerminalEmulatorTitle(title);

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

    public int getHeight() { return terminalSize.getRows(); }

    //remove "//" after adding player
    //public Set<Integer> getPressedKeys() { return pressedKeys; }

    private KeyAdapter createKeyAdapter() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (keySpam) {
                    //remove "//" after adding player
                    //pressedKeys.add(e.getKeyCode());
                    keyPressed = priorityKey = e;
                }
                else
                    keyPressed = e;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (keySpam) {
                    //remove "//" after adding player
                    //pressedKeys.remove(e.getKeyCode());
                    keyPressed = priorityKey = null;
                }
                else
                    keyPressed = priorityKey;
            }
        };
    }

    @Override
    public userInput getNextUserInput() {
        //remove "//" after adding player
        //List<userInput> inputs = new ArrayList<>();
        //inputs.add(userInput.NONE);

        //remove "/* */" after adding player
        if (/*pressedKeys.isEmpty() && */keyPressed == null)
            return userInput.NONE;

        int keyCode = keyPressed.getKeyCode();
        keyPressed = priorityKey;

        //if(!keySpam) { remove "//" after adding player
            return switch (keyCode) {
                case VK_LEFT -> userInput.LEFT;
                case VK_RIGHT -> userInput.RIGHT;
                case VK_UP -> userInput.UP;
                case VK_DOWN -> userInput.DOWN;
                case VK_ESCAPE -> userInput.PAUSE;
                case VK_ENTER -> userInput.SELECT;
                case VK_SPACE -> userInput.ATTACK;
                default -> userInput.NONE;
            };
        //} //remove "//" after adding player
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


    public void drawCharacter(Vector position, char c, TextColor color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(color);
        tg.putString((int) position.getX(), (int) position.getY(), "" + c);
    }

    @Override   //used for tests
    public void drawWall(Vector position){ //may change
        drawCharacter(position,'#',new TextColor.RGB(255,255,255));
    }

    @Override   //used for tests
    public void drawPlayer(Vector position, char design, TextColor color){ //will change when player becomes complex
        drawCharacter(position, design, color);
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

    public void setScreen(TerminalScreen screen) {
        this.screen = screen;
    }
}
