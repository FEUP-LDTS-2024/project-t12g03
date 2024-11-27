package projectt12g03;

import projectt12g03.gui.LanternaGui;
import projectt12g03.model.menu.MainMenu;
import projectt12g03.state.menuSate.MainMenuState;
import projectt12g03.state.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Game {
    public static final int FPS = 30;
    public static final int terminalWidth = 40;
    public static final int terminalHeight = 20;
    private static final String windowName = "GAME";
    private final Stack<State> stateStack;
    private final LanternaGui gui;

    public Game() throws IOException, URISyntaxException, FontFormatException {
        this.gui = new LanternaGui(terminalWidth, terminalHeight, windowName);
        this.stateStack = new Stack<>();
        stateStack.push(new MainMenuState(new MainMenu()));
    }
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        new Game().start();
    }

    public int getFps() { return FPS; }

    public void setState(State state) {
        this.stateStack.push(state);
    }

    public void popState() {
        if (!stateStack.isEmpty()) {
            stateStack.pop();
        }
    }

    public State getCurrentState() {
        if (!stateStack.isEmpty()){
            return stateStack.peek();
        }
        return null;
    }

    public void setKeySpam(boolean keySpam) {
        gui.setKeySpam(keySpam);
    }

    private void start() throws IOException {
        long frameTime = 1000 / FPS, frameCount = 0;


        while(getCurrentState() != null) {
            long startTime = System.currentTimeMillis();

            getCurrentState().step(this, gui, frameCount);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try { if (sleepTime > 0) Thread.sleep(sleepTime); }
            catch (InterruptedException e) { }
            frameCount++;
        }

        gui.close();
    }


}
