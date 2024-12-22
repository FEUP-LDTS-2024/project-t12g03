package projectt12g03.model.game.elements.stationary.potions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.model.game.elements.movable.player.Player;

import static org.mockito.Mockito.*;

class ImmunityPotionModelTest {

    private ImmunityPotion immunityPotion;
    private Player mockPlayer;

    @BeforeEach
    void setUp() {
        immunityPotion = new ImmunityPotion();
        mockPlayer = Mockito.mock(Player.class);
    }

    @Test
    void testApplyEffect() {
        immunityPotion.applyEffect(mockPlayer);

        verify(mockPlayer).setImmunity(300);
        verify(mockPlayer).setEffectDuration(300);
    }

    @Test
    void testRemoveEffect() {
        immunityPotion.removeEffect(mockPlayer);

        verifyNoInteractions(mockPlayer);
    }
}