package projectt12g03.model.game.elements.stationary.potion;

import projectt12g03.model.game.elements.movable.player.Player;

public interface PotionEffectStrategy {
    void applyEffect(Player player);
    void removeEffect(Player player);
}
