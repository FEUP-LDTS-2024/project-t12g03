package projectt12g03.model.game.elements.stationary.potions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.model.game.elements.movable.player.Player;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HealPotionModelTest {

    private HealPotion healPotion;
    private Player mockPlayer;

    @BeforeEach
    void setUp() {
        healPotion = new HealPotion();
        mockPlayer = Mockito.mock(Player.class);
    }

    @Test
    void testApplyEffectWhenHealthIsLessThan4() {
        when(mockPlayer.getHealth()).thenReturn(3);

        healPotion.applyEffect(mockPlayer);

        verify(mockPlayer).increaseHealth();
    }

    @Test
    void testApplyEffectWhenHealthIs4OrMore() {
        when(mockPlayer.getHealth()).thenReturn(4);

        healPotion.applyEffect(mockPlayer);

        verify(mockPlayer, never()).increaseHealth();
    }

    @Test
    void testRemoveEffect() {
        healPotion.removeEffect(mockPlayer);
        verifyNoInteractions(mockPlayer);
    }
}
