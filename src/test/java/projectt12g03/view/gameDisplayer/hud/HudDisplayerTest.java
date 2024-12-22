package projectt12g03.view.gameDisplayer.hud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.game.elements.movable.player.Player;
import projectt12g03.model.game.elements.stationary.Potion;
import projectt12g03.model.game.inventory.Inventory;
import projectt12g03.view.Sprite;
import projectt12g03.view.gameDisplayer.hud.hudComponents.EnergyDisplayer;
import projectt12g03.view.gameDisplayer.hud.hudComponents.HealthDisplayer;
import projectt12g03.view.gameDisplayer.hud.hudComponents.InventoryDisplayer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HudDisplayerTest {
    private Player player;
    private Inventory inventory;
    private Potion potion;
    private HudDisplayerHub hudDisplayerHub;
    private InventoryDisplayer inventoryDisplayer;
    private HealthDisplayer healthDisplayer;
    private EnergyDisplayer energyDisplayer;
    private LanternaGui gui;

    @BeforeEach
    void setup() {
        player = mock(Player.class);
        inventory = mock(Inventory.class);
        potion = mock(Potion.class);
        gui = mock(LanternaGui.class);

        hudDisplayerHub = mock(HudDisplayerHub.class);
        inventoryDisplayer = mock(InventoryDisplayer.class);
        energyDisplayer = mock(EnergyDisplayer.class);
        healthDisplayer = mock(HealthDisplayer.class);

        when(player.getInventory()).thenReturn(inventory);
        when(inventory.getSlotSelected()).thenReturn(0);
        when(inventory.getPotion(0)).thenReturn(potion);

        when(hudDisplayerHub.getInventoryDisplayer()).thenReturn(inventoryDisplayer);
        when(hudDisplayerHub.getEnergyDisplayer()).thenReturn(energyDisplayer);
        when(hudDisplayerHub.getHealthDisplayer()).thenReturn(healthDisplayer);
    }

    @Test
    void testDraw() throws IOException {
        HudDisplayer hudDisplayer = new HudDisplayer(hudDisplayerHub);
        when(player.getHealth()).thenReturn(4);
        when(player.getEnergy()).thenReturn(800);

        hudDisplayer.draw(player, gui, 0);

        verify(inventoryDisplayer, times(1)).draw(inventory, gui, 0);
        verify(healthDisplayer, times(1)).draw(4, gui);
        verify(energyDisplayer, times(1)).draw(800, gui);
    }

    @Test
    void getSpriteTest() throws IOException {
        HudDisplayer hudDisplayer = new HudDisplayer(hudDisplayerHub);

        Sprite sprite = hudDisplayer.getSprite();

        assertNotEquals(sprite, null);
    }
}