package projectt12g03;

import projectt12g03.gui.LanternaGui;
import projectt12g03.model.Menu;
import projectt12g03.state.MenuState;
import projectt12g03.state.State;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {
    private boolean running = true;
    public static final int terminalWidth = 100;
    public static final int terminalHeight = 56;
    private static final String windowName = "GAME";
    private final LanternaGui gui;
    private State state;

    public Game() throws IOException {
        this.gui = new LanternaGui(terminalWidth, terminalHeight, windowName);
        this.state = new MenuState(new Menu());
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        new Game().start();
    }

    public void setState(State<?> state) { this.state = state; }

    private void start() throws InterruptedException, IOException {
        int FPS = 30;
        long frameTime = 1000 / FPS;

        while(running) {
            long startTime = System.currentTimeMillis();

            state.step(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try { if (sleepTime > 0) Thread.sleep(sleepTime); }
            catch (InterruptedException e) { }
        }

        System.exit(0);
    }
}
