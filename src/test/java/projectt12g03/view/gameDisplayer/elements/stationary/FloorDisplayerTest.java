package projectt12g03.view.gameDisplayer.elements.stationary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.gui.GUI;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.stationary.Floor;
import projectt12g03.view.Sprite;
import projectt12g03.view.gameDisplayer.elements.stationary.FloorDisplayer;

import java.io.IOException;

import static org.mockito.Mockito.*;

class FloorDisplayerTest {
    private FloorDisplayer floorDisplayer;
    private Floor mockFloor;
    private GUI mockGui;
    private Sprite mockSpriteD;
    private Sprite mockSpriteE;

    @BeforeEach
    void setUp() throws Exception {
        mockFloor = mock(Floor.class);
        mockGui = mock(GUI.class);

        mockSpriteD = mock(Sprite.class);
        mockSpriteE = mock(Sprite.class);

        floorDisplayer = new FloorDisplayer();

        floorDisplayer.getSpriteMap().put('d', mockSpriteD);
        floorDisplayer.getSpriteMap().put('e', mockSpriteE);
    }

    @Test
    void testDrawLighterGrassFloor() {
        when(mockFloor.getType()).thenReturn('d');
        when(mockFloor.getPosition()).thenReturn(new Vector(10, 20));

        floorDisplayer.draw(mockFloor, mockGui, 0);

        verify(mockSpriteD, times(1)).draw(mockGui, 10, 20);
        verify(mockSpriteE, never()).draw(any(), anyInt(), anyInt());
    }

    @Test
    void testDrawPortalFloor() {
        when(mockFloor.getType()).thenReturn('e');
        when(mockFloor.getPosition()).thenReturn(new Vector(15, 25));

        floorDisplayer.draw(mockFloor, mockGui, 0);

        verify(mockSpriteE, times(1)).draw(mockGui, 15, 25);
        verify(mockSpriteD, never()).draw(any(), anyInt(), anyInt());
    }

    @Test
    void testUnknownFloorType() {
        when(mockFloor.getType()).thenReturn('x');
        when(mockFloor.getPosition()).thenReturn(new Vector(5, 5));

        floorDisplayer.draw(mockFloor, mockGui, 0);

        verify(mockSpriteD, never()).draw(any(), anyInt(), anyInt());
        verify(mockSpriteE, never()).draw(any(), anyInt(), anyInt());
    }
}