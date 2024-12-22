package projectt12g03.state;

import projectt12g03.controller.Path;
import projectt12g03.controller.gameController.*;
import projectt12g03.controller.Controller;
import projectt12g03.model.game.room.RoomManager;
import projectt12g03.view.gameDisplayer.GameDisplayer;
import projectt12g03.view.ScreenDisplayer;
import projectt12g03.view.gameDisplayer.GameDisplayerHub;

import java.io.IOException;

public class GameState extends State<RoomManager> {

    public GameState(RoomManager model) throws IOException { super(model); }

    @Override
    protected Controller<RoomManager> getController() throws IOException {
        return new GameController(getModel(), new PlayerController(getModel()), new MonsterController(getModel(), new Path()), new LaserController(getModel()), new HudController(getModel()));
    }

    @Override
    protected ScreenDisplayer<RoomManager> getScreenDisplayer() throws IOException {
        return new GameDisplayer(getModel(), new GameDisplayerHub());
    }

    @Override
    protected boolean allowInputSpam() { return true; }
}
