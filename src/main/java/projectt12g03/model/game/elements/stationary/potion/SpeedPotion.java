package projectt12g03.model.game.elements.stationary.potion;

import projectt12g03.model.game.elements.movable.player.Player;

public class SpeedPotion implements PotionEffectStrategy {
    private static final int SPEED_BOOST = 2;
    private static final int SPEED_BOOST_DURATION = 150;

    @Override
    public void applyEffect(Player player) {
        player.setSpeedBoost(SPEED_BOOST);
        player.setEffectDuration(SPEED_BOOST_DURATION);
    }

    @Override
    public void removeEffect(Player player) {
        player.setSpeedBoost(1);
    }
}

