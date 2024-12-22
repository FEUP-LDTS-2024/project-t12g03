package projectt12g03.view.gameDisplayer.hud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.view.gameDisplayer.hud.hudComponents.EnergyDisplayer;
import projectt12g03.view.gameDisplayer.hud.hudComponents.HealthDisplayer;
import projectt12g03.view.gameDisplayer.hud.hudComponents.InventoryDisplayer;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class HudDisplayerHubTest {

    private HudDisplayerHub hudDisplayerHub;

    @BeforeEach
    void setup() throws IOException {
        hudDisplayerHub = new HudDisplayerHub();
    }

    @Test
    void testHudDisplayerHubConstructor() {
        assertNotNull(hudDisplayerHub);
    }

    @Test
    void testGetInventoryDisplayer() {
        InventoryDisplayer inventoryDisplayer = hudDisplayerHub.getInventoryDisplayer();
        assertNotNull(inventoryDisplayer);
        assertEquals(InventoryDisplayer.class, inventoryDisplayer.getClass());
    }

    @Test
    void testGetHealthDisplayer() {
        HealthDisplayer healthDisplayer = hudDisplayerHub.getHealthDisplayer();
        assertNotNull(healthDisplayer);
        assertEquals(HealthDisplayer.class, healthDisplayer.getClass());
    }

    @Test
    void testGetEnergyDisplayer() {
        EnergyDisplayer energyDisplayer = hudDisplayerHub.getEnergyDisplayer();
        assertNotNull(energyDisplayer);
        assertEquals(EnergyDisplayer.class, energyDisplayer.getClass());
    }
}