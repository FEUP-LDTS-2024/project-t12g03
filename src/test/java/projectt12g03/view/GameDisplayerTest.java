package projectt12g03.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.gui.GUI;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.Player;
import projectt12g03.model.game.elements.Wall;
import projectt12g03.model.game.room.Room;
import projectt12g03.model.game.room.RoomBuilder;

import java.io.IOException;
import java.util.Arrays;

public class GameDisplayerTest {
    private GUI gui;
    private GameDisplayer displayer;
    private Room room;
    private RoomBuilder roomBuilder;

    @BeforeEach
    void setUp() {
        roomBuilder = new RoomBuilder(123);
        room = roomBuilder.createRoom();
        gui = Mockito.mock(LanternaGui.class);
        displayer = new GameDisplayer(room);

        room.setPlayer(new Player(5,5,room));
        room.setWalls(Arrays.asList(new Wall(1,1),new Wall(2,2), new Wall(2,3)));
    }

    @Test
    void drawWalls() throws IOException {
        displayer.draw(gui);

        Mockito.verify(gui,Mockito.times(1)).drawWall(new Vector(1,1));
        Mockito.verify(gui,Mockito.times(1)).drawWall(new Vector(2,2));
        Mockito.verify(gui,Mockito.times(1)).drawWall(new Vector(2,3));
        Mockito.verify(gui, Mockito.times(3)).drawWall(Mockito.any(Vector.class));
    }

    @Test
    void refreshAndClear() throws IOException {
        displayer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).clear();
        Mockito.verify(gui, Mockito.times(1)).refresh();
    }

}
