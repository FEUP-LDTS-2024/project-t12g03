package projectt12g03.view.game;

import projectt12g03.gui.GUI;
import projectt12g03.model.game.room.RoomManager;
import projectt12g03.view.ScreenDisplayer;
import projectt12g03.view.game.elements.*;

import java.io.IOException;

public class GameDisplayer extends ScreenDisplayer<RoomManager> {
    private PlayerDisplayer playerDisplayer;
    private WallDisplayer wallDisplayer;
    private MonsterDisplayer monsterDisplayer;
    private ChestDisplayer chestDisplayer;
    private KeyDisplayer keyDisplayer;

    public GameDisplayer(RoomManager model) {
        super(model);
        playerDisplayer = new PlayerDisplayer();
        wallDisplayer = new WallDisplayer();
        monsterDisplayer = new MonsterDisplayer();
        chestDisplayer = new ChestDisplayer();
        keyDisplayer = new KeyDisplayer();
    }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.clear();
        playerDisplayer.draw(getModel().getPlayer(), gui);
        wallDisplayer.draw(getModel().getRoom().getWalls(), gui);
        monsterDisplayer.draw(getModel().getRoom().getMonsters(), gui);
        chestDisplayer.draw(getModel().getRoom().getChest(), gui);
        keyDisplayer.draw(getModel().getRoom().getKey(), gui);
        gui.refresh();
    }
}
