package projectt12g03.view.gameDisplayer.elements.stationary;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.Sprite;
import projectt12g03.model.game.elements.stationary.Chest;
import projectt12g03.model.game.elements.stationary.Potion;

import java.io.IOException;
import java.util.Map;

public class ChestDisplayer {
    private final Sprite sprite;

    private final PotionDisplayer potionDisplayer;

    public ChestDisplayer() throws IOException {
        this.sprite = new Sprite("sprites/elements/chest/chest.png");

        potionDisplayer = new PotionDisplayer();
    }

    public void draw(Chest chest, GUI gui) {
        if (!chest.isOpened())
            sprite.draw(gui, chest.getPosition().getX(), chest.getPosition().getY());
        else {
            Potion potion = chest.getPotion();
            if (potion != null) {
                potionDisplayer.draw(potion, chest.getPosition(), gui);
            }
        }
    }
}
