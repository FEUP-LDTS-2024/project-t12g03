package projectt12g03.model.game.elements;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.model.Vector;
import projectt12g03.model.game.room.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class PlayerModelTest {
    private Player player;
    private Room room;

    @BeforeEach
    void setup() {
        room = mock(Room.class);
        player = new Player(2, 1, room);
    }

    @Test
    void moveLeftCanMove() {
        when(room.checkCollisions(any(Vector.class))).thenReturn(false);

        Vector position = player.moveLeft();

        assertEquals(1, position.getX());
        verify(room, times(1)).checkCollisions(any(Vector.class));
    }

    @Test
    void moveLeftCantMove() {
        when(room.checkCollisions(any(Vector.class))).thenReturn(true);

        Vector position = player.moveLeft();

        assertEquals(2, position.getX());
        verify(room, times(1)).checkCollisions(any(Vector.class));
    }

    @Test
    void moveRightCanMove() {
        when(room.checkCollisions(any(Vector.class))).thenReturn(false);

        Vector position = player.moveRight();

        assertEquals(3, position.getX());
        verify(room, times(1)).checkCollisions(any(Vector.class));
    }

    @Test
    void moveRightCantMove() {
        when(room.checkCollisions(any(Vector.class))).thenReturn(true);

        Vector position = player.moveRight();

        assertEquals(2, position.getX());
        verify(room, times(1)).checkCollisions(any(Vector.class));
    }

    @Test
    void moveUpCanMove() {
        when(room.checkCollisions(any(Vector.class))).thenReturn(false);

        Vector position = player.moveUp();

        assertEquals(0, position.getY());
        verify(room, times(1)).checkCollisions(any(Vector.class));
    }

    @Test
    void moveUpCantMove() {
        when(room.checkCollisions(any(Vector.class))).thenReturn(true);

        Vector position = player.moveUp();

        assertEquals(1, position.getY());
        verify(room, times(1)).checkCollisions(any(Vector.class));
    }

    @Test
    void moveDownCanMove() {
        when(room.checkCollisions(any(Vector.class))).thenReturn(false);

        Vector position = player.moveDown();

        assertEquals(2, position.getY());
        verify(room, times(1)).checkCollisions(any(Vector.class));
    }

    @Test
    void moveDownCantMove() {
        when(room.checkCollisions(any(Vector.class))).thenReturn(true);

        Vector position = player.moveDown();

        assertEquals(1, position.getY());
        verify(room, times(1)).checkCollisions(any(Vector.class));
    }

    @Test
    void canMoveHappen() {
        Vector testPosition = new Vector(2, 2);
        when(room.checkCollisions(testPosition)).thenReturn(false);

        Boolean canMove = player.canMove(testPosition);

        assertEquals(true, canMove);
        verify(room, times(1)).checkCollisions(any(Vector.class));
    }

    @Test
    void canMoveNotHappen() {
        Vector testPosition = new Vector(2, 2);
        when(room.checkCollisions(testPosition)).thenReturn(true);

        Boolean canMove = player.canMove(testPosition);

        assertEquals(false, canMove);
        verify(room, times(1)).checkCollisions(any(Vector.class));
    }
}
