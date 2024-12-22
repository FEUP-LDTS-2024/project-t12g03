package projectt12g03.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.movable.hostile.Monster;
import projectt12g03.model.game.elements.movable.player.Player;
import projectt12g03.model.game.room.Room;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PathTest {
    private Path path;
    private Monster monster;
    private Room room;
    private Player player;

    @BeforeEach
    void setup() {
        path = new Path();
        monster = mock(Monster.class);
        room = mock(Room.class);
        player = mock(Player.class);

        when(monster.getRoom()).thenReturn(room);
        when(room.getPlayer()).thenReturn(player);
    }

    @Test
    void testDistance() {
        when(player.getPosition()).thenReturn(new Vector(10, 10));
        Vector position = new Vector(14, 14);

        double result = path.distance(position, room);

        assertEquals(5.656, result, 0.001);
    }

    @Test
    void testTransform() {
        assertEquals("UP", path.transform(0), "Transform 0 should map to UP.");
        assertEquals("DOWN", path.transform(1), "Transform 1 should map to DOWN.");
        assertEquals("RIGHT", path.transform(2), "Transform 2 should map to RIGHT.");
        assertEquals("LEFT", path.transform(3), "Transform 3 should map to LEFT.");
        assertEquals("IDLE", path.transform(4), "Transform 4 should map to IDLE.");

        assertThrows(IllegalStateException.class, () -> path.transform(5));
    }

    @Test
    void nextMoveToPlayerIsIdleDueToDistance() {
        when(monster.getPosition()).thenReturn(new Vector(0, 0));
        when(player.getPosition()).thenReturn(new Vector(100, 100));

        //distance will be greater than the 48 allowed

        String result = path.findNextMoveToPlayer(monster);

        assertEquals("IDLE", result);
    }

    @Test
    void nextMoveToPlayerIsValid() {
        when(monster.getPosition()).thenReturn(new Vector(3, 5));
        when(player.getPosition()).thenReturn(new Vector(6, 5));
        when(monster.canMove(any(Vector.class))).thenReturn(true);

        String result = path.findNextMoveToPlayer(monster);

        assertEquals("RIGHT", result);
    }

    @Test
    void nextMoveToPlayerIsIdleDueToObstacle() {
        when(monster.getPosition()).thenReturn(new Vector(5, 3));
        when(player.getPosition()).thenReturn(new Vector(5, 5));

        when(monster.canMove(new Vector(5, 2))).thenReturn(false); // UP
        when(monster.canMove(new Vector(5, 4))).thenReturn(true);  // DOWN
        when(monster.canMove(new Vector(6, 3))).thenReturn(false); // RIGHT
        when(monster.canMove(new Vector(4, 3))).thenReturn(false); // LEFT

        String result = path.findNextMoveToPlayer(monster);

        assertEquals("DOWN", result);
    }

    @Test
    void nextMoveToPlayerIsIdleNoValidMoves() {
        when(monster.getPosition()).thenReturn(new Vector(5, 5));
        when(player.getPosition()).thenReturn(new Vector(6, 5));

        when(monster.canMove(any(Vector.class))).thenReturn(false);

        String result = path.findNextMoveToPlayer(monster);

        assertEquals("IDLE", result);
    }
}