package projectt12g03.view.gameDisplayer.elements.stationary;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.Sprite;
import projectt12g03.model.game.elements.stationary.Key;

import java.io.IOException;

public class KeyDisplayer {
    private final Sprite sprite;

    public KeyDisplayer() throws IOException {
        sprite = new Sprite("sprites/elements/key/key1.png");
    }

    public void draw(Key key, GUI gui) {
        if (key != null)
            sprite.draw(gui, key.getPosition().getX(), key.getPosition().getY());
    }
}
