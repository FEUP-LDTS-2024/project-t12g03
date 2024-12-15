package projectt12g03.view.gameDisplayer;

import projectt12g03.gui.GUI;
import projectt12g03.model.game.room.RoomManager;
import projectt12g03.view.ScreenDisplayer;
import projectt12g03.view.gameDisplayer.elements.movable.MonsterDisplayer;
import projectt12g03.view.gameDisplayer.elements.movable.PlayerDisplayer;
import projectt12g03.view.gameDisplayer.elements.stationary.ChestDisplayer;
import projectt12g03.view.gameDisplayer.elements.stationary.FloorDisplayer;
import projectt12g03.view.gameDisplayer.elements.stationary.KeyDisplayer;
import projectt12g03.view.gameDisplayer.elements.stationary.WallDisplayer;

import java.io.IOException;

public class GameDisplayer extends ScreenDisplayer<RoomManager> {
    private PlayerDisplayer playerDisplayer;
    private WallDisplayer wallDisplayer;
    private MonsterDisplayer monsterDisplayer;
    private ChestDisplayer chestDisplayer;
    private KeyDisplayer keyDisplayer;
    private FloorDisplayer floorDisplayer;

    public GameDisplayer(RoomManager model) throws IOException {
        super(model);

        playerDisplayer = new PlayerDisplayer();
        wallDisplayer = new WallDisplayer();
        monsterDisplayer = new MonsterDisplayer();
        chestDisplayer = new ChestDisplayer();
        keyDisplayer = new KeyDisplayer();
        floorDisplayer = new FloorDisplayer();
    }

    @Override
    public void draw(GUI gui, long frameCount) throws IOException {
        gui.clear();
        floorDisplayer.draw(getModel().getRoom().getFloors(), gui);
        wallDisplayer.draw(getModel().getRoom().getWalls(), gui);
        chestDisplayer.draw(getModel().getRoom().getChest(), gui);
        keyDisplayer.draw(getModel().getRoom().getKey(), gui);
        monsterDisplayer.draw(getModel().getRoom().getMonsters(), gui, frameCount);
        playerDisplayer.draw(getModel().getPlayer(), gui, frameCount);
        gui.refresh();
    }
}
