package projectt12g03.view;

import projectt12g03.gui.LanternaGui;
import projectt12g03.model.elements.Player;
import projectt12g03.model.room.Room;

import java.io.IOException;

public class GameDisplayer extends ScreenDisplayer<Room> {
    private  PlayerDisplayer playerview;

    public GameDisplayer(Room model) {
        super(model);
        playerview = new PlayerDisplayer(getModel().getPlayer());
    }

    @Override
    public void draw(LanternaGui gui) throws IOException {
        gui.clear();
        playerview.draw(gui);
        gui.refresh();
    }
}
