package projectt12g03.state;

import projectt12g03.controller.RoomController;
import projectt12g03.controller.Controller;
import projectt12g03.model.room.Room;
import projectt12g03.view.GameDisplayer;
import projectt12g03.view.ScreenDisplayer;

import java.io.IOException;

public class GameState extends State<Room> {

    public GameState(Room model) throws IOException { super(model); }

    @Override
    protected Controller<Room> getController() {
        return new RoomController(getModel());
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
