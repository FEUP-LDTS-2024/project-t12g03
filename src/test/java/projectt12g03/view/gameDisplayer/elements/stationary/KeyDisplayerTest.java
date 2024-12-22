package projectt12g03.view.gameDisplayer.elements.stationary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.gui.GUI;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.stationary.Key;
import projectt12g03.view.Sprite;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class KeyDisplayerTest {
    private KeyDisplayer keyDisplayer;
    private Key mockKey;
    private GUI mockGui;
    private Sprite mockSprite;

    @BeforeEach
    void setUp() throws IOException {
        mockKey = mock(Key.class);
        mockGui = mock(GUI.class);
        mockSprite = mock(Sprite.class);

        keyDisplayer = new KeyDisplayer() {
            @Override
            public Sprite getSprite() {
                return mockSprite;
            }
        };
    }

    @Test
    void testDrawKey() {
        when(mockKey.getPosition()).thenReturn(new Vector(10, 20));

        keyDisplayer.draw(mockKey, mockGui, 0);

        verify(mockSprite, times(1)).draw(mockGui, 10, 20);
    }

    @Test
    void testNotDrawKey() {
        keyDisplayer.draw(null, mockGui, 0);

        verifyNoInteractions(mockSprite);
    }

    @Test
    void testGetSprite() throws IOException {
        keyDisplayer = new KeyDisplayer();

        Sprite image = keyDisplayer.getSprite();

        assertNotNull(image);
    }
}