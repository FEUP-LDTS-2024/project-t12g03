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

import java.io.IOException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PlayerControllerTest {
    private Game game;
    private Room room;
    private RoomManager roomManager;
    private Player player;
    private PlayerController playerController;

    @BeforeEach
    public void setup() {
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
    public void stepLeft() throws IOException {
        playerController.step(game, GUI.userInput.LEFT, 0);

        verify(player, times(1))
                .moveLeft();
        verify(player, times(1))
                .setPosition(Mockito.any());
    }

    @Test
    public void stepRight() throws IOException {
        playerController.step(game, GUI.userInput.RIGHT, 0);

        verify(player, times(1))
                .moveRight();
        verify(player, times(1))
                .setPosition(Mockito.any());
    }

    @Test
    public void stepUp() throws IOException {
        playerController.step(game, GUI.userInput.UP, 0);

        verify(player, times(1))
                .moveUp();
        verify(player, times(1))
                .setPosition(Mockito.any());
    }

    @Test
    public void stepDown() throws IOException {
        playerController.step(game, GUI.userInput.DOWN, 0);

        verify(player, times(1))
                .moveDown();
        verify(player, times(1))
                .setPosition(Mockito.any());
    }

}