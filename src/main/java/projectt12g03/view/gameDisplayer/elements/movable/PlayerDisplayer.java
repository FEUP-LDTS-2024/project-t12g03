package projectt12g03.view.gameDisplayer.elements.movable;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.game.elements.movable.Player;

public class PlayerDisplayer {

    public void draw(Player player, GUI gui) {
        gui.drawText(player.getPosition(), "P", new TextColor.RGB(85,201,81));
    }
}
