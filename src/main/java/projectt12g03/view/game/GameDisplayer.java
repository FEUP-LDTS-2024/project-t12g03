package projectt12g03.view.game;

import projectt12g03.gui.GUI;
import projectt12g03.model.game.elements.Wall;
import projectt12g03.model.game.room.Room;
import projectt12g03.view.ScreenDisplayer;
import projectt12g03.view.game.elements.PlayerDisplayer;
import projectt12g03.view.game.elements.WallDisplayer;

import java.io.IOException;

public class GameDisplayer extends ScreenDisplayer<Room> {
    private PlayerDisplayer playerDisplayer;
    private WallDisplayer wallDisplayer;

    public GameDisplayer(Room model) {
        super(model);
        playerDisplayer = new PlayerDisplayer(getModel().getPlayer());
    }

    @Override
    // hard coded for testing
    public void draw(GUI gui) throws IOException {
        gui.clear();
        playerDisplayer.draw(gui);
        for(Wall wall : getModel().getWalls()){
        wallDisplayer = new WallDisplayer(wall);
           wallDisplayer.draw(gui);
        }
        gui.refresh();
    }
}
