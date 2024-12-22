package projectt12g03.model.game.elements.stationary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChestModelTest {

    private Chest chest;
    private Potion mockPotion;

    @BeforeEach
    void setUp() {
        mockPotion = mock(Potion.class);
        chest = new Chest(10, 10);
    }

    @Test
    void testConstructorInitialization() {
        assertFalse(chest.isUnlockable());
        assertFalse(chest.isOpened());
        assertNotNull(chest.getPotion());
    }

    @Test
    void testFoundKey() {
        assertFalse(chest.isUnlockable());
        chest.foundKey();
        assertTrue(chest.isUnlockable());
    }

    @Test
    void testOpen() {
        assertFalse(chest.isOpened());
        chest.open();
        assertTrue(chest.isOpened());
    }

    @Test
    void testPotionAssignment() {
        chest.setPotion(mockPotion);
        assertEquals(mockPotion, chest.getPotion());
    }

    @Test
    void testGetPotionFromInitialization() {
        Potion potion = chest.getPotion();
        assertNotNull(potion);
    }
}
