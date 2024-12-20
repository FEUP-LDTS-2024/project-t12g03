package projectt12g03.view.gameDisplayer.hud.hudComponents;

import projectt12g03.gui.GUI;
import projectt12g03.view.Sprite;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.stationary.Potion;
import projectt12g03.model.game.inventory.Inventory;
import projectt12g03.view.gameDisplayer.elements.stationary.PotionDisplayer;

import java.io.IOException;

public class InventoryDisplayer {
    private final int SPACING = 37;

    private final Sprite[] sprite;

    private final PotionDisplayer potionDisplayer;

    public InventoryDisplayer() throws IOException {
        sprite = new Sprite[] { new Sprite("sprites/hud/inventory/inventory_slot_unselected.png"),
                                new Sprite("sprites/hud/inventory/inventory_slot_selected.png")
        };

        potionDisplayer = new PotionDisplayer();
    }

    public void draw(Inventory inventory, GUI gui, long framecount) {
        for (int i = 0; i < 4; i++) {
            sprite[inventory.isSelected(i) ? 1 : 0].draw(gui, 91 + (i * SPACING), 258);
            Potion potion = inventory.getPotion(i);
            potionDisplayer.setDisplacement(new Vector(98 + (i * SPACING), 264));
            if (potion != null) potionDisplayer.draw(potion,gui,framecount);
        }
    }
}
