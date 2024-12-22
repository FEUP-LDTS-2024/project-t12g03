package projectt12g03.controller.gameController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projectt12g03.Game;
import projectt12g03.gui.GUI;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.movable.player.Player;
import projectt12g03.model.game.elements.stationary.Potion;
import projectt12g03.model.game.inventory.Inventory;
import projectt12g03.model.game.room.Room;
import projectt12g03.model.game.room.RoomManager;

import java.io.IOException;

import static org.mockito.Mockito.*;

class HudControllerTest {
    private Game game;
    private Player player;
    private Inventory inventory;
    private HudController hudController;

    @BeforeEach
    public void setup() throws IOException {
        Room room = Mockito.mock(Room.class);
        RoomManager roomManager = Mockito.mock(RoomManager.class);
        this.player = Mockito.mock(Player.class);
        this.inventory = Mockito.mock(Inventory.class);
        this.game = Mockito.mock(Game.class);

        this.hudController = new HudController(roomManager);

        Mockito.when(player.getPosition()).thenReturn(new Vector(5, 5));
        Mockito.when(roomManager.getRoom()).thenReturn(room);
        Mockito.when(roomManager.getPlayer()).thenReturn(player);
        Mockito.when(player.getInventory()).thenReturn(inventory);
    }

    @Test
    public void stepOne() throws IOException {
        hudController.step(game, GUI.userInput.ONE, 0);

        Mockito.verify(inventory, times(1)).select(0);
    }

    @Test
    public void stepTwo() throws IOException {
        hudController.step(game, GUI.userInput.TWO, 0);

        Mockito.verify(inventory, times(1)).select(1);
    }

    @Test
    public void stepThree() throws IOException {
        hudController.step(game, GUI.userInput.THREE, 0);

        Mockito.verify(inventory, times(1)).select(2);
    }

    @Test
    public void stepFour() throws IOException {
        hudController.step(game, GUI.userInput.FOUR, 0);

        Mockito.verify(inventory, times(1)).select(3);
    }

    @Test
    public void stepSelectWithPotionAndNoEffect() throws IOException {
        for (int i = 0; i < 4; i++) {
            Potion potion = Mockito.mock(Potion.class);
            when(inventory.getSlotSelected()).thenReturn(i);
            Mockito.when(inventory.getPotion(i)).thenReturn(potion);
            Mockito.when(player.getEffectDuration()).thenReturn(0);

            hudController.step(game, GUI.userInput.SELECT, 0);

        }

        Mockito.verify(inventory, times(4)).getPotion(Mockito.anyInt());
        Mockito.verify(player, times(4)).getEffectDuration();
        Mockito.verify(player, times(4)).consume(Mockito.any(Potion.class));
        Mockito.verify(inventory, times(4)).removePotion(Mockito.anyInt());
    }

    @Test
    public void stepSelectWithPotionAndEffect() throws IOException {
        for (int i = 0; i < 4; i++) {
            Potion potion = Mockito.mock(Potion.class);
            when(inventory.getSlotSelected()).thenReturn(i);
            Mockito.when(inventory.getPotion(i)).thenReturn(potion);
            Mockito.when(player.getEffectDuration()).thenReturn(5);

            hudController.step(game, GUI.userInput.SELECT, 0);
        }

        Mockito.verify(inventory, times(4)).getPotion(Mockito.anyInt());
        Mockito.verify(player, times(4)).getEffectDuration();
        Mockito.verify(player, never()).consume(Mockito.any(Potion.class));
        Mockito.verify(inventory, never()).removePotion(Mockito.anyInt());
    }

    @Test
    public void stepSelectPotionDoesNotExist() throws IOException {
        when(inventory.getSlotSelected()).thenReturn(0);
        Mockito.when(inventory.getPotion(0)).thenReturn(null);

        hudController.step(game, GUI.userInput.SELECT, 0);

        // Verify interactions
        Mockito.verify(inventory, times(1)).getPotion(inventory.getSlotSelected());
        Mockito.verify(player, times(0)).getEffectDuration();
        Mockito.verify(player, times(0)).consume(Mockito.any(Potion.class));
        Mockito.verify(inventory, times(0)).removePotion(Mockito.anyInt());
    }
}
