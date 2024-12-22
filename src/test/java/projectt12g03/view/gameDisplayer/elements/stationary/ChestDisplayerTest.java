package projectt12g03.view.gameDisplayer.elements.stationary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.stationary.Chest;
import projectt12g03.model.game.elements.stationary.Potion;
import projectt12g03.view.Sprite;
import projectt12g03.view.gameDisplayer.hud.hudComponents.EnergyDisplayer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChestDisplayerTest {
    private ChestDisplayer chestDisplayer;
    private PotionDisplayer potionDisplayer;
    private Chest chest;
    private Potion potion;
    private Sprite sprite;
    private LanternaGui gui;

    @BeforeEach
    void setup() throws IOException {
        chest = mock(Chest.class);
        potion = mock(Potion.class);
        sprite = mock(Sprite.class);
        gui = mock(LanternaGui.class);
        potionDisplayer = mock(PotionDisplayer.class);

        chestDisplayer = spy(new ChestDisplayer());

        chestDisplayer.setPotionDisplayer(potionDisplayer);
        doReturn(sprite).when(chestDisplayer).getSprite();
    }

    @Test
    void testDrawChestNotOpened() {
        when(chest.isOpened()).thenReturn(false);
        when(chest.getPosition()).thenReturn(new Vector(0,0));

        chestDisplayer.draw(chest, gui, 0);

        verify(sprite, times(1)).draw(gui, 0,0);
    }

    @Test
    void testDrawChestOpenedWithPotion() {
        when(chest.isOpened()).thenReturn(true);
        when(chest.getPosition()).thenReturn(new Vector(0,0));
        when(chest.getPotion()).thenReturn(potion);

        chestDisplayer.draw(chest, gui, 0);

        verify(potionDisplayer, times(1)).setDisplacement(any(Vector.class));
        verify(potionDisplayer, times(1)).draw(potion, gui, 0);
    }

    @Test
    void testDrawChestOpenedWithoutPotion() {
        when(chest.isOpened()).thenReturn(true);
        when(chest.getPosition()).thenReturn(new Vector(0,0));
        when(chest.getPotion()).thenReturn(null);

        chestDisplayer.draw(chest, gui, 0);

        verify(potionDisplayer, times(0)).setDisplacement(any(Vector.class));
        verify(potionDisplayer, times(0)).draw(potion, gui, 0);
    }

    @Test
    void getSpriteTest() throws IOException {
        chestDisplayer = new ChestDisplayer();

        Sprite image = chestDisplayer.getSprite();

        assertNotNull(image);
    }
}