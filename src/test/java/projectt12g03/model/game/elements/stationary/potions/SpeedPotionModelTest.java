package projectt12g03.model.game.elements.stationary.potions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.model.game.elements.movable.player.Player;

import static org.mockito.Mockito.*;

class SpeedPotionModelTest {

    private SpeedPotion speedPotion;
    private Player mockPlayer;

    @BeforeEach
    void setUp() {
        speedPotion = new SpeedPotion();
        mockPlayer = Mockito.mock(Player.class);
    }

    @Test
    void testApplyEffect() {
        speedPotion.applyEffect(mockPlayer);

        verify(mockPlayer).setSpeedBoost(2);
        verify(mockPlayer).setEffectDuration(150);
    }

    @Test
    void testRemoveEffect() {
        speedPotion.removeEffect(mockPlayer);

        verify(mockPlayer).setSpeedBoost(1);
    }
}
