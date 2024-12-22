package projectt12g03.view.gameDisplayer.hud;

import projectt12g03.gui.GUI;
import projectt12g03.view.Sprite;
import projectt12g03.model.game.elements.movable.player.Player;
import projectt12g03.view.gameDisplayer.hud.hudComponents.EnergyDisplayer;
import projectt12g03.view.gameDisplayer.hud.hudComponents.HealthDisplayer;
import projectt12g03.view.gameDisplayer.hud.hudComponents.InventoryDisplayer;

import java.io.IOException;

public class HudDisplayer {
    private final Sprite sprite;

    private final InventoryDisplayer inventoryDisplayer;
    private final EnergyDisplayer energyDisplayer;
    private final HealthDisplayer healthDisplayer;

    public HudDisplayer(HudDisplayerHub hudDisplayerHub) throws IOException {
        sprite = new Sprite("sprites/hud/full_hud.png");

        inventoryDisplayer = hudDisplayerHub.getInventoryDisplayer();
        energyDisplayer = hudDisplayerHub.getEnergyDisplayer();
        healthDisplayer = hudDisplayerHub.getHealthDisplayer();
    }

    public void draw(Player player, GUI gui, long framecount) {
        sprite.draw(gui, 0, 256);

        inventoryDisplayer.draw(player.getInventory(), gui, framecount);
        energyDisplayer.draw(player.getEnergy(), gui);
        healthDisplayer.draw(player.getHealth(), gui);
    }

    public Sprite getSprite() {
        return sprite;
    }
}
