package projectt12g03.view;

import projectt12g03.gui.LanternaGui;
import projectt12g03.model.elements.Player;
import projectt12g03.model.elements.Wall;
import projectt12g03.model.room.Room;

import java.io.IOException;

public class GameDisplayer extends ScreenDisplayer<Room> {
    private  PlayerDisplayer playerview;
    private  WallDisplayer wallview;

    public GameDisplayer(Room model) {
        super(model);
        playerview = new PlayerDisplayer(getModel().getPlayer());
    }

    @Override
    // hard coded for testing
    public void draw(LanternaGui gui) throws IOException {
        gui.clear();
        playerview.draw(gui);
        for(Wall wall : getModel().getWalls()){
            wallview = new WallDisplayer(wall);
            wallview.draw(gui);
        }
        gui.refresh();
    }
}
