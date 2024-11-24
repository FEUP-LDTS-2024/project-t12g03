package projectt12g03.view.game.elements;

import projectt12g03.gui.GUI;
import projectt12g03.model.game.elements.Wall;
import projectt12g03.view.ScreenDisplayer;

import java.io.IOException;
import java.util.List;

public class WallDisplayer extends ElementDisplayer<Wall> {

    @Override
    public void draw(List<Wall> walls, GUI gui) {
        for(Wall wall : walls){
            gui.drawWall(wall.getPosition());
        }
    }
}
