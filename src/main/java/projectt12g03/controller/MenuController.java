package projectt12g03.controller;

import projectt12g03.Game;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.Menu;

import java.io.IOException;

public class MenuController extends Controller<Menu> {
    public final String startGame = "Start";
    public final String leaveGame = "Exit";

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
                if (getModel().getCurrentEntry().equals(leaveGame)) game.setState(null);
                if (getModel().getCurrentEntry().equals(startGame)) /* add game.setState(some new Room Builder) */ ;
                break;
        }
    }

}
