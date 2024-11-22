package projectt12g03.controller;

import projectt12g03.Game;
import projectt12g03.gui.GUI;
import projectt12g03.model.elements.Player;
import projectt12g03.model.room.Room;

import java.io.IOException;
import java.math.RoundingMode;

public class GameController extends Controller<Room> {
    private PlayerController playerController;

    public GameController(Room model) {
        super(model);
        playerController = new PlayerController(getModel().getPlayer());

    }

    @Override
    public void step(Game game, GUI.userInput action, long time) throws IOException {
        playerController.step(game, action, time);
    }
}
