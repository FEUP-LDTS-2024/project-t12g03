package projectt12g03.controller.menuController;

import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.menu.Menu;
import projectt12g03.model.game.room.RoomBuilder;
import projectt12g03.state.GameState;

import java.io.IOException;

//This class needs to be deleted or generalized to an abstract function
//Only reason i am not making it an abstract function is sometimes "exit" makes different things
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
                if (getModel().getCurrentEntry().equals("start")) game.setState(new GameState(new RoomBuilder(0).createRoom()));
                if (getModel().getCurrentEntry().equals("exit")) game.popState();
                break;
        }
    }

}
