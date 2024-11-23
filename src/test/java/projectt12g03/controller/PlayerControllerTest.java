package projectt12g03.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.Game;
import projectt12g03.gui.GUI;
import projectt12g03.model.elements.Player;
import projectt12g03.model.room.Room;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PlayerControllerTest {
    private Game game;
    private Room room;
    private Player player;
    private PlayerController playerController;

    @BeforeEach
    public void setup() {
        this.room = Mockito.mock(Room.class);
        this.player = Mockito.mock(Player.class);
        Mockito.when(room.getPlayer()).thenReturn(player);
        this.game = Mockito.mock(Game.class);
        this.playerController = new PlayerController(room);
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