package projectt12g03.view.gameDisplayer.elements.stationary;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.Sprite;
import projectt12g03.model.game.elements.stationary.Wall;

import java.io.IOException;
import java.util.List;

public class WallDisplayer {
    private final Sprite sprite;

    public WallDisplayer() throws IOException {
        sprite = new Sprite("sprites/elements/wall/wall1.png");
    }

    public void draw(List<Wall> walls, GUI gui) {
        for(Wall wall : walls){
            sprite.draw(gui, wall.getPosition().getX(), wall.getPosition().getY());
        }
    }
}
