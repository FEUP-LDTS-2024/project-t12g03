package projectt12g03.view.gameDisplayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.gui.GUI;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.movable.hostile.Monster;
import projectt12g03.model.game.elements.movable.player.Player;
import projectt12g03.model.game.elements.stationary.Chest;
import projectt12g03.model.game.elements.stationary.Floor;
import projectt12g03.model.game.elements.stationary.Key;
import projectt12g03.model.game.elements.stationary.Laser;
import projectt12g03.model.game.elements.stationary.Wall;
import projectt12g03.model.game.inventory.Inventory;
import projectt12g03.model.game.room.Room;
import projectt12g03.model.game.room.RoomManager;
import projectt12g03.view.gameDisplayer.elements.movable.MonsterDisplayer;
import projectt12g03.view.gameDisplayer.elements.movable.PlayerDisplayer;
import projectt12g03.view.gameDisplayer.elements.stationary.*;
import projectt12g03.view.gameDisplayer.hud.HudDisplayer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class GameDisplayerTest {

    private GameDisplayer gameDisplayer;
    private RoomManager roomManager;
    private Room room;
    private GUI gui;
    private HudDisplayer hudDisplayer;
    private PlayerDisplayer playerDisplayer;
    private LaserDisplayer laserDisplayer;
    private MonsterDisplayer monsterDisplayer;
    private FloorDisplayer floorDisplayer;
    private WallDisplayer wallDisplayer;
    private ChestDisplayer chestDisplayer;
    private KeyDisplayer keyDisplayer;

    private GameDisplayerHub gameDisplayerHub;

    private Player player;
    private Chest chest;
    private Key key;
    private Monster monster;
    private List<Monster> monsters;
    private Wall wall;
    private List<Wall> walls;
    private Floor floor;
    private List<Floor> floors;
    private Laser laser;
    private List<Laser> lasers;
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        roomManager = mock(RoomManager.class);
        room = mock(Room.class);
        gui = mock(GUI.class);
        gameDisplayerHub = mock(GameDisplayerHub.class);

        player = mock(Player.class);
        chest = mock(Chest.class);
        key = mock(Key.class);
        monster = mock(Monster.class);
        monsters = Arrays.asList(monster);
        wall = mock(Wall.class);
        walls = Arrays.asList(wall);
        floor = mock(Floor.class);
        floors = Arrays.asList(floor);
        laser = mock(Laser.class);
        lasers = Arrays.asList(laser);
        inventory = mock(Inventory.class);

        //elements dependencies
        when(floor.getType()).thenReturn('d');
        when(player.getState()).thenReturn("IDLE");
        when(player.getDirection()).thenReturn("UP");
        when(monster.getDirection()).thenReturn("UP");
        when(monster.getState()).thenReturn("IDLE");

        //getters dependencies
        when(roomManager.getRoom()).thenReturn(room);
        when(roomManager.getPlayer()).thenReturn(player);
        when(room.getChest()).thenReturn(chest);
        when(room.getKey()).thenReturn(key);
        when(room.getWalls()).thenReturn(walls);
        when(room.getFloors()).thenReturn(floors);
        when(room.getLasers()).thenReturn(lasers);
        when(room.getMonsters()).thenReturn(monsters);
        when(player.getInventory()).thenReturn(inventory);

        //positions dependencies
        when(player.getPosition()).thenReturn(new Vector(0,0));
        when(chest.getPosition()).thenReturn(new Vector(0,0));
        when(key.getPosition()).thenReturn(new Vector(0,0));
        when(wall.getPosition()).thenReturn(new Vector(0,0));
        when(floor.getPosition()).thenReturn(new Vector(0,0));
        when(laser.getPosition()).thenReturn(new Vector(0,0));
        when(monster.getPosition()).thenReturn(new Vector(0,0));

        hudDisplayer = mock(HudDisplayer.class);
        playerDisplayer = mock(PlayerDisplayer.class);
        laserDisplayer = mock(LaserDisplayer.class);
        wallDisplayer = mock(WallDisplayer.class);
        monsterDisplayer = mock(MonsterDisplayer.class);
        chestDisplayer = mock(ChestDisplayer.class);
        keyDisplayer = mock(KeyDisplayer.class);
        floorDisplayer = mock(FloorDisplayer.class);

        when(gameDisplayerHub.getHudDisplayer()).thenReturn(hudDisplayer);
        when(gameDisplayerHub.getPlayerDisplayer()).thenReturn(playerDisplayer);
        when(gameDisplayerHub.getLaserDisplayer()).thenReturn(laserDisplayer);
        when(gameDisplayerHub.getWallDisplayer()).thenReturn(wallDisplayer);
        when(gameDisplayerHub.getMonsterDisplayer()).thenReturn(monsterDisplayer);
        when(gameDisplayerHub.getChestDisplayer()).thenReturn(chestDisplayer);
        when(gameDisplayerHub.getKeyDisplayer()).thenReturn(keyDisplayer);
        when(gameDisplayerHub.getFloorDisplayer()).thenReturn(floorDisplayer);
    }

    @Test
    void testDraw() throws IOException {
        gameDisplayer = new GameDisplayer(roomManager, gameDisplayerHub);

        gameDisplayer.draw(gui, 0);

        verify(gui, times(1)).clear();
        verify(gui, times(1)).refresh();

        verify(hudDisplayer, times(1)).draw(player, gui, 0);
        verify(playerDisplayer, times(1)).draw(player, gui, 0);
        verify(laserDisplayer, times(1)).draw(laser, gui, 0);
        verify(wallDisplayer, times(1)).draw(wall, gui, 0);
        verify(monsterDisplayer, times(1)).draw(monster, gui, 0);
        verify(chestDisplayer, times(1)).draw(chest, gui, 0);
        verify(keyDisplayer, times(1)).draw(key, gui, 0);
        verify(floorDisplayer, times(1)).draw(floor, gui, 0);
    }
}