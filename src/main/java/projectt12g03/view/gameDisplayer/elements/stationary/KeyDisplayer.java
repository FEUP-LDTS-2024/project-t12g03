package projectt12g03.view.gameDisplayer.elements.stationary;

import projectt12g03.gui.GUI;
import projectt12g03.view.Sprite;
import projectt12g03.model.game.elements.stationary.Key;
import projectt12g03.view.gameDisplayer.elements.ElementDisplayer;

import java.io.IOException;

public class KeyDisplayer implements ElementDisplayer<Key> {
    private final Sprite sprite;

    public KeyDisplayer() throws IOException {
        sprite = new Sprite("sprites/elements/key/key1.png");
    }

    @Override
    public void draw(Key key, GUI gui, long frameCount) {
        Sprite image = getSprite();

        if (key != null) {
            image.draw(gui, key.getPosition().getX(), key.getPosition().getY());
        }
    }

    public Sprite getSprite() {
        return sprite;
    }
}
