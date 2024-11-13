package projectt12g03;

import projectt12g03.gui.LanternaGui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {
    private boolean running = true;
    //238
    public static final int terminalWidth = 160;
    //67
    public static final int terminalHeight = 90;
    private static final String windowName = "GAME";
    private final LanternaGui lanternaGui;

    public Game() {
        lanternaGui = new LanternaGui(terminalWidth, terminalHeight, windowName);
    }
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Game.class.getName());
        try {
            new Game().start();
        } catch (Exception e) {
            logger.log(Level.INFO, "An error occurred", e);
        }
    }

    private void start() throws InterruptedException, IOException {
        int FPS = 30;
        long frameTime = 1000 / FPS;

        while(running) {
            long startTime = System.currentTimeMillis();

            lanternaGui.update();

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            if (sleepTime > 0) Thread.sleep(sleepTime);
        }

        System.exit(0);
    }
}
