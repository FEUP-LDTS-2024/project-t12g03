package projectt12g03;

import projectt12g03.gui.LanternaGui;
import projectt12g03.model.Menu;
import projectt12g03.state.MenuState;
import projectt12g03.state.State;

import java.io.IOException;

public class Game {
    public static final int terminalWidth = 40;
    public static final int terminalHeight = 20;
    private static final String windowName = "GAME";
    private final LanternaGui gui;
    private State<?> state;

    public Game() throws IOException {
        this.gui = new LanternaGui(terminalWidth, terminalHeight, windowName);
        this.state = new MenuState(new Menu());
    }
    public static void main(String[] args) throws IOException {
        new Game().start();
    }

    public void setKeySpam(boolean keySpam) {
        gui.setKeySpam(keySpam);
    }

    public void setState(State<?> state) { this.state = state; }

    private void start() throws IOException {
        int FPS = 30;
        long frameTime = 1000 / FPS;

        while(state != null) {
            long startTime = System.currentTimeMillis();

            state.step(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try { if (sleepTime > 0) Thread.sleep(sleepTime); }
            catch (InterruptedException e) { }
        }

        gui.close();
    }


}
