package projectt12g03.view.gameDisplayer.elements.stationary;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.Sprite;
import projectt12g03.model.game.elements.stationary.Wall;
import projectt12g03.view.gameDisplayer.elements.ElementDisplayer;

import java.io.IOException;
import java.util.List;

public class WallDisplayer implements ElementDisplayer<Wall> {
    private final Sprite sprite;

    public WallDisplayer() throws IOException {
        sprite = new Sprite("sprites/elements/wall/wall2.png");
    }

    @Override
    public void draw(Wall wall, GUI gui, long frameCount) {
        sprite.draw(gui, wall.getPosition().getX(), wall.getPosition().getY());
    }
}
