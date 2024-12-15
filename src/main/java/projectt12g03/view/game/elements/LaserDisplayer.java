package projectt12g03.view.game.elements;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.stationary.Laser;

import java.util.List;

public class LaserDisplayer {
    public void draw(List<Laser> lasers, GUI gui) {
        for(Laser laser : lasers){
            switch (laser.getDirection()){
                case "RIGHT":
                    gui.drawText(laser.getPosition(), "L", new TextColor.RGB(255, 0, 0));
                    if (laser.getActive() > 0){
                        for (int i = 1; i <= laser.getRange();  i++){
                            gui.drawText(new Vector(laser.getPosition().getX() + i, laser.getPosition().getY()), "-", new TextColor.RGB(255, 0, 0));
                        }
                    }
                    break;
                case "LEFT":
                    gui.drawText(laser.getPosition(), "⅃", new TextColor.RGB(255, 0, 0));
                    if (laser.getActive() > 0){
                        for (int i = 1; i <= laser.getRange();  i++){
                            gui.drawText(new Vector(laser.getPosition().getX() - i, laser.getPosition().getY()), "-", new TextColor.RGB(255, 0, 0));
                        }
                    }
                    break;
            }
        }
    }
}
