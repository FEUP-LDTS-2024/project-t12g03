package projectt12g03;

import projectt12g03.gui.LanternaGui;
import projectt12g03.model.menu.Menu;
import projectt12g03.state.menu.MenuState;
import projectt12g03.state.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Stack;

public class Game {
    public static final int terminalWidth = 40;
    public static final int terminalHeight = 20;
    private final Stack<State> stateStack;
    private static final String windowName = "GAME";
    private final LanternaGui gui;
    //private State state;  probably gonna be permanent delete

    public Game() throws IOException, URISyntaxException, FontFormatException {
        this.gui = new LanternaGui(terminalWidth, terminalHeight, windowName);
        //this.state = new MenuState(new Menu()); probably gonna be permanent delete
        this.stateStack = new Stack<>();
        stateStack.push(new MenuState(new Menu()));
    }
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        new Game().start();
    }

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

    //public void setState(State state) { this.state = state; } probably gonna be permanent delete

    private void start() throws IOException {
        int FPS = 30;
        long frameTime = 1000 / FPS;

        while(getCurrentState() != null) {
            long startTime = System.currentTimeMillis();

            getCurrentState().step(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try { if (sleepTime > 0) Thread.sleep(sleepTime); }
            catch (InterruptedException e) { }
        }

        gui.close();
    }


}
