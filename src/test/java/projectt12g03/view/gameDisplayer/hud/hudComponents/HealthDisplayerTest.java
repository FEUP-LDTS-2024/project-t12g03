package projectt12g03.view.gameDisplayer.hud.hudComponents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.gui.LanternaGui;
import projectt12g03.view.Sprite;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class HealthDisplayerTest {
    private HealthDisplayer healthDisplayer;
    private Sprite sprite;
    private LanternaGui gui;

    @BeforeEach
    void setup() {
        gui = mock(LanternaGui.class);
        sprite = mock(Sprite.class);
    }

    @Test
    void testDrawWithHealth() throws IOException {
        healthDisplayer = spy(new HealthDisplayer());

        doReturn(sprite).when(healthDisplayer).getSprite();

        healthDisplayer.draw(4, gui);

        for (int i = 0; i < 4; i++) {
            verify(sprite, times(1)).draw(gui, 19 + (i * 16), 266);
        }
        verify(sprite, times(4)).draw(eq(gui), anyInt(), anyInt());

        assertNotNull(sprite);
    }

    @Test
    void testDrawWithoutHealth() throws IOException {
        healthDisplayer = spy(new HealthDisplayer());

        doReturn(sprite).when(healthDisplayer).getSprite();

        healthDisplayer.draw(0, gui);

        for (int i = 0; i < 4; i++) {
            verify(sprite, never()).draw(gui, 246 + (i * 14), 266);
        }
        verify(sprite, times(0)).draw(eq(gui), anyInt(), anyInt());

        assertNotNull(sprite);
    }

    @Test
    void getSpriteTest() throws IOException {
        healthDisplayer = new HealthDisplayer();

        Sprite image = healthDisplayer.getSprite();

        assertNotNull(image);
    }
}