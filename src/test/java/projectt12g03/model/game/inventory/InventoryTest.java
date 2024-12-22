package projectt12g03.model.game.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.model.game.elements.stationary.Potion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryTest {

    private Inventory inventory;
    private Potion mockPotion1;
    private Potion mockPotion2;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        mockPotion1 = mock(Potion.class);
        mockPotion2 = mock(Potion.class);
    }

    @Test
    void testAddPotionCanAdd() {
        assertTrue(inventory.isNotFull());

        inventory.addPotion(mockPotion1);
        assertEquals(mockPotion1, inventory.getPotion(0));

        inventory.addPotion(mockPotion2);
        assertEquals(mockPotion2, inventory.getPotion(1));

        inventory.addPotion(mock(Potion.class));
        inventory.addPotion(mock(Potion.class));

        assertFalse(inventory.isNotFull());
    }

    @Test
    void testAddPotionCanNotAdd() {
        inventory.addPotion(mock(Potion.class));
        inventory.addPotion(mock(Potion.class));
        inventory.addPotion(mock(Potion.class));
        inventory.addPotion(mock(Potion.class));

        inventory.addPotion(mockPotion1);
        assertNotEquals(mockPotion1, inventory.getPotion(0));
        assertNotEquals(mockPotion1, inventory.getPotion(1));
        assertNotEquals(mockPotion1, inventory.getPotion(2));
        assertNotEquals(mockPotion1, inventory.getPotion(3));

    }

    @Test
    void testRemovePotion() {
        inventory.addPotion(mockPotion1);
        inventory.addPotion(mockPotion2);

        inventory.removePotion(0);
        assertNull(inventory.getPotion(0));
        assertEquals(mockPotion2, inventory.getPotion(1));

        inventory.removePotion(1);
        assertNull(inventory.getPotion(1));
    }

    @Test
    void testRemovePotionFailCases() {
        inventory.addPotion(mockPotion1);
        inventory.addPotion(mockPotion2);

        inventory.removePotion(3);

        inventory.removePotion(-1);

        inventory.removePotion(6);

        assertEquals(mockPotion1, inventory.getPotion(0));
        assertEquals(mockPotion2, inventory.getPotion(1));
        assertNull(inventory.getPotion(2));
        assertNull(inventory.getPotion(3));
    }

    @Test
    void testGetPotion() {
        inventory.addPotion(mockPotion1);
        assertEquals(mockPotion1, inventory.getPotion(0));
        assertNull(inventory.getPotion(1));
        assertNull(inventory.getPotion(-1));
        assertNull(inventory.getPotion(23));
    }

    @Test
    void testGetSlotSelected() {
        assertEquals(0, inventory.getSlotSelected()); // Default selected slot is 0

        inventory.select(2);
        assertEquals(2, inventory.getSlotSelected()); // Selected slot should now be 2
    }

    @Test
    void testIsNotFull() {
        assertTrue(inventory.isNotFull()); // Empty inventory is not full

        inventory.addPotion(mockPotion1);
        inventory.addPotion(mockPotion2);
        inventory.addPotion(mock(Potion.class));
        inventory.addPotion(mock(Potion.class));

        assertFalse(inventory.isNotFull()); // Inventory is now full
    }

    @Test
    void testSelectAndIsSelected() {
        inventory.select(1);
        assertTrue(inventory.isSelected(1)); // Slot 1 should be selected
        assertFalse(inventory.isSelected(0)); // Slot 0 should not be selected
    }
}
