package projectt12g03.view.game.elements;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.game.elements.Chest;
import projectt12g03.model.game.elements.Player;

public class ChestDisplayer {
    public void draw(Chest chest, GUI gui) {
        if (chest != null)
            gui.drawText(chest.getPosition(), "C", new TextColor.RGB(138, 85, 66));
    }
}
