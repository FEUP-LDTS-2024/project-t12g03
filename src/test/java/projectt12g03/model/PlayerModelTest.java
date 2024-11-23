package projectt12g03.model;


import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import projectt12g03.model.elements.Player;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import projectt12g03.model.room.Room;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlayerModelTest {
    private Room room;

    @Property
    void moveLeft(@ForAll int x, @ForAll int y) {
        room = Mockito.mock(Room.class);
        Mockito.when(room.checkCollisions(new Vector(x-1,y))).thenReturn(true);

        assertEquals(x - 1, new Player(x,y,room).moveLeft().getX());
        assertEquals(y, new Player(x,y,room).moveLeft().getY());
    }

}
