package projectt12g03.view.gameDisplayer.elements.movable;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.Sprite;
import projectt12g03.model.game.elements.movable.Player;

import java.io.IOException;

public class PlayerDisplayer {
    private final Sprite sprite;

    public PlayerDisplayer() throws IOException {
        sprite = new Sprite("sprites/elements/hero/down/hero_idle_down.png");
    }

    public void draw(Player player, GUI gui) {
        sprite.draw(gui, player.getPosition().getX(), player.getPosition().getY());
    }
}
