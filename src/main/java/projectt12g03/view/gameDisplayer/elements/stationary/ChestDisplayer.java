package projectt12g03.view.gameDisplayer.elements.stationary;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.Sprite;
import projectt12g03.model.game.elements.stationary.Chest;

import java.io.IOException;
import java.util.Map;

public class ChestDisplayer {
    private final Sprite sprite;

    TextColor.RGB invisibilityColor = new TextColor.RGB(142, 135, 145);
    TextColor.RGB speedColor = new TextColor.RGB(0, 168, 230);
    TextColor.RGB rangeColor = new TextColor.RGB(255, 129, 25);
    TextColor.RGB immunityColor = new TextColor.RGB(212, 214, 156);

    public ChestDisplayer() throws IOException {
        this.sprite = new Sprite("sprites/elements/chest/chest.png");
    }

    public void draw(Chest chest, GUI gui) {
        if (!chest.isOpened())
            sprite.draw(gui, chest.getPosition().getX(), chest.getPosition().getY());
        else {
            if (chest.getPotion() != null) {
                String effect = chest.getPotion().getEffect();
                switch (effect) {
                    case "invisibility":
                        gui.drawPixel(chest.getPosition().getX(), chest.getPosition().getY(), invisibilityColor);
                        break;
                    case "speed":
                        gui.drawPixel(chest.getPosition().getX(), chest.getPosition().getY(), speedColor);
                        break;
                    case "range":
                        gui.drawPixel(chest.getPosition().getX(), chest.getPosition().getY(), rangeColor);
                        break;
                    case "immunity":
                        gui.drawPixel(chest.getPosition().getX(), chest.getPosition().getY(), immunityColor);
                        break;

                }
            }
        }
    }
}
