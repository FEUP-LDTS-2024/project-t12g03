package projectt12g03.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

import static java.awt.event.KeyEvent.*;

public class LanternaGui implements GUI {
    private final String title;
    private final KeyAdapter keyAdapter;
    private final TerminalSize terminalSize;

    private DefaultTerminalFactory terminal;
    private TerminalScreen screen;

    private KeyEvent keyPressed;
    private KeyEvent priorityKey;
    private boolean keySpam;


    public LanternaGui(int terminalWidth, int terminalHeight, String title) throws IOException, URISyntaxException, FontFormatException {
        this.title = title;
        this.terminalSize = new TerminalSize(terminalWidth, terminalHeight);
        this.keyPressed = null;
        this.priorityKey = null;
        this.keySpam = false;
        this.keyAdapter = createKeyAdapter();
        this.terminal = new DefaultTerminalFactory();
        this.screen = null;
    }

    public void createScreen() throws URISyntaxException, IOException, FontFormatException {
        AWTTerminalFontConfiguration fontConfig = loadFont();

        terminal = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize)
                .setForceAWTOverSwing(true)
                .setTerminalEmulatorFontConfiguration(fontConfig);

        screen = terminal.createScreen();

        AWTTerminalFrame terminalFrame = (AWTTerminalFrame) screen.getTerminal();
        terminalFrame.getComponent(0).addKeyListener(keyAdapter);
        terminalFrame.setTitle(title);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
    }

    private AWTTerminalFontConfiguration loadFont() throws URISyntaxException, IOException, FontFormatException {
            URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
            File fontFile = new File(Objects.requireNonNull(resource).toURI());

            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

            Font newfont = font.deriveFont(Font.PLAIN, 3);

            return AWTTerminalFontConfiguration.newInstance(newfont);
    }

    private KeyAdapter createKeyAdapter() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (keySpam) {
                    keyPressed = priorityKey = e;
                }
                else
                    keyPressed = e;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (keySpam) {
                    keyPressed = priorityKey = null;
                }
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
            case VK_A, VK_LEFT -> userInput.LEFT;
            case VK_D, VK_RIGHT -> userInput.RIGHT;
            case VK_W, VK_UP -> userInput.UP;
            case VK_S, VK_DOWN -> userInput.DOWN;
            case VK_ESCAPE -> userInput.PAUSE;
            case VK_ENTER -> userInput.SELECT;
            case VK_SPACE -> userInput.ATTACK;
            case VK_SHIFT -> userInput.SPRINT;
            case VK_H -> userInput.HEAL;
            case VK_1 -> userInput.ONE;
            case VK_2 -> userInput.TWO;
            case VK_3 -> userInput.THREE;
            case VK_4 -> userInput.FOUR;
            default -> userInput.NONE;
        };
    }

    public void setKeySpam(boolean keySpam) {
        if (!keySpam)
            priorityKey = null;
        this.keySpam = keySpam;
    }

    @Override
    public void drawPixel(double x, double y, TextColor color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(color);
        tg.setCharacter((int) x, (int) y, ' ');
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
