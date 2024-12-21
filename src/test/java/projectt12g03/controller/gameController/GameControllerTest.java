package projectt12g03.controller.gameController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.Game;
import projectt12g03.gui.GUI;
import projectt12g03.model.game.elements.movable.player.Player;
import projectt12g03.model.game.elements.stationary.Chest;
import projectt12g03.model.game.elements.stationary.Potion;
import projectt12g03.model.game.room.Room;
import projectt12g03.model.game.room.RoomManager;
import projectt12g03.state.menuSate.GameOverMenuState;
import projectt12g03.state.menuSate.PauseMenuState;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GameControllerTest {
    private Game game;
    private Room room;
    private Player player;
    private Chest chest;
    private GameController gameController;
    private RoomManager roomManager;
    private PlayerController playerController;
    private MonsterController monsterController;
    private LaserController laserController;
    private HudController hudController;

    @BeforeEach
    public void setup() throws IOException {
        this.game = Mockito.mock(Game.class);
        this.room = Mockito.mock((Room.class));
        this.roomManager = Mockito.mock(RoomManager.class);
        this.player = Mockito.mock(Player.class);
        this.chest = Mockito.mock(Chest.class);

        when(roomManager.getRoom()).thenReturn(room);
        when(roomManager.getPlayer()).thenReturn(player);
        when(roomManager.getRoom().getChest()).thenReturn(chest);

        this.playerController = Mockito.mock(PlayerController.class);
        this.monsterController = Mockito.mock(MonsterController.class);
        this.laserController = Mockito.mock(LaserController.class);
        this.hudController = Mockito.mock(HudController.class);

        this.gameController = new GameController(roomManager, playerController, monsterController,laserController,hudController);
    }

    @Test
    public void stepWithPause() throws IOException {
        GUI.userInput action = GUI.userInput.PAUSE;

        gameController.step(game, action, 0);

        verify(game, times(1)).setState(any(PauseMenuState.class));

        verify(playerController, times(0))
                .step(game, action, 0);

        verify(monsterController, times(0))
                .step(game, action, 0);

        verify(laserController, times(0))
                .step(game,action,0);

        verify(hudController, times(0))
                .step(game,action,0);
    }

    @Test
    public void stepWithoutPause() throws IOException {
        GUI.userInput action = GUI.userInput.NONE;

        gameController.step(game, action, 0);

        verify(playerController, times(1))
                .step(game, action, 0);

        verify(monsterController, times(1))
                .step(game, action, 0);

        verify(laserController, times(1))
                .step(game,action,0);

        verify(hudController, times(1))
                .step(game,action,0);
    }

    @Test
    public void stepWithoutPotionImmunity() throws IOException {
        GUI.userInput action = GUI.userInput.NONE;

        Potion potion = Mockito.mock(Potion.class);

        when(chest.getPotion()).thenReturn(potion);
        when(chest.isOpened()).thenReturn(true);

        when(potion.getImmunity()).thenReturn(0);

        gameController.step(game, action, 0);

        verify(potion, times(0)).reduceImmunity();
    }

    @Test
    public void stepWithPotionImmunity() throws IOException {
        GUI.userInput action = GUI.userInput.NONE;

        Potion potion = Mockito.mock(Potion.class);

        when(chest.getPotion()).thenReturn(potion);
        when(chest.isOpened()).thenReturn(true);

        when(potion.getImmunity()).thenReturn(1);

        gameController.step(game, action, 0);

        verify(potion, times(1)).reduceImmunity();
    }

    @Test
    public void stepWithoutHealth() throws IOException {
        GUI.userInput action = GUI.userInput.NONE;

        when(player.getHealth()).thenReturn(0);

        gameController.step(game, action, 0);

        verify(game, times(1)).popState();
        verify(game, times(1)).setState(Mockito.any(GameOverMenuState.class));
    }

    @Test
    public void stepWithHealth() throws IOException {
        GUI.userInput action = GUI.userInput.NONE;

        when(player.getHealth()).thenReturn(1);

        gameController.step(game, action, 0);

        verify(game, times(0)).popState();
        verify(game, times(0)).setState(Mockito.any(GameOverMenuState.class));
    }

    @Test
    public void stepDeathWithMonstersKilled() throws IOException {
        GUI.userInput action = GUI.userInput.NONE;

        when(player.getHealth()).thenReturn(0);
        when(roomManager.getTotalMonsters()).thenReturn(20);
        when(roomManager.getCurrentMonsters()).thenReturn(10);

        gameController.step(game, action, 0);

        verify(game, times(1)).popState();
        verify(game, times(1)).setState(gameController.getGameOverMenuState());

        assertEquals("DIED_KILLED", gameController.getGameOverMenuState().getModel().getType());
    }

    @Test
    public void stepDeathWithoutMonstersKilled() throws IOException {
        GUI.userInput action = GUI.userInput.NONE;

        when(player.getHealth()).thenReturn(0);
        when(roomManager.getTotalMonsters()).thenReturn(20);
        when(roomManager.getCurrentMonsters()).thenReturn(20);

        gameController.step(game, action, 0);

        verify(game, times(1)).popState();
        verify(game, times(1)).setState(any(GameOverMenuState.class));

        assertEquals("DIED_!KILLED", gameController.getGameOverMenuState().getModel().getType());
    }
}