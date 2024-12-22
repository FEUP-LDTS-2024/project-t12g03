package projectt12g03.view.gameDisplayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.view.gameDisplayer.elements.movable.MonsterDisplayer;
import projectt12g03.view.gameDisplayer.elements.movable.PlayerDisplayer;
import projectt12g03.view.gameDisplayer.elements.stationary.*;
import projectt12g03.view.gameDisplayer.hud.HudDisplayer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GameDisplayerHubTest {

    private GameDisplayerHub gameDisplayerHub;


    @BeforeEach
    void setup() throws IOException {
        gameDisplayerHub = new GameDisplayerHub();
    }

    @Test
    void testGetPlayerDisplayer() {
        PlayerDisplayer playerDisplayer = gameDisplayerHub.getPlayerDisplayer();
        assertNotNull(playerDisplayer);
        assertEquals(PlayerDisplayer.class, playerDisplayer.getClass());
    }

    @Test
    void testGetWallDisplayer() {
        WallDisplayer wallDisplayer = gameDisplayerHub.getWallDisplayer();
        assertNotNull(wallDisplayer);
        assertEquals(WallDisplayer.class, wallDisplayer.getClass());
    }

    @Test
    void testGetMonsterDisplayer() {
        MonsterDisplayer monsterDisplayer = gameDisplayerHub.getMonsterDisplayer();
        assertNotNull(monsterDisplayer);
        assertEquals(MonsterDisplayer.class, monsterDisplayer.getClass());
    }

    @Test
    void testGetLaserDisplayer() {
        LaserDisplayer laserDisplayer = gameDisplayerHub.getLaserDisplayer();
        assertNotNull(laserDisplayer);
        assertEquals(LaserDisplayer.class, laserDisplayer.getClass());
    }

    @Test
    void testGetChestDisplayer() {
        ChestDisplayer chestDisplayer = gameDisplayerHub.getChestDisplayer();
        assertNotNull(chestDisplayer);
        assertEquals(ChestDisplayer.class, chestDisplayer.getClass());
    }

    @Test
    void testGetKeyDisplayer() {
        KeyDisplayer keyDisplayer = gameDisplayerHub.getKeyDisplayer();
        assertNotNull(keyDisplayer);
        assertEquals(KeyDisplayer.class, keyDisplayer.getClass());
    }

    @Test
    void testGetFloorDisplayer() {
        FloorDisplayer floorDisplayer = gameDisplayerHub.getFloorDisplayer();
        assertNotNull(floorDisplayer);
        assertEquals(FloorDisplayer.class, floorDisplayer.getClass());
    }

    @Test
    void testGetHudDisplayer() {
        HudDisplayer hudDisplayer = gameDisplayerHub.getHudDisplayer();
        assertNotNull(hudDisplayer);
        assertEquals(HudDisplayer.class, hudDisplayer.getClass());
    }
}