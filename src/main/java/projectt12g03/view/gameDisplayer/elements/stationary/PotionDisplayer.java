package projectt12g03.view.gameDisplayer.elements.stationary;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.Sprite;
import projectt12g03.model.game.elements.stationary.Potion;
import projectt12g03.model.Vector;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PotionDisplayer {
    private final Map<String, Sprite> potionMap;

    public PotionDisplayer() throws IOException {
        potionMap = new HashMap<>();
        potionMap.put("invisibility", new Sprite("sprites/elements/potions/potion_invisibility.png"));
        potionMap.put("speed", new Sprite("sprites/elements/potions/potion_speed.png"));
        potionMap.put("range", new Sprite("sprites/elements/potions/potion_range.png"));
        potionMap.put("immunity", new Sprite("sprites/elements/potions/potion_immunity.png"));
    }

    public void draw(Potion potion, Vector position,GUI gui) {
        potionMap.get(potion.getEffect()).draw(gui, position.getX(), position.getY());
    }

}
