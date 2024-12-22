package projectt12g03.view.gameDisplayer.hud.hudComponents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.gui.LanternaGui;
import projectt12g03.view.Sprite;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnergyDisplayerTest {
    private EnergyDisplayer energyDisplayer;
    private Sprite sprite;
    private LanternaGui gui;

    @BeforeEach
    void setup() throws IOException {
        gui = mock(LanternaGui.class);
        sprite = mock(Sprite.class);

        energyDisplayer = spy(new EnergyDisplayer());

        doReturn(sprite).when(energyDisplayer).getSprite();
    }

    @Test
    void testDrawWithEnergy() {
        energyDisplayer.draw(800, gui);

        for (int i = 0; i < 4; i++) {
            verify(sprite, times(1)).draw(gui, 246 + (i * 14), 266);
        }
        verify(sprite, times(4)).draw(eq(gui), anyInt(), anyInt());

        assertNotNull(sprite);
    }

    @Test
    void testDrawWithoutEnergy() {
        energyDisplayer.draw(0, gui);

        for (int i = 0; i < 4; i++) {
            verify(sprite, never()).draw(gui, 246 + (i * 14), 266);
        }
        verify(sprite, times(0)).draw(eq(gui), anyInt(), anyInt());

        assertNotNull(sprite);
    }

    @Test
    void getSpriteTest() throws IOException {
        energyDisplayer = new EnergyDisplayer();

        Sprite image = energyDisplayer.getSprite();

        assertNotNull(image);
    }
}