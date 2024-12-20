package projectt12g03.view.gameDisplayer.elements.stationary;

import projectt12g03.gui.GUI;
import projectt12g03.model.game.elements.stationary.potion.*;
import projectt12g03.view.Sprite;
import projectt12g03.model.Vector;
import projectt12g03.view.gameDisplayer.elements.ElementDisplayer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PotionDisplayer implements ElementDisplayer<Potion> {
    private final Map<Class<? extends PotionEffectStrategy>, Sprite> potionMap;
    private Vector displacement; //to draw in inventory

    public PotionDisplayer() throws IOException {
        potionMap = new HashMap<>();
        potionMap.put(EnergyPotion.class, new Sprite("sprites/elements/potions/potion_energy.png"));
        potionMap.put(SpeedPotion.class, new Sprite("sprites/elements/potions/potion_speed.png"));
        potionMap.put(HealPotion.class, new Sprite("sprites/elements/potions/potion_heal.png"));
        potionMap.put(ImmunityPotion.class, new Sprite("sprites/elements/potions/potion_immunity.png"));
    }

    @Override
    public void draw(Potion potion, GUI gui, long frameCount) {
        potionMap.get(potion.getEffectStrategy().getClass()).draw(gui, displacement.getX(), displacement.getY());
    }

    public void setDisplacement(Vector displacement) { this.displacement = displacement; }
}
