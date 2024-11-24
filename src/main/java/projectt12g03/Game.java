package projectt12g03;

import projectt12g03.gui.LanternaGui;
import projectt12g03.model.menu.MainMenu;
import projectt12g03.state.menuSate.MainMenuState;
import projectt12g03.state.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    public static final int terminalWidth = 40;
    public static final int terminalHeight = 20;
    private static final String windowName = "GAME";
    private final LanternaGui gui;
    private State<?> state;

    public Game() throws IOException, URISyntaxException, FontFormatException {
        this.gui = new LanternaGui(terminalWidth, terminalHeight, windowName);
        this.state = new MainMenuState(new MainMenu());
    }
    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
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
