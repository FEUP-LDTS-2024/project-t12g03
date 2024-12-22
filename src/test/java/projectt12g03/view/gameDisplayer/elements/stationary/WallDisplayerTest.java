package projectt12g03.view.gameDisplayer.elements.stationary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.gui.GUI;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.stationary.Wall;
import projectt12g03.view.Sprite;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class WallDisplayerTest {
    private WallDisplayer wallDisplayer;
    private Wall mockWall;
    private GUI mockGui;
    private Sprite mockSprite;

    @BeforeEach
    void setUp() throws IOException {
        mockWall = mock(Wall.class);
        mockGui = mock(GUI.class);
        mockSprite = mock(Sprite.class);

        wallDisplayer = new WallDisplayer() {
            @Override
            public Sprite getSprite() {
                return mockSprite;
            }
        };
    }

    @Test
    void testDrawWall() {
        when(mockWall.getPosition()).thenReturn(new Vector(15, 25));

        wallDisplayer.draw(mockWall, mockGui, 0);

        verify(mockSprite, times(1)).draw(mockGui, 15, 25);
    }

    @Test
    void testGetSprite() throws IOException {
        wallDisplayer = new WallDisplayer();

        Sprite image = wallDisplayer.getSprite();

        assertNotNull(image);
    }
}