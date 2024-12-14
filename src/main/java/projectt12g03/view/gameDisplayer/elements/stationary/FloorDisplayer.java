package projectt12g03.view.gameDisplayer.elements.stationary;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.Sprite;
import projectt12g03.model.game.elements.stationary.Floor;
import projectt12g03.model.game.elements.stationary.Wall;

import java.io.IOException;
import java.util.List;


public class FloorDisplayer {
    private final Sprite sprite;

    public FloorDisplayer() throws IOException {
        sprite = new Sprite("sprites/elements/floor/grass4.png");
    }

    public void draw(List<Floor> floors, GUI gui) {
        for(Floor floor : floors){
            sprite.draw(gui, floor.getPosition().getX(), floor.getPosition().getY());
        }
    }
}
