package projectt12g03.view.gameDisplayer;

import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.game.elements.movable.Player;
import projectt12g03.model.game.room.Room;
import projectt12g03.view.gameDisplayer.elements.movable.PlayerDisplayer;

import java.io.IOException;

public class PlayerDisplayerTest {
    private Player player;
    private PlayerDisplayer displayer;
    private LanternaGui gui;

    @BeforeEach
    void setUp(){
        player = new Player(10,10,new Room(20,20));
        displayer = new PlayerDisplayer();
        gui = Mockito.mock(LanternaGui.class);
    }

    @Test
    void drawPlayer() throws IOException {
        displayer.draw(player, gui);
        Mockito.verify(gui,Mockito.times(1)).drawText(player.getPosition(), "P", new TextColor.RGB(85,201,81));
    }
}
