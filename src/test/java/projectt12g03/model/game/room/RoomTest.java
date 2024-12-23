package projectt12g03.model.game.room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.movable.hostile.Monster;
import projectt12g03.model.game.elements.movable.player.Player;
import projectt12g03.model.game.elements.stationary.*;
import projectt12g03.model.game.inventory.Inventory;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomTest {
    private int roomCode;
    private Player player;
    private Inventory inventory;
    private Chest chest;
    private Potion potion;
    private Key key;
    private Floor floor;
    private List<Floor> floors;
    private Wall wall;
    private List<Wall> walls;
    private Door door;
    private List<Door> doors;
    private Monster monster;
    private List<Monster> monsters;
    private Laser laser;
    private List<Laser> lasers;
    private Room room;

    @BeforeEach
    void setup() {
        player = mock(Player.class);
        inventory = mock(Inventory.class);
        chest = mock(Chest.class);
        potion = mock(Potion.class);
        key = mock(Key.class);
        floor = mock(Floor.class);
        floors = Arrays.asList(floor);
        wall = mock(Wall.class);
        walls = Arrays.asList(wall);
        door = mock(Door.class);
        doors = Arrays.asList(door);
        monster = mock(Monster.class);
        monsters = Arrays.asList(monster);
        laser = mock(Laser.class);
        lasers = Arrays.asList(laser);

        roomCode = 1;

        room = spy(new Room(roomCode));

        room.setPlayer(player);
        room.setChest(chest);
        room.setFloors(floors);
        room.setWalls(walls);
        room.setDoors(doors);
        room.setMonsters(monsters);
        room.setLasers(lasers);
        room.setKey(key);

        when(player.getHitbox()).thenReturn(new Rectangle(0,0,32,32));
        when(monster.getHitbox()).thenReturn(new Rectangle(0,0,32,32));
        when(wall.getHitbox()).thenReturn(new Rectangle(0,0,16,16));
        when(door.getHitbox()).thenReturn(new Rectangle(0,0,16,16));
        when(laser.getHitbox()).thenReturn(new Rectangle(0,0,16,16));
        when(laser.getLaserBeamHitbox()).thenReturn(new Rectangle(1,0,1,16));
        when(key.getHitbox()).thenReturn(new Rectangle(0,0,16,16));
        when(potion.getHitbox()).thenReturn(new Rectangle(0,0,16,16));
        when(chest.getHitbox()).thenReturn(new Rectangle(0,0,16,16));

        when(player.getPosition()).thenReturn(new Vector(0,0));
        when(monster.getPosition()).thenReturn(new Vector(32,32));
        when(wall.getPosition()).thenReturn(new Vector(160,160));
        when(door.getPosition()).thenReturn(new Vector(100,100));
        when(laser.getPosition()).thenReturn(new Vector(304,64));
        when(key.getPosition()).thenReturn(new Vector(200,200));
        when(potion.getPosition()).thenReturn(new Vector(0,64));
        when(chest.getPosition()).thenReturn(new Vector(304,112));
    }

    @Test
    void playerDoesntCollideTest() {
        assertFalse(room.checkCollisionsForPlayer(player.getPosition()));
    }

    @Test
    void playerCollidesWithWallTest() {
        room.checkCollisionsForPlayer(new Vector(160, 160));

        verify(room).checkCollisionWithWall(player, new Vector(160,160));
        assertTrue(room.checkCollisionWithWall(player, new Vector(160,160)));
    }

    @Test
    void playerCollidesWithLaserWallTest() {
        room.checkCollisionsForPlayer(new Vector(304, 64));

        verify(room).checkCollisionWithLaserWall(player, new Vector(304,64));
        assertTrue(room.checkCollisionWithLaserWall(player, new Vector(304,64)));
    }

    @Test
    void monsterHitsPlayerTest() {
        room.checkCollisionsForPlayer(new Vector(32, 32));

        verify(room).checkDamageToPlayer(new Vector(32,32));
        assertTrue(room.checkDamageToPlayer(new Vector(32,32)));
    }

    @Test
    void playerIsAtTransitionTest() {
        room.checkCollisionsForPlayer(new Vector(100, 100));

        verify(room).isAtTransition(player, new Vector(100,100));
        assertTrue(room.isAtTransition(player, new Vector(100,100)));
    }

    @Test
    void playerCollidesWithKey() {
        doReturn(true).when(room).checkCollisionWithKey(player, new Vector(200, 200));
        doNothing().when(room).setKey(null);
        doNothing().when(chest).foundKey();

        assertFalse(room.checkCollisionsForPlayer(new Vector(200, 200)));

        verify(room).checkCollisionWithKey(player, new Vector(200, 200));
        verify(chest).foundKey();
    }

    @Test
    void chestIsUnlockableAndNotOpened() {
        doReturn(true).when(room).checkCollisionWithChest(player, new Vector(304, 112));
        when(chest.isUnlockable()).thenReturn(true);
        when(chest.isOpened()).thenReturn(false);
        doNothing().when(chest).open();

        boolean result = room.checkCollisionsForPlayer(new Vector(304, 112));

        assertFalse(result);
        verify(chest).open();
    }

    @Test
    void chestIsLockedAndOpened() {
        doReturn(true).when(room).checkCollisionWithChest(player, new Vector(304, 112));
        when(chest.isUnlockable()).thenReturn(false);
        when(chest.isOpened()).thenReturn(true);

        room.checkCollisionsForPlayer(new Vector(304, 112));

        verify(chest, never()).open();
    }

    @Test
    void chestIsAlreadyOpened() {
        doReturn(true).when(room).checkCollisionWithChest(player, new Vector(304, 112));
        when(chest.isUnlockable()).thenReturn(true);
        when(chest.isOpened()).thenReturn(true);

        boolean result = room.checkCollisionsForPlayer(new Vector(304, 112));

        assertFalse(result);
        verify(chest, never()).open();
    }

    @Test
    void chestIsOpenedAndPlayerTakesPotion() {
        doReturn(true).when(room).checkCollisionWithChest(player, new Vector(304, 112));
        when(chest.isUnlockable()).thenReturn(false);
        when(chest.isOpened()).thenReturn(true);
        when(chest.getPotion()).thenReturn(potion);

        when(potion.getImmunity()).thenReturn(0);
        when(player.getInventory()).thenReturn(inventory);
        when(player.getInventory().isNotFull()).thenReturn(true);

        boolean result = room.checkCollisionsForPlayer(new Vector(304, 112));

        assertFalse(result);
        verify(inventory).addPotion(potion);
        verify(chest).setPotion(null);
    }

    @Test
    void chestIsOpenedButInventoryFull() {
        doReturn(true).when(room).checkCollisionWithChest(player, new Vector(304, 112));
        when(chest.isUnlockable()).thenReturn(false);
        when(chest.isOpened()).thenReturn(true);
        when(chest.getPotion()).thenReturn(potion);

        when(potion.getImmunity()).thenReturn(0);
        when(player.getInventory()).thenReturn(inventory);
        when(player.getInventory().isNotFull()).thenReturn(false);

        boolean result = room.checkCollisionsForPlayer(new Vector(304, 112));

        assertFalse(result);
        verify(player.getInventory(), never()).addPotion(potion);
        verify(chest, never()).setPotion(null);
    }

    @Test
    void chestIsOpenedButPotionNull() {
        doReturn(true).when(room).checkCollisionWithChest(player, new Vector(304, 112));
        when(chest.isUnlockable()).thenReturn(false);
        when(chest.isOpened()).thenReturn(true);
        when(chest.getPotion()).thenReturn(null);

        when(potion.getImmunity()).thenReturn(0);
        when(player.getInventory()).thenReturn(inventory);
        when(player.getInventory().isNotFull()).thenReturn(true);

        boolean result = room.checkCollisionsForPlayer(new Vector(304, 112));

        assertFalse(result);
        verify(player.getInventory(), never()).addPotion(potion);
        verify(chest, never()).setPotion(null);
    }

    @Test
    void chestIsOpenedButImmunity() {
        doReturn(true).when(room).checkCollisionWithChest(player, new Vector(304, 112));
        when(chest.isUnlockable()).thenReturn(false);
        when(chest.isOpened()).thenReturn(true);
        when(chest.getPotion()).thenReturn(potion);

        when(potion.getImmunity()).thenReturn(2);
        when(player.getInventory()).thenReturn(inventory);
        when(player.getInventory().isNotFull()).thenReturn(false);

        boolean result = room.checkCollisionsForPlayer(new Vector(304, 112));

        assertFalse(result);
        verify(player.getInventory(), never()).addPotion(potion);
        verify(chest, never()).setPotion(null);
    }

    @Test
    void chestIsNeitherUnlockableNorOpened() {
        doReturn(true).when(room).checkCollisionWithChest(player, new Vector(304, 112));
        when(chest.isUnlockable()).thenReturn(false);
        when(chest.isOpened()).thenReturn(false);

        boolean result = room.checkCollisionsForPlayer(new Vector(304, 112));

        assertTrue(result);
        verify(chest, never()).open();
    }

    @Test
    void monsterDoesntCollideTest() {
        assertFalse(room.checkCollisionsForMonster(monster, monster.getPosition()));
    }

    @Test
    void monsterCollidesWithPlayerTest() {
        assertTrue(room.checkCollisionsForMonster(monster, player.getPosition()));
        assertTrue(room.checkCollisionWithPlayer(monster, player.getPosition()));
    }

    @Test
    void monsterCollidesWithKeyTest() {
        assertTrue(room.checkCollisionsForMonster(monster, key.getPosition()));
        assertTrue(room.checkCollisionWithKey(monster, key.getPosition()));
    }

    @Test
    void monsterCollidesWithKeyButNullTest() {
        room.setKey(null);
        assertFalse(room.checkCollisionsForMonster(monster, key.getPosition()));
        assertFalse(room.checkCollisionWithKey(monster, key.getPosition()));
    }

    @Test
    void monsterCollidesWithPWallTest() {
        assertTrue(room.checkCollisionsForMonster(monster, wall.getPosition()));
        assertTrue(room.checkCollisionWithWall(monster, wall.getPosition()));
    }

    @Test
    void monsterCollidesWithChestTest() {
        assertTrue(room.checkCollisionsForMonster(monster, chest.getPosition()));
        assertTrue(room.checkCollisionWithChest(monster, chest.getPosition()));
    }

    @Test
    void monsterCollidesWithLaserWallTest() {
        assertTrue(room.checkCollisionsForMonster(monster, laser.getPosition()));
        assertTrue(room.checkCollisionWithLaserWall(monster, laser.getPosition()));
    }

    @Test
    void monsterCollidesWithMonsterTest() {
        Monster otherMonster = mock(Monster.class);
        when(otherMonster.getPosition()).thenReturn(new Vector(64,64));
        when(otherMonster.getHitbox()).thenReturn(new Rectangle(0,0,32,32));
        room.setMonsters(Arrays.asList(monster, otherMonster));

        assertTrue(room.checkCollisionsForMonster(monster, new Vector(64,64)));
        assertTrue(room.checkCollisionWithMonster(monster, new Vector(64,64)));
    }

    @Test
    void monsterIsAtTransition() {
        assertTrue(room.checkCollisionsForMonster(monster, door.getPosition()));
        assertTrue(room.isAtTransition(monster, door.getPosition()));
    }

    @Test
    void monsterDoesntDamagePlayerTest() {
        when(player.getImmunity()).thenReturn(2);

        boolean result = room.checkDamageToPlayer(new Vector(32,32));

        verify(player, never()).reduceHealth();
        verify(player, never()).setImmunity();

        assertTrue(room.checkCollisionWithMonster(player, new Vector(32,32)));
        assertTrue(result);
    }

    @Test
    void monsterDamagesPlayerTest() {
        when(player.getImmunity()).thenReturn(0);

        boolean result = room.checkDamageToPlayer(new Vector(32,32));

        verify(player).reduceHealth();
        verify(player).setImmunity();

        assertTrue(room.checkCollisionWithMonster(player, new Vector(32,32)));
        assertTrue(result);
    }

    @Test
    void monsterDamagesPlayerWalkingIntoHimTest() {
        when(player.getImmunity()).thenReturn(2);

        boolean result = room.checkCollisionWithPlayer(monster, new Vector(0,0));

        verify(player, never()).reduceHealth();
        verify(player, never()).setImmunity();

        assertTrue(room.checkCollisionForElements(monster, player, new Vector(0,0)));
        assertTrue(result);
    }

    @Test
    void playerKillsMonsterTest() {
        when(player.getSpear()).thenReturn(new Rectangle(0, 0, 16, 16));
        when(player.getPosition()).thenReturn(new Vector(32, 32));
        when(monster.getHealth()).thenReturn(0);

        List<Monster> monsters = new ArrayList<>();
        monsters.add(monster);
        room.setMonsters(monsters);

        room.checkDamageToMonster();

        assertTrue(room.checkCollisionForDamage(monster, player.getSpear(), player.getPosition()));
        verify(monster).reduceHealth();
        assertFalse(monsters.contains(monster));
    }

    @Test
    void playerDamagesMonsterTest() {
        when(player.getSpear()).thenReturn(new Rectangle(0, 0, 16, 16));
        when(player.getPosition()).thenReturn(new Vector(32, 32));
        when(monster.getHealth()).thenReturn(2);

        List<Monster> monsters = new ArrayList<>();
        monsters.add(monster);
        room.setMonsters(monsters);

        room.checkDamageToMonster();

        assertTrue(room.checkCollisionForDamage(monster, player.getSpear(), player.getPosition()));
        verify(monster).reduceHealth();
        assertTrue(monsters.contains(monster));
    }

    @Test
    void playerMissesMonsterTest() {
        when(player.getSpear()).thenReturn(new Rectangle(0, 0, 16, 16));
        when(player.getPosition()).thenReturn(new Vector(0, 0));
        when(monster.getHealth()).thenReturn(0);

        List<Monster> monsters = new ArrayList<>();
        monsters.add(monster);
        room.setMonsters(monsters);

        room.checkDamageToMonster();

        assertFalse(room.checkCollisionForDamage(monster, player.getSpear(), player.getPosition()));
        verify(monster, never()).reduceHealth();
        assertTrue(monsters.contains(monster));
    }

    @Test
    void laserBeamHitsPlayer() {
        int displacement = 0;

        when(player.getImmunity()).thenReturn(2);
        doNothing().when(player).reduceHealth();
        doNothing().when(player).setImmunity();

        Rectangle laserBeamHitbox = new Rectangle(0, 0, 10, 10);
        when(laser.getLaserBeamHitbox()).thenReturn(laserBeamHitbox);
        when(laser.getPosition()).thenReturn(new Vector(0, 0));

        Vector laserBeamPosition = new Vector(displacement, laser.getPosition().getY());
        doReturn(true).when(room).checkCollisionForDamage(player, laserBeamHitbox, laserBeamPosition);

        boolean result = room.checkCollisionsForLaserBeam(laser, displacement);

        assertTrue(result);
        verify(player, never()).reduceHealth();
        verify(player, never()).setImmunity();
        assertTrue(room.checkCollisionForDamage(player, laserBeamHitbox, laserBeamPosition));
    }

    @Test
    void laserBeamDamagesPlayer() {
        int displacement = 0;

        when(player.getImmunity()).thenReturn(0);
        doNothing().when(player).reduceHealth();
        doNothing().when(player).setImmunity();

        Rectangle laserBeamHitbox = new Rectangle(0, 0, 10, 10);
        when(laser.getLaserBeamHitbox()).thenReturn(laserBeamHitbox);
        when(laser.getPosition()).thenReturn(new Vector(0, 0));

        Vector laserBeamPosition = new Vector(displacement, laser.getPosition().getY());
        doReturn(true).when(room).checkCollisionForDamage(player, laserBeamHitbox, laserBeamPosition);

        boolean result = room.checkCollisionsForLaserBeam(laser, displacement);

        assertTrue(result);
        verify(player).reduceHealth();
        verify(player).setImmunity();
        assertTrue(room.checkCollisionForDamage(player, laserBeamHitbox, laserBeamPosition));
    }

    @Test
    void laserBeamHitsMonster() {
        int displacement = 0;

        Rectangle laserBeamHitbox = new Rectangle(0, 0, 10, 10);
        when(laser.getLaserBeamHitbox()).thenReturn(laserBeamHitbox);
        when(laser.getPosition()).thenReturn(new Vector(32, 32));

        Vector laserBeamPosition = new Vector(displacement, laser.getPosition().getY());
        doReturn(true).when(room).checkCollisionForDamage(monster, laserBeamHitbox, laserBeamPosition);

        boolean result = room.checkCollisionsForLaserBeam(laser, displacement);

        assertTrue(result);
        assertTrue(room.checkCollisionForDamage(monster, laserBeamHitbox, laserBeamPosition));
    }

    @Test
    void laserBeamHitsWall() {
        int displacement = 0;

        Rectangle laserBeamHitbox = new Rectangle(0, 0, 10, 10);
        when(laser.getLaserBeamHitbox()).thenReturn(laserBeamHitbox);
        when(laser.getPosition()).thenReturn(new Vector(160, 160));

        Vector laserBeamPosition = new Vector(displacement, laser.getPosition().getY());
        doReturn(true).when(room).checkCollisionForDamage(wall, laserBeamHitbox, laserBeamPosition);

        boolean result = room.checkCollisionsForLaserBeam(laser, displacement);

        assertTrue(result);
        assertTrue(room.checkCollisionForDamage(wall, laserBeamHitbox, laserBeamPosition));
    }

    @Test
    void laserBeamHitsNothing() {
        int displacement = 0;

        Rectangle laserBeamHitbox = new Rectangle(0, 0, 10, 10);
        when(laser.getLaserBeamHitbox()).thenReturn(laserBeamHitbox);
        when(laser.getPosition()).thenReturn(new Vector(250, 250));

        Vector laserBeamPosition = new Vector(displacement, laser.getPosition().getY());

        boolean result = room.checkCollisionsForLaserBeam(laser, displacement);

        assertFalse(result);
        assertFalse(room.checkCollisionForDamage(player, laserBeamHitbox, laserBeamPosition));
        assertFalse(room.checkCollisionForDamage(monster, laserBeamHitbox, laserBeamPosition));
        assertFalse(room.checkCollisionForDamage(wall, laserBeamHitbox, laserBeamPosition));
    }

    @Test
    void getDoorNumber() {
        Vector mockedPosition = mock(Vector.class);
        when(door.getPosition()).thenReturn(mockedPosition);

        doReturn(true).when(room).checkCollisionForElements(player, door, mockedPosition);

        when(door.getNumber()).thenReturn(1);

        int result = room.getDoorNumber(mockedPosition);

        verify(door).getNumber();
        verify(room).checkCollisionForElements(player, door, mockedPosition);
        assertEquals(1, result);
    }

    @Test
    void dontGetDoorNumber() {
        Vector mockedPosition = mock(Vector.class);
        when(door.getPosition()).thenReturn(mockedPosition);

        doReturn(false).when(room).checkCollisionForElements(player, door, mockedPosition);

        when(door.getNumber()).thenReturn(1);

        int result = room.getDoorNumber(mockedPosition);

        verify(door, never()).getNumber();
        verify(room).checkCollisionForElements(player, door, mockedPosition);
        assertEquals(-1, result);
    }

    @Test
    void testLaserEncountersObstacle() {
        when(laser.getPosition()).thenReturn(new Vector(10, 0));
        when(laser.isPointingLeft()).thenReturn(false);

        doAnswer(invocation -> {
            int pos = invocation.getArgument(1);
            return pos >= 18;
        }).when(room).checkCollisionsForLaserBeam(eq(laser), anyInt());

        int range = room.getLaserRange(laser);

        assertEquals(1, range);
    }

    @Test
    void testLaserBoundaryConditions() {
        when(laser.getPosition()).thenReturn(new Vector(2, 0));
        when(laser.isPointingLeft()).thenReturn(true);

        doAnswer(invocation -> {
            int pos = invocation.getArgument(1);
            return pos <= 0;
        }).when(room).checkCollisionsForLaserBeam(eq(laser), anyInt());

        int range = room.getLaserRange(laser);

        assertEquals(0, range);
    }

    @Test
    void testGetFloors() {
        List<Floor> floorsList = room.getFloors();
        assertNotNull(floorsList);
        assertEquals(floorsList, floors);
    }

    @Test
    void testGetWalls() {
        List<Wall> wallsList = room.getWalls();
        assertNotNull(wallsList);
        assertEquals(wallsList, walls);
    }

    @Test
    void testGetPlayer() {
        Player getPlayer = room.getPlayer();
        assertNotNull(getPlayer);
        assertEquals(getPlayer, player);
    }

    @Test
    void testGetChest() {
        Chest chest = room.getChest();
        assertNotNull(chest);
    }

    @Test
    void testGetKey() {
        Key getKey = room.getKey();
        assertNotNull(getKey);
        assertEquals(getKey, key);
    }

    @Test
    void testGetMonsters() {
        List<Monster> monstersList = room.getMonsters();
        assertNotNull(monstersList);
        assertEquals(monstersList, monstersList);
    }

    @Test
    void testGetLasers() {
        List<Laser> lasersList = room.getLasers();
        assertNotNull(lasersList);
        assertEquals(lasersList, lasers);
    }

    @Test
    void testGetRoamCode() {
        int roomcode = room.getRoomCode();
        assertEquals(roomcode, roomCode);
    }
}