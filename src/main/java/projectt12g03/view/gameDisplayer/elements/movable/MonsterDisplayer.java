package projectt12g03.view.gameDisplayer.elements.movable;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.Sprite;
import projectt12g03.model.game.elements.movable.hostile.Monster;
import projectt12g03.view.gameDisplayer.elements.ElementDisplayer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonsterDisplayer extends MovableDisplayer implements ElementDisplayer<Monster> {
    private final Map<String, Sprite[][]> monsterMap;

    public MonsterDisplayer() throws IOException {
        monsterMap = new HashMap<>();
        monsterMap.put("IDLE", new Sprite[][]{
                {new Sprite("sprites/elements/monster/up/monster_idle_up.png")},
                {new Sprite("sprites/elements/monster/down/monster_idle_down.png")},
                {new Sprite("sprites/elements/monster/left/monster_idle_left.png")},
                {new Sprite("sprites/elements/monster/right/monster_idle_right.png")}
        });
        monsterMap.put("WALKING", new Sprite[][] {
                { new Sprite("sprites/elements/monster/up/monster_walk_up1.png"), new Sprite("sprites/elements/monster/up/monster_walk_up2.png") },
                { new Sprite("sprites/elements/monster/down/monster_walk_down1.png"), new Sprite("sprites/elements/monster/down/monster_walk_down2.png") },
                { new Sprite("sprites/elements/monster/left/monster_walk_left1.png"), new Sprite("sprites/elements/monster/left/monster_walk_left2.png") },
                { new Sprite("sprites/elements/monster/right/monster_walk_right1.png"), new Sprite("sprites/elements/monster/right/monster_walk_right2.png")}
        });
    }


    @Override
    public void draw(Monster model, GUI gui, long frameCount) {
        Sprite[][] sprites;
        sprites = monsterMap.get(model.getState());
        int walkingCycle = (int) (frameCount % 10) / 5;

        Sprite[] spriteSequence = getSpriteSequence(model.getDirection(), sprites);

        if (model.getState().equals("WALKING")) {
            spriteSequence[walkingCycle].draw(gui, model.getPosition().getX(), model.getPosition().getY());
        } else {
            spriteSequence[0].draw(gui, model.getPosition().getX(), model.getPosition().getY());
        }
    }

}
