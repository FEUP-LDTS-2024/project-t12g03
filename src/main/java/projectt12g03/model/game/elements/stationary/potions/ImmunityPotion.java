package projectt12g03.model.game.elements.stationary.potions;

import projectt12g03.model.game.elements.movable.player.Player;

public class ImmunityPotion implements PotionEffectStrategy {
    private final int IMMUNITY_DURATION = 300;

    @Override
    public void applyEffect(Player player) {
        player.setImmunity(IMMUNITY_DURATION);
        player.setEffectDuration(IMMUNITY_DURATION);
    }

    @Override
    public void removeEffect(Player player) {

    }
}
