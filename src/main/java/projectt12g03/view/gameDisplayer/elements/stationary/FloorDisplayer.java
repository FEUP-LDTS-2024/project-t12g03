package projectt12g03.view.gameDisplayer.elements.stationary;

import projectt12g03.gui.GUI;
import projectt12g03.view.Sprite;
import projectt12g03.model.game.elements.stationary.Floor;
import projectt12g03.view.gameDisplayer.elements.ElementDisplayer;

import java.io.IOException;


public class FloorDisplayer implements ElementDisplayer<Floor> {
    private final Sprite sprite;

    public FloorDisplayer() throws IOException {
        sprite = new Sprite("sprites/elements/floor/lighter_grass.png");
    }

    @Override
    public void draw(Floor floor, GUI gui, long frameCount) {
        sprite.draw(gui, floor.getPosition().getX(), floor.getPosition().getY());
    }
}
