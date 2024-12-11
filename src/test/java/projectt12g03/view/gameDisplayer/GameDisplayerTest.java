package projectt12g03.view.game;

import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.gui.GUI;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.Monster;
import projectt12g03.model.game.elements.Player;
import projectt12g03.model.game.elements.Wall;
import projectt12g03.model.game.room.Room;
import projectt12g03.model.game.room.RoomManager;

import java.io.IOException;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class GameDisplayerTest {
    private GUI gui;
    private GameDisplayer displayer;
    private RoomManager roomManager;
    private Room room;
    private Player player;
    private Monster monster;

    @BeforeEach
    void setUp() throws IOException {
        gui = mock(GUI.class);
        roomManager = mock(RoomManager.class);
        room = mock(Room.class);

        player = mock(Player.class);
        monster = mock(Monster.class);

        when(roomManager.getRoom()).thenReturn(room);
        when(room.getPlayer()).thenReturn(player);
        when(room.getMonsters()).thenReturn(Arrays.asList(monster));
        when(room.getWalls()).thenReturn(Arrays.asList(new Wall(1, 1), new Wall(2, 2), new Wall(2, 3)));

        displayer = new GameDisplayer(roomManager);
    }

    @Test
    void drawShouldClearAndRefresh() throws IOException {
        displayer.draw(gui);

        verify(gui).clear();
        verify(gui).refresh();
    }

    @Test
    void drawShouldDisplayPlayer() throws IOException {
        when(player.getPosition()).thenReturn(new Vector(0, 0));

        displayer.draw(gui);

        verify(gui).drawText(new Vector(0, 0), "P", new TextColor.RGB(85, 201, 81));
    }

    @Test
    void drawShouldDisplayWalls() throws IOException {
        displayer.draw(gui);

        verify(gui).drawWall(new Vector(1, 1));
        verify(gui).drawWall(new Vector(2, 2));
        verify(gui).drawWall(new Vector(2, 3));
    }

    @Test
    void drawShouldDisplayMonsters() throws IOException {
        when(monster.getPosition()).thenReturn(new Vector(5, 5));

        displayer.draw(gui);

        verify(gui).drawText(new Vector(5, 5), "M", new TextColor.RGB(255, 0, 0));
    }

    @Test
    void drawShouldHandleNoWalls() throws IOException {
        when(room.getWalls()).thenReturn(Arrays.asList());

        displayer.draw(gui);

        verify(gui, never()).drawWall(any(Vector.class));
    }

    @Test
    void drawShouldHandleNoMonsters() throws IOException {
        when(room.getMonsters()).thenReturn(Arrays.asList());

        displayer.draw(gui);

        verify(gui, never()).drawText(any(Vector.class), eq("M"), any());
    }
}
