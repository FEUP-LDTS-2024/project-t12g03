package projectt12g03.controller.gameController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.Game;
import projectt12g03.gui.GUI;
import projectt12g03.model.game.elements.movable.player.Player;
import projectt12g03.model.game.room.Room;
import projectt12g03.model.game.room.RoomManager;

import java.io.IOException;

class GameControllerTest {
    private Game game;
    private Room room;
    private GameController gameController;
    private RoomManager roomManager;
    private PlayerController playerController;
    private MonsterController monsterController;
    private LaserController laserController;
    private HudController hudController;

    @BeforeEach
    public void setup() {
        this.game = Mockito.mock(Game.class);
        this.room = Mockito.mock((Room.class));
        this.roomManager = Mockito.mock(RoomManager.class);

        Player player = Mockito.mock(Player.class);

        Mockito.when(roomManager.getRoom()).thenReturn(room);
        Mockito.when(roomManager.getPlayer()).thenReturn(player);

        this.playerController = Mockito.mock(PlayerController.class);
        this.monsterController = Mockito.mock(MonsterController.class);
        this.laserController = Mockito.mock(LaserController.class);
        this.hudController = Mockito.mock(HudController.class);
        this.gameController = new GameController(roomManager, playerController, monsterController,laserController,hudController);
    }

    @Test
    public void step() throws IOException {
        GUI.userInput action = GUI.userInput.NONE;
        long frameCount = 0;

        gameController.step(game, action, frameCount);
        Mockito.verify(playerController, Mockito.times(1))
                .step(game, action, frameCount);
        Mockito.verify(monsterController, Mockito.times(1)).step(game, action, frameCount);
        Mockito.verify(laserController,Mockito.times(1)).step(game,action,frameCount);
        Mockito.verify(hudController,Mockito.times(1)).step(game,action,frameCount);
    }
}