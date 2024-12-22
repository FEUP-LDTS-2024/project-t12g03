package projectt12g03.model.game.elements.stationary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.model.game.elements.movable.player.Player;
import projectt12g03.model.game.elements.stationary.potions.PotionEffectStrategy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PotionModelTest {

    private PotionEffectStrategy mockEffectStrategy;
    private Player mockPlayer;
    private Potion potion;

    @BeforeEach
    void setUp() {
        mockEffectStrategy = Mockito.mock(PotionEffectStrategy.class);
        mockPlayer = Mockito.mock(Player.class);
        potion = new Potion(10, 20, mockEffectStrategy);
    }

    @Test
    void testConstructorInitialization() {
        assertEquals(10, potion.getPosition().getX());
        assertEquals(20, potion.getPosition().getY());
        assertEquals(1, potion.getHitbox().getX());
        assertEquals(7, potion.getHitbox().getY());
        assertEquals(14, potion.getHitbox().getWidth());
        assertEquals(9, potion.getHitbox().getHeight());
        assertEquals(mockEffectStrategy, potion.getEffectStrategy());
    }

    @Test
    void testApplyEffect() {
        potion.applyEffect(mockPlayer);
        verify(mockEffectStrategy).applyEffect(mockPlayer);
    }

    @Test
    void testRemoveEffect() {
        potion.removeEffect(mockPlayer);
        verify(mockEffectStrategy,times(1)).removeEffect(mockPlayer);
    }

    @Test
    void testImmunityCountdown() {
        assertEquals(15, potion.getImmunity());
        potion.reduceImmunity();
        assertEquals(14, potion.getImmunity());
    }
}
