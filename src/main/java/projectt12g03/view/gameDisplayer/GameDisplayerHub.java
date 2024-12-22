package projectt12g03.view.gameDisplayer;

import projectt12g03.view.gameDisplayer.elements.movable.MonsterDisplayer;
import projectt12g03.view.gameDisplayer.elements.movable.PlayerDisplayer;
import projectt12g03.view.gameDisplayer.elements.stationary.*;
import projectt12g03.view.gameDisplayer.hud.HudDisplayer;
import projectt12g03.view.gameDisplayer.hud.HudDisplayerHub;

import java.io.IOException;

public class GameDisplayerHub {
    private final PlayerDisplayer playerDisplayer;
    private final WallDisplayer wallDisplayer;
    private final MonsterDisplayer monsterDisplayer;
    private final LaserDisplayer laserDisplayer;
    private final ChestDisplayer chestDisplayer;
    private final KeyDisplayer keyDisplayer;
    private final FloorDisplayer floorDisplayer;
    private final HudDisplayer hudDisplayer;

    public GameDisplayerHub() throws IOException {
        playerDisplayer = new PlayerDisplayer();
        wallDisplayer = new WallDisplayer();
        monsterDisplayer = new MonsterDisplayer();
        laserDisplayer = new LaserDisplayer();
        chestDisplayer = new ChestDisplayer();
        keyDisplayer = new KeyDisplayer();
        floorDisplayer = new FloorDisplayer();
        hudDisplayer = new HudDisplayer(new HudDisplayerHub());
    }

    public PlayerDisplayer getPlayerDisplayer() {
        return playerDisplayer;
    }

    public WallDisplayer getWallDisplayer() {
        return wallDisplayer;
    }

    public MonsterDisplayer getMonsterDisplayer() {
        return monsterDisplayer;
    }

    public LaserDisplayer getLaserDisplayer() {
        return laserDisplayer;
    }

    public ChestDisplayer getChestDisplayer() {
        return chestDisplayer;
    }

    public KeyDisplayer getKeyDisplayer() {
        return keyDisplayer;
    }

    public FloorDisplayer getFloorDisplayer() {
        return floorDisplayer;
    }

    public HudDisplayer getHudDisplayer() {
        return hudDisplayer;
    }
}
