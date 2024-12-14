package projectt12g03.view.gameDisplayer.elements.movable;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.Sprite;
import projectt12g03.model.game.elements.movable.Monster;

import java.io.IOException;
import java.util.List;

public class MonsterDisplayer {
    private final Sprite sprite;

    public MonsterDisplayer() throws IOException {
        sprite = new Sprite("sprites/elements/monster/down/monster_idle_down.png");
    }

    public void draw(List<Monster> monsters, GUI gui) {
        for(Monster monster : monsters){
            sprite.draw(gui, monster.getPosition().getX(), monster.getPosition().getY());
        }
    }
}
