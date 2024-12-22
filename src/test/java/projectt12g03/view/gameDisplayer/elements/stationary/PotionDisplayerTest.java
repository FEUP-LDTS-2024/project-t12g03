package projectt12g03.view.gameDisplayer.elements.stationary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.gui.GUI;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.stationary.Potion;
import projectt12g03.model.game.elements.stationary.potions.HealPotion;
import projectt12g03.view.Sprite;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PotionDisplayerTest {
    private PotionDisplayer potionDisplayer;
    private GUI gui;
    private Sprite mockedSprite;
    private Potion potion;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        mockedSprite = mock(Sprite.class);
        potion = mock(Potion.class);


        when(potion.getEffectStrategy()).thenReturn(new HealPotion());
    }

    @Test
    void testDrawPotion() throws IOException {
        potionDisplayer = new PotionDisplayer() {
            @Override
            public Sprite getSprite(Potion potion) {
                return mockedSprite;
            }
        };

        potionDisplayer.setDisplacement(new Vector(10, 20));

        potionDisplayer.draw(potion, gui, 0);

        verify(mockedSprite, times(1)).draw(gui, 10, 20);
    }

    @Test
    void testGetSpriteHealPotion() throws IOException {
        potionDisplayer = new PotionDisplayer();

        when(potion.getEffectStrategy()).thenReturn(new HealPotion());

        Sprite sprite = potionDisplayer.getSprite(potion);
        assertNotNull(sprite);
    }
}