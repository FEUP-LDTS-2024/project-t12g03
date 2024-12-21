package projectt12g03.controller.gameController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.Game;
import projectt12g03.gui.GUI;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.movable.player.Player;
import projectt12g03.model.game.elements.stationary.Laser;
import projectt12g03.model.game.room.Room;
import projectt12g03.model.game.room.RoomManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class LaserControllerTest {
    private Game game;
    private Room room;
    private LaserController laserController;
    private Laser laser;

    @BeforeEach
    public void setUp() {
        this.room = Mockito.mock(Room.class);
        RoomManager roomManager = Mockito.mock(RoomManager.class);
        Player player = Mockito.mock(Player.class);
        this.game = Mockito.mock(Game.class);
        this.laser = mock(Laser.class);

        laserController = new LaserController(roomManager);

        Mockito.when(player.getPosition()).thenReturn(new Vector(5, 5));
        Mockito.when(roomManager.getRoom()).thenReturn(room);
        Mockito.when(roomManager.getPlayer()).thenReturn(player);

        when(laser.isPointingLeft()).thenReturn(false);
        when(room.getLasers()).thenReturn(List.of(laser));
    }

    @Test
    public void stepWithNoLasers() throws IOException {
        when(room.getLasers()).thenReturn(new ArrayList<>());

        laserController.step(game, GUI.userInput.NONE, 0);

        verify(laser, times(0)).getActive();
        verify(laser, times(0)).getResting();
    }

    @Test
    public void stepWithLaserActive() throws IOException {
        when(laser.getActive()).thenReturn(5);
        when(laser.getResting()).thenReturn(0);

        laserController.step(game, GUI.userInput.NONE, 0);

        verify(laser, times(1)).reduceActive();
        verify(laser, times(0)).changeMode();
    }

    @Test
    public void stepWithLaserActiveAndChangeMode() throws IOException {
        when(laser.getActive()).thenReturn(0);
        when(laser.getResting()).thenReturn(0);

        laserController.step(game, GUI.userInput.NONE, 0);

        verify(laser, times(0)).reduceActive();
        verify(laser, times(1)).changeMode();
        verify(laser, times(0)).reduceResting();
    }

    @Test
    public void stepWithLaserResting() throws IOException {
        when(laser.getActive()).thenReturn(0);
        when(laser.getResting()).thenReturn(5);

        laserController.step(game, GUI.userInput.NONE, 0);

        verify(laser, times(1)).reduceResting();
        verify(laser, times(0)).changeMode();
    }

    @Test
    public void stepWithLaserRestingAndChangeMode() throws IOException {
        when(laser.getActive()).thenReturn(0);
        when(laser.getResting()).thenReturn(0);

        laserController.step(game, GUI.userInput.NONE, 0);

        verify(laser, times(0)).reduceActive();
        verify(laser, times(1)).changeMode();
        verify(laser, times(0)).reduceResting();
    }


}