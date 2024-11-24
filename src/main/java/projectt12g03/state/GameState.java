package projectt12g03.state;

import projectt12g03.controller.PlayerController;
import projectt12g03.controller.RoomController;
import projectt12g03.controller.Controller;
import projectt12g03.model.game.room.Room;
import projectt12g03.view.GameDisplayer;
import projectt12g03.view.ScreenDisplayer;

import java.io.IOException;

public class GameState extends State<Room> {

    public GameState(Room model) throws IOException { super(model); }

    @Override
    protected Controller<Room> getController() {
        return new RoomController(getModel(), new PlayerController(getModel()));
    }

    @Override
    protected ScreenDisplayer<Room> getScreenDisplayer() {
        return new GameDisplayer(getModel());
    }

    @Override
    protected boolean allowInputSpam() {
        return false;
    }
}
