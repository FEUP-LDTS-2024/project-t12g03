package projectt12g03.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.Game;
import projectt12g03.gui.GUI;
import projectt12g03.model.game.elements.Player;
import projectt12g03.model.game.room.Room;

import java.io.IOException;

class RoomControllerTest {
    private Game game;
    private RoomController roomController;
    private Room room;
    private PlayerController playerController;

    @BeforeEach
    public void setup() {
        this.game = Mockito.mock(Game.class);
        this.room = Mockito.mock(Room.class);

        Player player = Mockito.mock(Player.class);
        Mockito.when(room.getPlayer()).thenReturn(player);

        this.playerController = Mockito.mock(PlayerController.class);

        this.roomController = new RoomController(room, playerController);
    }

    @Test
    public void step() throws IOException {
        GUI.userInput action = GUI.userInput.NONE;
        long frameCount = 0;

        roomController.step(game, action, frameCount);
        Mockito.verify(playerController, Mockito.times(1))
                .step(game, action, frameCount);
    }
}