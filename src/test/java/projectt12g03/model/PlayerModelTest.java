package projectt12g03.model;


import projectt12g03.model.elements.Player;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlayerModelTest {
    private Player player;



    @Property
    void moveTest(@ForAll int x, @ForAll int y) {
        player = new Player(0,0);
        Vector next = new Vector(x,y);
        player.move(next);
        assertEquals(next,player.getPosition());
    }
}
