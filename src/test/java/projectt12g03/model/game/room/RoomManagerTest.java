package projectt12g03.model.game.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class RoomManagerTest {
    private final int STARTING_ROOM = 1;
    RoomManager roomManager;

    @BeforeEach
    void setup() throws IOException {
        roomManager = spy(new RoomManager());
    }

    @Test
    void createsRoomTest() {
        assertNotNull(roomManager.getRoom());
    }

    @Test
    void createRoomTest() {
        Room room = roomManager.getRoom();
        assertNotNull(room.getWalls());
        assertNotNull(room.getPlayer());
        assertNotNull(room.getChest());
        assertNotNull(room.getKey());
        assertNotNull(room.getFloors());
        assertNotNull(room.getLasers());
        assertNotNull(room.getMonsters());
    }

    @Test
    void testGetTotalMonsters() {
        assertEquals(5, roomManager.getTotalMonsters());
    }

    @Test
    void testGetCurrentMonsters() {
        assertEquals(5, roomManager.getCurrentMonsters());
    }
}
