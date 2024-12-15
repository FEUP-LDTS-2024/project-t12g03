package projectt12g03.model.game.inventory;

import projectt12g03.model.game.elements.stationary.Potion;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Potion> potions;
    private int slotSelected = 0;

    public Inventory() {
        potions = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            potions.add(null);
        }
    }

    public void addPotion(Potion potion) {
        for (int i = 0; i < potions.size(); i++) {
            if (potions.get(i) == null) {
                potions.set(i, potion);
                break;
            }
        }
    }

    public Potion removePotion(int index) {
        if (index >= 0 && index < potions.size() && potions.get(index) != null) {
            Potion removedPotion = potions.get(index);
            potions.set(index, null);
            return removedPotion;
        }
        return null;
    }

    public Potion getPotion(int index) {
        if (index >= 0 && index < potions.size()) {
            return potions.get(index);
        }
        return null;
    }

    public int getSlotSelected() { return slotSelected; }

    public boolean isNotFull() {
        return potions.contains(null);
    }

    public void select(int slot) { slotSelected = slot; }

    public boolean isSelected(int slot) { return slotSelected == slot; }
}
