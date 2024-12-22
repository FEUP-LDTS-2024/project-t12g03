package projectt12g03.view.gameDisplayer.hud;

import projectt12g03.view.gameDisplayer.hud.hudComponents.EnergyDisplayer;
import projectt12g03.view.gameDisplayer.hud.hudComponents.HealthDisplayer;
import projectt12g03.view.gameDisplayer.hud.hudComponents.InventoryDisplayer;

import java.io.IOException;

public class HudDisplayerHub {
    private final InventoryDisplayer inventoryDisplayer;
    private final HealthDisplayer healthDisplayer;
    private final EnergyDisplayer energyDisplayer;

    public HudDisplayerHub() throws IOException {
        inventoryDisplayer = new InventoryDisplayer();
        healthDisplayer = new HealthDisplayer();
        energyDisplayer = new EnergyDisplayer();
    }

    public InventoryDisplayer getInventoryDisplayer() {
        return inventoryDisplayer;
    }

    public HealthDisplayer getHealthDisplayer() {
        return healthDisplayer;
    }

    public EnergyDisplayer getEnergyDisplayer() {
        return energyDisplayer;
    }
}
