package projectt12g03.view.game.elements;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.game.elements.Key;
import projectt12g03.model.game.elements.Player;

public class KeyDisplayer {
    public void draw(Key key, GUI gui) {
        if (key != null)
            gui.drawText(key.getPosition(), "K", new TextColor.RGB(255, 221, 0));
    }
}
