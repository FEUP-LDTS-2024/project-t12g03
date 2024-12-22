package projectt12g03.model.game.elements.movable.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.stationary.Potion;
import projectt12g03.model.game.room.Room;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerModelTest {

    private Player player;
    private Room mockRoom;

    @BeforeEach
    void setUp() {
        mockRoom = mock(Room.class);

        player = new Player(0, 0, mockRoom);
    }

    @Test
    void testInitialState() {
        assertEquals(3, player.getVelocity());
        assertEquals(4, player.getHealth());
        assertEquals(800, player.getEnergy());
        assertNotNull(player.getInventory());
        assertEquals("DOWN", player.getDirection());
        assertEquals("IDLE", player.getState());
    }

    @Test
    void testWalk() {
        player.walk();
        assertFalse(player.isSprinting());
        assertEquals(3, player.getVelocity());
        assertEquals("WALKING", player.getState());
    }

    @Test
    void testSprint() {
        player.sprint();
        assertTrue(player.isSprinting());
        assertEquals(5, player.getVelocity());
        assertEquals("RUNNING", player.getState());

        player.sprint();
        assertFalse(player.isSprinting());
        assertEquals(3, player.getVelocity());
        assertEquals("WALKING", player.getState());
    }

    @Test
    void testSprintWhenEnergyLow() {
        player.setEnergy(8);   // Reduce energy to near sprint threshold with helper
        player.reduceEnergy();  //Reduce energy to below sprint threshold
        player.sprint();
        assertFalse(player.isSprinting());
        assertEquals(3, player.getVelocity());
        assertEquals("WALKING", player.getState());
    }

    @Test
    void testHealLowEnergy() {
        player.reduceHealth();
        player.reduceEnergy();
        player.heal();
        assertEquals(794, player.getEnergy());
        assertEquals(3, player.getHealth());
    }

    @Test
    void testHealFullEnergy(){
        player.reduceHealth();
        player.heal();
        assertEquals(4, player.getHealth());
        assertEquals(0, player.getEnergy());
    }

    @Test
    void testHealFullHealth(){
        player.setHealth(4);
        player.heal();
        assertEquals(4, player.getHealth());
        assertEquals(800, player.getEnergy());
    }

    @Test
    void testConsumePotion() {
        Potion mockPotion = mock(Potion.class);
        player.consume(mockPotion);
        verify(mockPotion).applyEffect(player);
    }

    @Test
    void testUpdatePotionDurationPositive() {
        Potion mockPotion = mock(Potion.class);
        player.consume(mockPotion);
        player.setEffectDuration(1);

        player.updatePotionDuration();
        assertEquals(0, player.getEffectDuration());
    }

    @Test
    void testUpdatePotionDurationZero(){
        Potion mockPotion = mock(Potion.class);
        player.consume(mockPotion);
        player.setEffectDuration(0);

        player.updatePotionDuration();
        assertEquals(0, player.getEffectDuration());
        verify(mockPotion).removeEffect(player);
    }

    @Test
    void testUpdatePotionDurationNegative() {
        Potion mockPotion = mock(Potion.class);
        player.consume(mockPotion);
        player.setEffectDuration(-1);

        player.updatePotionDuration();
        assertEquals(-1, player.getEffectDuration());
        verify(mockPotion,times(0)).removeEffect(player);
    }

    @Test
    void testUpdatePotionDurationNoActivePotion() {
        Potion mockPotion = mock(Potion.class);
        player.updatePotionDuration();
        assertEquals(0, player.getEffectDuration());
        verify(mockPotion,times(0)).removeEffect(player);
    }

    @Test
    void testIncreaseEnergyWithinLimit() {
        player.setEnergy(100);
        player.increaseEnergy(50);
        assertEquals(150, player.getEnergy());
    }

    @Test
    void testIncreaseEnergyExceedsLimit() {
        player.increaseEnergy(100);
        assertEquals(800, player.getEnergy());
    }

    @Test
    void testAttackNotOnCooldown() {
        player.attack();
        assertEquals(6, player.getIsAttacking());
        assertNotNull(player.getSpear());
        assertEquals(new Rectangle(5, 30, 6, 18), player.getSpear());

        player.reduceIsAttacking();
        assertEquals(5, player.getIsAttacking());

    }

    @Test
    void testAttackOnCooldown() {
        player.setIsAttacking(3);
        player.attack();
        assertEquals(3, player.getIsAttacking());
        assertNull(player.getSpear());
    }


    @Test
    void testCalculateNewPosition() {
        player.setDirection("UP");
        assertEquals(new Vector(0, 223), player.calculateNewPosition());

        player.setDirection("DOWN");
        assertEquals(new Vector(0, -15), player.calculateNewPosition());

        player.setDirection("LEFT");
        assertEquals(new Vector(304, 0), player.calculateNewPosition());

        player.setDirection("RIGHT");
        assertEquals(new Vector(0, 0), player.calculateNewPosition());

    }

    @Test
    void testCalculateNewPositionThrowsException() {
        player.setDirection("INVALID");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            player.calculateNewPosition();
        });

        assertEquals("Direction for Transition not Found", exception.getMessage());
    }

    @Test
    void testCanMove() {
        Vector position = new Vector(10, 10);
        when(mockRoom.checkCollisionsForPlayer(position)).thenReturn(false);
        assertTrue(player.canMove(position));

        when(mockRoom.checkCollisionsForPlayer(position)).thenReturn(true);
        assertFalse(player.canMove(position));
    }

    @Test
    void testSetWeaponHitboxForValidDirections() {
        player.setDirection("UP");
        assertEquals(new Rectangle(5, 2, 6, 18), player.setWeaponHitbox());

        player.setDirection("DOWN");
        assertEquals(new Rectangle(5, 30, 6, 18), player.setWeaponHitbox());

        player.setDirection("LEFT");
        assertEquals(new Rectangle(-14, 20, 18, 6), player.setWeaponHitbox());

        player.setDirection("RIGHT");
        assertEquals(new Rectangle(12, 20, 18, 6), player.setWeaponHitbox());
    }

    @Test
    void testSetWeaponHitboxThrowsExceptionForInvalidDirection() {
        player.setDirection("INVALID"); // Set an invalid direction

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            player.setWeaponHitbox();
        });

        assertEquals("Direction for Spear not Found", exception.getMessage());
    }

    @Test
    void testImmunityOperations(){
        player.setImmunity();
        assertEquals(15,player.getImmunity());

        player.reduceImmunity();
        assertEquals(14,player.getImmunity());

        player.setImmunity(6);
        assertEquals(6,player.getImmunity());
    }

    @Test
    void testSetRoom(){
        Room newRoom = mock(Room.class);
        player.setRoom(newRoom);
        assertSame(newRoom,player.getRoom());
    }

    @Test
    void PlayerIncreaseHealth(){
        player.setHealth(2);
        player.increaseHealth();
        assertEquals(3,player.getHealth());
    }

    @Test
    void testMoveUp() {
        player.setSpeedBoost(2);
        Vector newPosition = player.moveUp();
        assertEquals(0, newPosition.getX());
        assertEquals(-6, newPosition.getY());
    }

    @Test
    void testMoveDown() {
        player.setSpeedBoost(2);
        Vector newPosition = player.moveDown();
        assertEquals(0, newPosition.getX());
        assertEquals(6, newPosition.getY());
    }

    @Test
    void testMoveRight() {
        player.setSpeedBoost(2);
        Vector newPosition = player.moveRight();
        assertEquals(6, newPosition.getX());
        assertEquals(0, newPosition.getY());
    }

    @Test
    void testMoveLeft() {
        player.setSpeedBoost(2);
        Vector newPosition = player.moveLeft();
        assertEquals(-6, newPosition.getX());
        assertEquals(0, newPosition.getY());
    }

}