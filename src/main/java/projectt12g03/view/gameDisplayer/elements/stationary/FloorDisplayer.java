package projectt12g03.view.gameDisplayer.elements.stationary;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.game.elements.stationary.Floor;
import projectt12g03.model.game.elements.stationary.Wall;

import java.util.List;


public class FloorDisplayer {
    public void draw(List<Floor> floors, GUI gui) {
        for(Floor floor : floors){
            gui.drawText(floor.getPosition(), " ", new TextColor.RGB(1,1,1));
        }
    }
}
