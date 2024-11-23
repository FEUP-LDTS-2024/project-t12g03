package projectt12g03.controller;

import projectt12g03.Game;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.menu.Menu;
import projectt12g03.model.room.Room;
import projectt12g03.model.room.RoomBuilder;
import projectt12g03.state.GameState;

import java.io.IOException;

public class MenuController extends Controller<Menu> {
    public MenuController(Menu menu) {
        super(menu);
    }

    @Override
    public void step(Game game, LanternaGui.userInput action, long time) throws IOException {
        switch (action) {
            case UP:
                getModel().moveUp();
                break;
            case DOWN:
                getModel().moveDown();
                break;
            case SELECT:
                if (getModel().getCurrentEntry().equals("exit")) game.setState(null);
                if (getModel().getCurrentEntry().equals("start")) game.setState(new GameState(new RoomBuilder(0).createRoom()));
                break;
        }
    }

}
