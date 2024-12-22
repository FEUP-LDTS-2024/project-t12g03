package projectt12g03.view.gameDisplayer.elements.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.gui.GUI;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.movable.player.Player;
import projectt12g03.view.Sprite;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class SpearDisplayerTest {
    private SpearDisplayer spearDisplayer;
    private GUI mockGui;
    private Player mockPlayer;
    private Sprite mockSpriteUp;
    private Sprite mockSpriteDown;
    private Sprite mockSpriteLeft;
    private Sprite mockSpriteRight;

    @BeforeEach
    void setUp() throws IOException {
        mockGui = mock(GUI.class);
        mockPlayer = mock(Player.class);

        mockSpriteUp = mock(Sprite.class);
        mockSpriteDown = mock(Sprite.class);
        mockSpriteLeft = mock(Sprite.class);
        mockSpriteRight = mock(Sprite.class);

        spearDisplayer = new SpearDisplayer() {
            {
                getSpearMap().put("UP", mockSpriteUp);
                getSpearMap().put("DOWN", mockSpriteDown);
                getSpearMap().put("LEFT", mockSpriteLeft);
                getSpearMap().put("RIGHT", mockSpriteRight);
            }
        };
    }

    @Test
    void testDrawSpearUp() {
        when(mockPlayer.getDirection()).thenReturn("UP");
        when(mockPlayer.getPosition()).thenReturn(new Vector(10, 20));

        spearDisplayer.draw(mockPlayer, mockGui);

        verify(mockSpriteUp, times(1)).draw(mockGui, 10, 22);
    }

    @Test
    void testDrawSpearDown() {
        when(mockPlayer.getDirection()).thenReturn("DOWN");
        when(mockPlayer.getPosition()).thenReturn(new Vector(15, 25));

        spearDisplayer.draw(mockPlayer, mockGui);

        verify(mockSpriteDown, times(1)).draw(mockGui, 15, 57);
    }

    @Test
    void testDrawSpearLeft() {
        when(mockPlayer.getDirection()).thenReturn("LEFT");
        when(mockPlayer.getPosition()).thenReturn(new Vector(30, 40));

        spearDisplayer.draw(mockPlayer, mockGui);

        verify(mockSpriteLeft, times(1)).draw(mockGui, 15, 56);
    }

    @Test
    void testDrawSpearRight() {
        when(mockPlayer.getDirection()).thenReturn("RIGHT");
        when(mockPlayer.getPosition()).thenReturn(new Vector(50, 60));

        spearDisplayer.draw(mockPlayer, mockGui);

        verify(mockSpriteRight, times(1)).draw(mockGui, 65, 76);
    }

    @Test
    void testDrawWithInvalidDirectionThrowsException() {
        when(mockPlayer.getDirection()).thenReturn("INVALID");
        when(mockPlayer.getPosition()).thenReturn(new Vector(10, 10));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> spearDisplayer.draw(mockPlayer, mockGui));

        assert (exception.getMessage().contains("Invalid Direction for Spear"));

        verifyNoInteractions(mockSpriteUp, mockSpriteDown, mockSpriteLeft, mockSpriteRight);
    }
}