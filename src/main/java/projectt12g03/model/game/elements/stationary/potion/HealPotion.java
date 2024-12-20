package projectt12g03.model.game.elements.stationary.potion;

import projectt12g03.model.game.elements.movable.player.Player;

public class HealPotion implements PotionEffectStrategy {
    @Override
    public void applyEffect(Player player) {
        if (player.getHealth() < 4) {
            player.increaseHealth();
        }
    }

    @Override
    public void removeEffect(Player player) {
        // No persistent effect to remove
    }
}
