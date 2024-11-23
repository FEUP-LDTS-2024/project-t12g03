package projectt12g03.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.gui.GUI;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.elements.Player;

import java.io.IOException;

public class PlayerDisplayerTest {
    private Player player;
    private PlayerDisplayer displayer;
    private LanternaGui gui;

    @BeforeEach
    void setUp(){
        player = new Player(10,10);
        displayer = new PlayerDisplayer(player);
        gui = Mockito.mock(LanternaGui.class);
    }

    @Test
    void drawPlayer() throws IOException {
        displayer.draw(gui);
        Mockito.verify(gui,Mockito.times(1)).drawPlayer(player.getPosition(),player.getDesign(), player.getColor());
    }
}
