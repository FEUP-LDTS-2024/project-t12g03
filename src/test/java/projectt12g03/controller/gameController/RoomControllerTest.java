package projectt12g03.controller.gameController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.Game;
import projectt12g03.gui.GUI;
import projectt12g03.model.game.elements.Player;
import projectt12g03.model.game.room.Room;
import projectt12g03.model.game.room.RoomManager;

import java.io.IOException;

class RoomControllerTest {
    private Game game;
    private RoomController roomController;
    private RoomManager roomManager;
    private PlayerController playerController;
    private MonsterController monsterController;

    @BeforeEach
    public void setup() {
        this.game = Mockito.mock(Game.class);
        this.roomManager = Mockito.mock(RoomManager.class);

        Player player = Mockito.mock(Player.class);
        Mockito.when(roomManager.getRoom().getPlayer()).thenReturn(player);

        this.playerController = Mockito.mock(PlayerController.class);
        this.monsterController = Mockito.mock(MonsterController.class);

        this.roomController = new RoomController(roomManager, playerController, monsterController);
    }

    @Test
    public void step() throws IOException {
        GUI.userInput action = GUI.userInput.NONE;
        long frameCount = 0;

        roomController.step(game, action, frameCount);
        Mockito.verify(playerController, Mockito.times(1))
                .step(game, action, frameCount);
        Mockito.verify(monsterController, Mockito.times(1)).step(game, action, frameCount);
    }
}