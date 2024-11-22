package projectt12g03.state;

import projectt12g03.controller.Controller;
import projectt12g03.controller.GameController;
import projectt12g03.controller.PlayerController;
import projectt12g03.model.room.Room;
import projectt12g03.view.GameDisplayer;
import projectt12g03.view.ScreenDisplayer;

import java.io.IOException;
import java.math.RoundingMode;

public class GameState extends State<Room> {

    public GameState(Room model) throws IOException { super(model); }

    @Override
    protected Controller<Room> getController() {
        return new GameController(getModel());
    }

    @Override
    protected ScreenDisplayer<Room> getScreenDisplayer() {
        return new GameDisplayer(getModel());
    }

    @Override
    protected boolean allowInputSpam() {
        return true;
    }
}
