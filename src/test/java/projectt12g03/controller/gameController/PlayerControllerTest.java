package projectt12g03.controller.gameController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.Game;
import projectt12g03.gui.GUI;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.movable.player.Player;
import projectt12g03.model.game.room.Room;
import projectt12g03.model.game.room.RoomManager;
import projectt12g03.state.menuState.GameOverMenuState;
import projectt12g03.state.menuState.VictoryMenuState;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PlayerControllerTest {
    private Game game;
    private Room room;
    private RoomManager roomManager;
    private Player player;
    private PlayerController playerController;

    @BeforeEach
    public void setup() throws IOException {
        this.room = Mockito.mock(Room.class);
        this.roomManager = Mockito.mock(RoomManager.class);
        this.player = Mockito.mock(Player.class);
        this.game = Mockito.mock(Game.class);
        this.playerController = new PlayerController(roomManager);

        Mockito.when(player.getPosition()).thenReturn(new Vector(5, 5));
        Mockito.when(roomManager.getRoom()).thenReturn(room);
        Mockito.when(roomManager.getPlayer()).thenReturn(player);
    }


    @Test
    public void stepPlayerIsAttacking() throws IOException {
        when(player.getIsAttacking()).thenReturn(10);

        playerController.step(game, GUI.userInput.NONE, 0);

        verify(room, times(1)).checkDamageToMonster();
    }

    @Test
    public void stepPlayerIsNotAttacking() throws IOException {
        playerController.step(game, GUI.userInput.NONE, 0);

        verify(room, times(0)).checkDamageToMonster();
    }

    @Test
    public void stepUp() throws IOException {
        playerController.step(game, GUI.userInput.UP, 0);

        verify(player, times(1)).moveUp();
        verify(player, times(1)).setDirection("UP");
        verify(player, times(1)).setState("WALKING");
    }

    @Test
    public void stepDown() throws IOException {
        playerController.step(game, GUI.userInput.DOWN, 0);

        verify(player, times(1)).moveDown();
        verify(player, times(1)).setDirection("DOWN");
        verify(player, times(1)).setState("WALKING");
    }

    @Test
    public void stepLeft() throws IOException {
        playerController.step(game, GUI.userInput.LEFT, 0);

        verify(player, times(1)).moveLeft();
        verify(player, times(1)).setDirection("LEFT");
        verify(player, times(1)).setState("WALKING");
    }

    @Test
    public void stepRight() throws IOException {
        playerController.step(game, GUI.userInput.RIGHT, 0);

        verify(player, times(1)).moveRight();
        verify(player, times(1)).setDirection("RIGHT");
        verify(player, times(1)).setState("WALKING");
    }

    @Test
    public void stepAttack() throws IOException {
        playerController.step(game, GUI.userInput.ATTACK, 0);

        verify(player, times(1)).attack();
        verify(player, times(1)).setState("ATTACKING");
    }

    @Test
    public void stepSprintNoKeyDelay() throws IOException {
        playerController.step(game, GUI.userInput.SPRINT, 0);

        verify(player, times(1)).sprint();

        assertEquals(10, playerController.getDelay());
    }

    @Test
    public void stepSprintKeyDelay() throws IOException {
        playerController.setDelay();

        playerController.step(game, GUI.userInput.SPRINT, 0);

        verify(player, times(0)).sprint();

        assertEquals(9, playerController.getDelay());
    }

    @Test
    public void stepHeal() throws IOException {
        playerController.step(game, GUI.userInput.HEAL, 0);

        verify(player, times(1)).heal();
    }

    @Test
    public void stepDefault() throws IOException {
        playerController.step(game, GUI.userInput.NONE, 0);

        verify(player, times(1)).setState("IDLE");
    }

    @Test
    public void updateDelayCooldown() {
        playerController.setDelay();

        playerController.updateCoolDowns(player);

        assertEquals(9, playerController.getDelay());
    }

    @Test
    public void noUpdateDelayCooldown() {
        playerController.updateCoolDowns(player);

        assertEquals(0, playerController.getDelay());
    }

    @Test
    public void updateIsAttacking() {
        when(player.getIsAttacking()).thenReturn(10);

        playerController.updateCoolDowns(player);

        verify(player, times(1)).reduceIsAttacking();
    }

    @Test
    public void noUpdateIsAttacking() {
        playerController.updateCoolDowns(player);

        verify(player, times(0)).reduceIsAttacking();
    }

    @Test
    public void updateIsSprinting() {
        when(player.isSprinting()).thenReturn(true);

        playerController.updateCoolDowns(player);

        verify(player, times(1)).reduceEnergy();
    }

    @Test
    public void noUpdateIsSprinting() {
        when(player.isSprinting()).thenReturn(false);

        playerController.updateCoolDowns(player);

        verify(player, times(0)).reduceEnergy();
    }

    @Test
    public void updateEnergy() {
        when(player.getEnergy()).thenReturn(50);

        playerController.updateCoolDowns(player);

        verify(player, times(1)).increaseEnergy(2);
    }

    @Test
    public void noUpdateEnergy() {
        when(player.getEnergy()).thenReturn(800);

        playerController.updateCoolDowns(player);

        verify(player, times(0)).increaseEnergy(2);
    }

    @Test
    public void updateImmunity() {
        when(player.getImmunity()).thenReturn(10);

        playerController.updateCoolDowns(player);

        verify(player, times(1)).reduceImmunity();
    }

    @Test
    public void noUpdateImmunity() {
        when(player.getImmunity()).thenReturn(0);

        playerController.updateCoolDowns(player);

        verify(player, times(0)).reduceImmunity();
    }

    @Test
    public void defaultUpdateCoolDown() {
        playerController.updateCoolDowns(player);

        verify(player, times(1)).updatePotionDuration();
    }

    @Test
    public void nextPlayerPositionNull() throws IOException {
        playerController.handlePlayerMovement(null, game);

        verify(player, times(0)).setPosition(Mockito.any(Vector.class));
        verify(room, times(0)).getDoorNumber(Mockito.any(Vector.class));
        verify(game, times(0)).popState();
    }

    @Test
    public void canMoveToNextPlayerPosition() throws IOException {
        Vector mockedVector = mock(Vector.class);
        when(player.canMove(mockedVector)).thenReturn(true);

        playerController.handlePlayerMovement(mockedVector, game);

        verify(player, times(1)).setPosition(mockedVector);
        verify(room, times(0)).getDoorNumber(mockedVector);
    }

    @Test
    public void canNotMoveAndNoTransition() throws IOException {
        Vector mockedVector = mock(Vector.class);
        when(player.canMove(mockedVector)).thenReturn(false);
        when(room.isAtTransition(player, mockedVector)).thenReturn(false);

        playerController.handlePlayerMovement(mockedVector, game);

        verify(player, times(0)).setPosition(mockedVector);
        verify(room, times(0)).getDoorNumber(mockedVector);
        verify(game, times(0)).setState(any(GameOverMenuState.class));
    }

    @Test
    public void isAtTransitionAndDoorNumberZero() throws IOException {
        Vector mockedVector = mock(Vector.class);
        when(player.canMove(mockedVector)).thenReturn(false);
        when(room.isAtTransition(player, mockedVector)).thenReturn(true);
        when(room.getDoorNumber(mockedVector)).thenReturn(0);

        playerController.handlePlayerMovement(mockedVector, game);

        verify(room, times(1)).getDoorNumber(mockedVector);
        verify(game, times(1)).popState();
    }

    @Test
    public void isAtTransitionAndDoorNumberNotZero() throws IOException {
        Vector mockedVector = mock(Vector.class);
        when(player.canMove(mockedVector)).thenReturn(false);
        when(room.isAtTransition(player, mockedVector)).thenReturn(true);
        when(room.getDoorNumber(mockedVector)).thenReturn(1);

        playerController.handlePlayerMovement(mockedVector, game);

        verify(room, times(1)).getDoorNumber(mockedVector);
        verify(roomManager, times(1)).loadRoom(1);
        verify(game, times(0)).setState(any(GameOverMenuState.class));
    }

    @Test
    public void chooseScreenDefault() throws IOException {
        Game game = mock(Game.class);

        playerController.chooseScreen(game);

        verify(game, times(1)).popState();
    }

    @Test
    public void chooseScreenWithMonsterKilled() throws IOException {
        when(roomManager.getTotalMonsters()).thenReturn(10);
        when(roomManager.getCurrentMonsters()).thenReturn(5);

        playerController.chooseScreen(game);

        assertEquals("LIVED_KILLED", playerController.getGameOverMenuState().getModel().getType());

        verify(game, times(1)).setState(any(GameOverMenuState.class));
    }

    @Test
    public void chooseScreenWithoutMonsterKilled() throws IOException {
        when(roomManager.getTotalMonsters()).thenReturn(10);
        when(roomManager.getCurrentMonsters()).thenReturn(10);

        playerController.chooseScreen(game);

        verify(game, times(1)).setState(any(VictoryMenuState.class));
    }
}