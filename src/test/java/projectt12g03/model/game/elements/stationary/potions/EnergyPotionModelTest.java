package projectt12g03.model.game.elements.stationary.potions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.model.game.elements.movable.player.Player;

import static org.mockito.Mockito.*;

class EnergyPotionModelTest {

    private EnergyPotion energyPotion;
    private Player mockPlayer;

    @BeforeEach
    void setUp() {
        energyPotion = new EnergyPotion();
        mockPlayer = Mockito.mock(Player.class);
    }

    @Test
    void testApplyEffectWhenEnergyIsLessThan800() {
        when(mockPlayer.getEnergy()).thenReturn(600);

        energyPotion.applyEffect(mockPlayer);

        verify(mockPlayer).increaseEnergy(200);
    }

    @Test
    void testApplyEffectWhenEnergyIs800OrMore() {
        when(mockPlayer.getEnergy()).thenReturn(800);

        energyPotion.applyEffect(mockPlayer);

        verify(mockPlayer, never()).increaseEnergy(anyInt());
    }

    @Test
    void testRemoveEffect() {
        energyPotion.removeEffect(mockPlayer);
        verifyNoInteractions(mockPlayer);
    }
}
