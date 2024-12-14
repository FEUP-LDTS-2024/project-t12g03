package projectt12g03.view.game.elements;

import projectt12g03.gui.GUI;
import projectt12g03.model.game.elements.stationary.Wall;

import java.util.List;

public class WallDisplayer {

    public void draw(List<Wall> walls, GUI gui) {
        for(Wall wall : walls){
            gui.drawWall(wall.getPosition());
        }
    }
}
