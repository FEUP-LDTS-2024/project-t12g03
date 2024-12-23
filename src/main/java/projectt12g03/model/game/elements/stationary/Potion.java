package projectt12g03.model.game.elements.stationary;

import projectt12g03.model.game.elements.Element;
import projectt12g03.model.game.elements.movable.player.Player;
import projectt12g03.model.game.elements.stationary.potions.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Potion extends Element {
    private final PotionEffectStrategy effectStrategy;

    private int immunity = 15;

    public Potion(int x, int y, PotionEffectStrategy effectStrategy) {
        super(x,y);
        this.effectStrategy = effectStrategy;
        setHitbox(new Rectangle(1,7,14,9));
    }

    public void applyEffect(Player player) {
        effectStrategy.applyEffect(player);
    }

    public void removeEffect(Player player) {
        effectStrategy.removeEffect(player);
    }

    public static Potion createRandomPotion(int x, int y) {
        Map<Integer, PotionEffectStrategy> effects = new HashMap<>();
        effects.put(0, new EnergyPotion());
        effects.put(1, new SpeedPotion());
        effects.put(2, new HealPotion());
        effects.put(3, new ImmunityPotion());

        int randomEffect = new Random().nextInt(effects.size());
        return new Potion(x, y, effects.get(randomEffect));
    }

    public PotionEffectStrategy getEffectStrategy() {
        return effectStrategy;
    }

    public int getImmunity() {
        return immunity;
    }

    public void reduceImmunity() {
        immunity--;
    }
}
