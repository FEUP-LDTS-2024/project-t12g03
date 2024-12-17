package projectt12g03.controller.gameController;

import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.GUI;
import projectt12g03.model.game.elements.movable.player.Player;
import projectt12g03.model.game.elements.stationary.Potion;
import projectt12g03.model.game.inventory.Inventory;
import projectt12g03.model.game.room.RoomManager;

import java.io.IOException;

public class HudController extends Controller<RoomManager> {

    public HudController(RoomManager model) {
        super(model);
    }

    @Override
    public void step(Game game, GUI.userInput action, long time) throws IOException {
        Inventory inventory = getModel().getPlayer().getInventory();
        Player player = getModel().getPlayer();

        if (player.getEnergy() < 800) player.increaseEnergy(1);

        switch (action) {
            case ONE:
                inventory.select(0);
                break;
            case TWO:
                inventory.select(1);
                break;
            case THREE:
                inventory.select(2);
                break;
            case FOUR:
                inventory.select(3);
                break;
            case SELECT:
                Potion potion = inventory.getPotion(inventory.getSlotSelected());
                if (potion != null && player.getEffectDuration() == 0) {
                    player.consume(potion);
                    inventory.removePotion(inventory.getSlotSelected());
                }
                break;
        }
    }
}
