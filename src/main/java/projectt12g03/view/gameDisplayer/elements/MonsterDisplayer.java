package projectt12g03.view.gameDisplayer.elements;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.game.elements.Monster;

import java.util.List;

public class MonsterDisplayer {
    public void draw(List<Monster> monsters, GUI gui) {
        for(Monster monster : monsters){
            gui.drawText(monster.getPosition(), "M", new TextColor.RGB(255, 0, 0));
        }
    }
}
