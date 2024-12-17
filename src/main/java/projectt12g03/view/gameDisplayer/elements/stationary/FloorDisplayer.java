package projectt12g03.view.gameDisplayer.elements.stationary;

import projectt12g03.gui.GUI;
import projectt12g03.view.Sprite;
import projectt12g03.model.game.elements.stationary.Floor;
import projectt12g03.view.gameDisplayer.elements.ElementDisplayer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class FloorDisplayer implements ElementDisplayer<Floor> {
    private final Map<Character, Sprite> sprite;

    public FloorDisplayer() throws IOException {
        sprite = new HashMap<>();

        sprite.put('d', new Sprite("sprites/elements/floor/lighter_grass.png"));
        sprite.put('e', new Sprite("sprites/elements/floor/le_portal2.png"));
    }

    @Override
    public void draw(Floor floor, GUI gui, long frameCount) {
        sprite.get(floor.getType()).draw(gui, floor.getPosition().getX(), floor.getPosition().getY());
    }
}
