package projectt12g03.controller;

import projectt12g03.Game;
import projectt12g03.gui.GUI;
import projectt12g03.model.game.room.Room;

import java.io.IOException;

public class RoomController extends Controller<Room> {
    private final PlayerController playerController;

    public RoomController(Room room, PlayerController playerController) {
        super(room);

        this.playerController = playerController;
    }

    @Override
    public void step(Game game, GUI.userInput action, long time) throws IOException {
        playerController.step(game, action, time);
    }
}
