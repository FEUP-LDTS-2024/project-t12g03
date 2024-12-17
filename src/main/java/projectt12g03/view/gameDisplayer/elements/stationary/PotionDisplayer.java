package projectt12g03.view.gameDisplayer.elements.stationary;

import projectt12g03.gui.GUI;
import projectt12g03.view.Sprite;
import projectt12g03.model.game.elements.stationary.Potion;
import projectt12g03.model.Vector;
import projectt12g03.view.gameDisplayer.elements.ElementDisplayer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PotionDisplayer implements ElementDisplayer<Potion> {
    private final Map<String, Sprite> potionMap;
    private Vector displacement; //to draw in inventory

    public PotionDisplayer() throws IOException {
        potionMap = new HashMap<>();
        potionMap.put("energy", new Sprite("sprites/elements/potions/potion_energy.png"));
        potionMap.put("speed", new Sprite("sprites/elements/potions/potion_speed.png"));
        potionMap.put("heal", new Sprite("sprites/elements/potions/potion_heal.png"));
        potionMap.put("immunity", new Sprite("sprites/elements/potions/potion_immunity.png"));
    }

    @Override
    public void draw(Potion potion, GUI gui, long frameCount) {
        potionMap.get(potion.getEffect()).draw(gui, displacement.getX(), displacement.getY());
    }

    public void setDisplacement(Vector displacement) { this.displacement = displacement; }
}
