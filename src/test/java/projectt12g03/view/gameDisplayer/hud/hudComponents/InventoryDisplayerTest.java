package projectt12g03.view.gameDisplayer.hud.hudComponents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.stationary.Potion;
import projectt12g03.model.game.inventory.Inventory;
import projectt12g03.view.Sprite;
import projectt12g03.view.gameDisplayer.elements.stationary.PotionDisplayer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryDisplayerTest {
    private Inventory inventory;
    private Potion potion;
    private InventoryDisplayer inventoryDisplayer;
    private PotionDisplayer potionDisplayer;
    private LanternaGui gui;

    @BeforeEach
    void setup() throws IOException {
        inventory = mock(Inventory.class);
        potion = mock(Potion.class);
        gui = mock(LanternaGui.class);

        potionDisplayer = mock(PotionDisplayer.class);

        inventoryDisplayer = spy(new InventoryDisplayer());
        inventoryDisplayer.setPotionDisplayer(potionDisplayer);
    }

    @Test
    void testDrawWithPotions() {
        for (int i = 0; i < 4; i++) {
            when(inventory.isSelected(i)).thenReturn(true);
            when(inventory.getPotion(i)).thenReturn(potion);
        }

        inventoryDisplayer.draw(inventory, gui,0);

        verify(potionDisplayer, times(4)).setDisplacement(any(Vector.class));
        verify(potionDisplayer, times(4)).draw(potion, gui, 0);
    }

    @Test
    void testDrawWithoutPotions() {
        Sprite image = mock(Sprite.class);

        for (int i = 0; i < 4; i++) {
            when(inventory.isSelected(i)).thenReturn(true);
            when(inventory.getPotion(i)).thenReturn(null);
            doReturn(image).when(inventoryDisplayer).getSprite(inventory, i);
        }

        inventoryDisplayer.draw(inventory, gui, 0);

        for (int i = 0; i < 4; i++) {
            assertEquals(true, inventory.isSelected(i));
            verify(image).draw(gui, 91 + (i * 37), 258);
            verify(potionDisplayer, times(1)).setDisplacement(new Vector(98 + (i * 37), 264));
        }
        verify(potionDisplayer, times(0)).draw(potion, gui, 0);
    }

    @Test
    void testDrawNothingSelected() {
        for (int i = 0; i < 4; i++) {
            when(inventory.isSelected(i)).thenReturn(false);
            when(inventory.getPotion(i)).thenReturn(potion);
        }


        inventoryDisplayer.draw(inventory, gui, 0);

        verify(potionDisplayer, times(4)).setDisplacement(any(Vector.class));
        verify(potionDisplayer, times(4)).draw(potion, gui, 0);
    }
}