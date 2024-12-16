package projectt12g03.view.gameDisplayer.elements.movable;

import projectt12g03.gui.GUI;
import projectt12g03.model.Sprite;
import projectt12g03.model.game.elements.movable.hostile.Monster;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonsterDisplayer {
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

    public void draw(List<Monster> monsters, GUI gui, long frameCount) {
        for(Monster monster : monsters){
            Sprite[][] sprites;
            Sprite[] spriteSequence;
            switch (monster.getDirection()) {
                case "UP":
                    sprites = monsterMap.get(monster.getState());
                    spriteSequence = sprites[0];
                    if (monster.getState().equals("WALKING")) {
                        int cycle = (int) (frameCount % 10) / 5;
                        spriteSequence[cycle].draw(gui, monster.getPosition().getX(), monster.getPosition().getY());
                    }
                    else spriteSequence[0].draw(gui, monster.getPosition().getX(), monster.getPosition().getY());
                    break;
                case "DOWN":
                    sprites = monsterMap.get(monster.getState());
                    spriteSequence = sprites[1];
                    if (monster.getState().equals("WALKING")) {
                        int cycle = (int) (frameCount % 10) / 5;
                        spriteSequence[cycle].draw(gui, monster.getPosition().getX(), monster.getPosition().getY());
                    }
                    else spriteSequence[0].draw(gui, monster.getPosition().getX(), monster.getPosition().getY());
                    break;
                case "LEFT":
                    sprites = monsterMap.get(monster.getState());
                    spriteSequence = sprites[2];
                    if (monster.getState().equals("WALKING")) {
                        int cycle = (int) (frameCount % 10) / 5;
                        spriteSequence[cycle].draw(gui, monster.getPosition().getX(), monster.getPosition().getY());
                    }
                    else spriteSequence[0].draw(gui, monster.getPosition().getX(), monster.getPosition().getY());
                    break;
                case "RIGHT":
                    sprites = monsterMap.get(monster.getState());
                    spriteSequence = sprites[3];
                    if (monster.getState().equals("WALKING")) {
                        int cycle = (int) (frameCount % 10) / 5;
                        spriteSequence[cycle].draw(gui, monster.getPosition().getX(), monster.getPosition().getY());
                    }
                    else spriteSequence[0].draw(gui, monster.getPosition().getX(), monster.getPosition().getY());
                    break;

            }
        }
    }
}
