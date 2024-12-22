package projectt12g03.view.gameDisplayer.elements.stationary;

import projectt12g03.gui.GUI;
import projectt12g03.view.Sprite;
import projectt12g03.model.game.elements.stationary.Chest;
import projectt12g03.model.game.elements.stationary.Potion;
import projectt12g03.view.gameDisplayer.elements.ElementDisplayer;

import java.io.IOException;

public class ChestDisplayer implements ElementDisplayer<Chest> {
    private final Sprite sprite;

    private PotionDisplayer potionDisplayer;

    public ChestDisplayer() throws IOException {
        this.sprite = new Sprite("sprites/elements/chest/chest.png");

        potionDisplayer = new PotionDisplayer();
    }

    @Override
    public void draw(Chest chest, GUI gui, long frameCount) {
        if (!chest.isOpened()) {
            Sprite image = getSprite();
            image.draw(gui, chest.getPosition().getX(), chest.getPosition().getY());
        }
        else {
            Potion potion = chest.getPotion();
            if (potion != null) {
                potionDisplayer.setDisplacement(chest.getPosition());
                potionDisplayer.draw(potion, gui, frameCount);
            }
        }
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setPotionDisplayer(PotionDisplayer potionDisplayer) {
        this.potionDisplayer = potionDisplayer;
    }
}
