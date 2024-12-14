package projectt12g03.view.gameDisplayer.elements.stationary;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.game.elements.stationary.Wall;

import java.util.List;

public class WallDisplayer {

    public void draw(List<Wall> walls, GUI gui) {
        for(Wall wall : walls){
            gui.drawText(wall.getPosition(), "#", new TextColor.RGB(255,255,255));
        }
    }
}
