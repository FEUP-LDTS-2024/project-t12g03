package projectt12g03.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.model.game.elements.Player;
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
        player = new Player(0, 0, room);
        player.setPosition(new Vector(2, 1));
    }

    @Test
    void moveLeftCanMove() {
        when(player.canMove(any(Vector.class))).thenReturn(true);

        Vector position = player.moveLeft();

        assertEquals(1, position.getX());
    }

    @Test
    void moveLeftCantMove() {
        when(player.canMove(any(Vector.class))).thenReturn(false);

        Vector position = player.moveLeft();

        assertEquals(2, position.getX());
    }

    @Test
    void moveRightCanMove() {
        when(player.canMove(any(Vector.class))).thenReturn(true);

        Vector position = player.moveRight();

        assertEquals(3, position.getX());
    }

    @Test
    void moveRightCantMove() {
        when(player.canMove(any(Vector.class))).thenReturn(false);

        Vector position = player.moveRight();

        assertEquals(2, position.getX());
    }

    @Test
    void moveUpCanMove() {
        when(player.canMove(any(Vector.class))).thenReturn(true);

        Vector position = player.moveUp();

        assertEquals(0, position.getY());
    }

    @Test
    void moveUpCantMove() {
        when(player.canMove(any(Vector.class))).thenReturn(false);

        Vector position = player.moveUp();

        assertEquals(1, position.getY());
    }

    @Test
    void moveDownCanMove() {
        when(player.canMove(any(Vector.class))).thenReturn(true);

        Vector position = player.moveDown();

        assertEquals(2, position.getY());
    }

    @Test
    void moveDownCantMove() {
        when(player.canMove(any(Vector.class))).thenReturn(false);

        Vector position = player.moveDown();

        assertEquals(1, position.getY());
    }

    @Test
    void canMoveHappen() {
        when(room.checkCollisions(any(Vector.class))).thenReturn(false);

        Boolean fountObstacle = player.canMove(any(Vector.class));

        assertEquals(false, fountObstacle);
    }

    @Test
    void canMoveNotHappen() {
        when(room.checkCollisions(any(Vector.class))).thenReturn(false);

        Boolean foundObstacle = player.canMove(any(Vector.class));

        assertEquals(false, foundObstacle);
    }
}
