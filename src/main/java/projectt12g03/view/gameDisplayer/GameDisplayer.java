package projectt12g03.view.gameDisplayer;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.game.elements.Element;
import projectt12g03.model.game.elements.stationary.Door;
import projectt12g03.model.game.room.RoomManager;
import projectt12g03.view.ScreenDisplayer;
import projectt12g03.view.gameDisplayer.elements.ElementDisplayer;
import projectt12g03.view.gameDisplayer.elements.movable.MonsterDisplayer;
import projectt12g03.view.gameDisplayer.elements.movable.PlayerDisplayer;
import projectt12g03.view.gameDisplayer.elements.stationary.*;
import projectt12g03.view.gameDisplayer.hud.HudDisplayer;


import java.io.IOException;
import java.util.List;

public class GameDisplayer extends ScreenDisplayer<RoomManager> {
    private final PlayerDisplayer playerDisplayer;
    private final WallDisplayer wallDisplayer;
    private final MonsterDisplayer monsterDisplayer;
    private final LaserDisplayer laserDisplayer;
    private final ChestDisplayer chestDisplayer;
    private final KeyDisplayer keyDisplayer;
    private final FloorDisplayer floorDisplayer;
    private final HudDisplayer hudDisplayer;

    public GameDisplayer(RoomManager model) throws IOException {
        super(model);

        playerDisplayer = new PlayerDisplayer();
        wallDisplayer = new WallDisplayer();
        monsterDisplayer = new MonsterDisplayer();
        laserDisplayer = new LaserDisplayer();
        chestDisplayer = new ChestDisplayer();
        keyDisplayer = new KeyDisplayer();
        floorDisplayer = new FloorDisplayer();
        hudDisplayer = new HudDisplayer();
    }

    @Override
    public void draw(GUI gui, long frameCount) throws IOException {
        gui.clear();

        drawElements(gui, getModel().getRoom().getFloors(), floorDisplayer, frameCount);
        drawElements(gui, getModel().getRoom().getWalls(), wallDisplayer, frameCount);
        drawElement(gui, getModel().getRoom().getChest(), chestDisplayer, frameCount);
        drawElement(gui, getModel().getRoom().getKey(), keyDisplayer, frameCount);
        drawElements(gui, getModel().getRoom().getMonsters(), monsterDisplayer, frameCount);
        drawElement(gui, getModel().getPlayer(), playerDisplayer, frameCount);
        drawElements(gui, getModel().getRoom().getLasers(), laserDisplayer, frameCount);

        hudDisplayer.draw(getModel().getPlayer(), gui, frameCount);

        gui.refresh();
    }

    private <T extends Element> void drawElement(GUI gui, T element, ElementDisplayer<T> displayer, long frameCount) {
        displayer.draw(element, gui, frameCount);
    }

    private <T extends Element> void drawElements(GUI gui, List<T> elements, ElementDisplayer<T> viewer, long frameCount) {
        for (T element: elements)
            drawElement(gui, element, viewer, frameCount);
    }
}
