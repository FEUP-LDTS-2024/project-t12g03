package projectt12g03.model.game.elements.stationary.potions;

import projectt12g03.model.game.elements.movable.player.Player;

public class EnergyPotion implements PotionEffectStrategy {
    @Override
    public void applyEffect(Player player) {
        if (player.getEnergy() < 800) {
            player.increaseEnergy(200);
        }
    }

    @Override
    public void removeEffect(Player player) {
        // No persistent effect to remove
    }
}
