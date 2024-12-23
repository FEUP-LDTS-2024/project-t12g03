package projectt12g03;

import projectt12g03.gui.LanternaGui;
import projectt12g03.model.menu.MainMenu;
import projectt12g03.state.menuState.MainMenuState;
import projectt12g03.state.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Game {
    public static final int FPS = 15;
    public static final int terminalWidth = 320;
    public static final int terminalHeight = 288;
    private static final String windowName = "SPEAR BOY";
    private final Stack<State<?>> stateStack;
    private final LanternaGui gui;

    public Game() throws IOException, URISyntaxException, FontFormatException {
        this.gui = new LanternaGui(terminalWidth, terminalHeight, windowName);
        this.stateStack = new Stack<>();
        stateStack.push(new MainMenuState(new MainMenu()));
    }
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        new Game().start();
    }

    public void setState(State<?> state) {
        this.stateStack.push(state);
    }

    public void popState() {
        if (!stateStack.isEmpty()) {
            stateStack.pop();
        }
    }

    public State<?> getCurrentState() {
        if (!stateStack.isEmpty()){
            return stateStack.peek();
        }
        return null;
    }

    public void setKeySpam(boolean keySpam) {
        gui.setKeySpam(keySpam);
    }

    private void start() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        long frameTime = 1000 / FPS, frameCount = 0;

        gui.createScreen();

        while(getCurrentState() != null) {
            long startTime = System.currentTimeMillis();

            getCurrentState().step(this, gui, frameCount);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            if (sleepTime > 0) {
                Thread.sleep(sleepTime);
            }

            frameCount++;
        }

        gui.close();
    }
}
