package projectt12g03.model.game.room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.model.game.elements.Player;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RoomBuilderTest {
    private Player player;

    @BeforeEach
    public void setup() {
        this.player = new Player(0, 0, null);
    }

    @Test
    void createRoom() throws IOException {
        RoomBuilder roomBuilder = new RoomBuilder(1);
        Room room = roomBuilder.createRoom(player);

        assertNotNull(room);
        assertNotNull(room.getWalls());
        assertNotNull(room.getPlayer());
    }
}