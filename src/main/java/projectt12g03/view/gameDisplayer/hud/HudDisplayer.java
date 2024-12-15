package projectt12g03.view.gameDisplayer.hud;

import projectt12g03.gui.GUI;
import projectt12g03.model.Sprite;
import projectt12g03.model.game.elements.movable.Player;
import projectt12g03.view.gameDisplayer.hud.hudComponents.EnergyDisplayer;
import projectt12g03.view.gameDisplayer.hud.hudComponents.HealthDisplayer;
import projectt12g03.view.gameDisplayer.hud.hudComponents.InventoryDisplayer;

import java.io.IOException;

public class HudDisplayer {
    private final Sprite sprite;

    private final InventoryDisplayer inventoryDisplayer;
    private final EnergyDisplayer energyDisplayer;
    private final HealthDisplayer healthDisplayer;

    public HudDisplayer() throws IOException {
        sprite = new Sprite("sprites/hud/full_hud.png");

        inventoryDisplayer = new InventoryDisplayer();
        energyDisplayer = new EnergyDisplayer();
        healthDisplayer = new HealthDisplayer();
    }

    public void draw(Player player, GUI gui) {
        sprite.draw(gui, 0, 160);

        inventoryDisplayer.draw(player.getInventory(), gui);
        energyDisplayer.draw(player.getEnergy(), gui);
        healthDisplayer.draw(player.getHealth(), gui);
    }
}
