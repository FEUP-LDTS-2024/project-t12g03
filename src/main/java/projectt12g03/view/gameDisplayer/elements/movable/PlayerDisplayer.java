package projectt12g03.view.gameDisplayer.elements.movable;

import projectt12g03.gui.GUI;
import projectt12g03.model.Sprite;
import projectt12g03.model.game.elements.movable.Player;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PlayerDisplayer {
    private final Map<String, Sprite[][]> playerMap;

    public PlayerDisplayer() throws IOException {
        playerMap = new HashMap<>();
        playerMap.put("IDLE", new Sprite[][]{
                {new Sprite("sprites/elements/hero/up/hero_idle_up.png")},
                {new Sprite("sprites/elements/hero/down/hero_idle_down.png")},
                {new Sprite("sprites/elements/hero/left/hero_idle_left.png")},
                {new Sprite("sprites/elements/hero/right/hero_idle_right.png")}
        });
        playerMap.put("WALKING", new Sprite[][] {
                { new Sprite("sprites/elements/hero/up/hero_walk_up1.png"), new Sprite("sprites/elements/hero/up/hero_walk_up2.png") },
                { new Sprite("sprites/elements/hero/down/hero_walk_down1.png"), new Sprite("sprites/elements/hero/down/hero_walk_down2.png") },
                { new Sprite("sprites/elements/hero/left/hero_walk_left1.png"), new Sprite("sprites/elements/hero/left/hero_walk_left2.png") },
                { new Sprite("sprites/elements/hero/right/hero_walk_right1.png"), new Sprite("sprites/elements/hero/right/hero_walk_right2.png")}
        });
    }

    public void draw(Player player, GUI gui, long frameCount) {
        Sprite[][] sprites;
        Sprite[] spriteSequence;
        int cycle = (int) (frameCount % 6) / 3;
        switch (player.getDirection()) {
            case "UP":
                sprites = playerMap.get(player.getState());
                spriteSequence = sprites[0];
                if (player.getState().equals("WALKING")) {
                    spriteSequence[cycle].draw(gui, player.getPosition().getX(), player.getPosition().getY());
                }
                else spriteSequence[0].draw(gui, player.getPosition().getX(), player.getPosition().getY());
                break;
            case "DOWN":
                sprites = playerMap.get(player.getState());
                spriteSequence = sprites[1];
                if (player.getState().equals("WALKING")) {
                    spriteSequence[cycle].draw(gui, player.getPosition().getX(), player.getPosition().getY());
                }
                else spriteSequence[0].draw(gui, player.getPosition().getX(), player.getPosition().getY());
                break;
            case "LEFT":
                sprites = playerMap.get(player.getState());
                spriteSequence = sprites[2];
                if (player.getState().equals("WALKING")) {
                    spriteSequence[cycle].draw(gui, player.getPosition().getX(), player.getPosition().getY());
                }
                else spriteSequence[0].draw(gui, player.getPosition().getX(), player.getPosition().getY());
                break;
            case "RIGHT":
                sprites = playerMap.get(player.getState());
                spriteSequence = sprites[3];
                if (player.getState().equals("WALKING")) {
                    spriteSequence[cycle].draw(gui, player.getPosition().getX(), player.getPosition().getY());
                }
                else spriteSequence[0].draw(gui, player.getPosition().getX(), player.getPosition().getY());
                break;
        }
    }
}
