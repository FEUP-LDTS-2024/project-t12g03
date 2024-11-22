package projectt12g03.controller;

import projectt12g03.Game;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.elements.Player;

import java.io.IOException;

public class PlayerController extends Controller<Player> {


    public PlayerController(Player model) { super(model); }

    @Override
    public void step(Game game, LanternaGui.userInput action, long time) throws IOException {
        switch (action) {
            case UP:
                getModel().moveUp();
                break;
            case DOWN:
                getModel().moveDown();
                break;
            case LEFT:
                getModel().moveLeft();
                break;
            case RIGHT:
                getModel().moveRight();
                break;
        }
    }
}
