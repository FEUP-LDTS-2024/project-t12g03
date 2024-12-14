package projectt12g03.state;

import projectt12g03.controller.gameController.MonsterController;
import projectt12g03.controller.gameController.PlayerController;
import projectt12g03.controller.gameController.GameController;
import projectt12g03.controller.Controller;
import projectt12g03.model.game.room.RoomManager;
import projectt12g03.view.gameDisplayer.GameDisplayer;
import projectt12g03.view.ScreenDisplayer;

import java.io.IOException;

public class GameState extends State<RoomManager> {

    public GameState(RoomManager model) throws IOException { super(model); }

    @Override
    protected Controller<RoomManager> getController() {
        return new GameController(getModel(), new PlayerController(getModel()), new MonsterController(getModel()));
    }

    @Override
    protected ScreenDisplayer<RoomManager> getScreenDisplayer() throws IOException {
        return new GameDisplayer(getModel());
    }

    @Override
    protected boolean allowInputSpam() { return true; }
}
