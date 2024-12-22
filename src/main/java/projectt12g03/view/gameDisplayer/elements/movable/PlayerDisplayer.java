package projectt12g03.view.gameDisplayer.elements.movable;

import projectt12g03.gui.GUI;
import projectt12g03.view.Sprite;
import projectt12g03.model.game.elements.movable.player.Player;
import projectt12g03.view.gameDisplayer.elements.ElementDisplayer;
import projectt12g03.view.gameDisplayer.elements.weapon.SpearDisplayer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PlayerDisplayer extends MovableDisplayer implements ElementDisplayer<Player> {
    private final Map<String, Sprite[][]> playerMap;

    private SpearDisplayer spearDisplayer;

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
        playerMap.put("RUNNING", new Sprite[][] {
                { new Sprite("sprites/elements/hero/up/hero_walk_up1.png"), new Sprite("sprites/elements/hero/up/hero_walk_up2.png") },
                { new Sprite("sprites/elements/hero/down/hero_walk_down1.png"), new Sprite("sprites/elements/hero/down/hero_walk_down2.png") },
                { new Sprite("sprites/elements/hero/left/hero_walk_left1.png"), new Sprite("sprites/elements/hero/left/hero_walk_left2.png") },
                { new Sprite("sprites/elements/hero/right/hero_walk_right1.png"), new Sprite("sprites/elements/hero/right/hero_walk_right2.png")}
        });
        playerMap.put("ATTACKING", new Sprite[][]{
                {new Sprite("sprites/elements/hero/up/hero_attacking_up.png")},
                {new Sprite("sprites/elements/hero/down/hero_attacking_down.png")},
                {new Sprite("sprites/elements/hero/left/hero_attacking_left.png")},
                {new Sprite("sprites/elements/hero/right/hero_attacking_right.png")}
        });

        spearDisplayer = new SpearDisplayer();
    }

    @Override
    public void draw(Player player, GUI gui, long frameCount) {
        Sprite[][] sprites;
        sprites = playerMap.get(player.getState());
        int walkingCycle = (int) (frameCount % 6) / 3;
        int sprintCyle = (int) (frameCount % 2);

        Sprite[] spriteSequence = getSpriteSequence(player.getDirection(), sprites);

        switch (player.getState()) {
            case "WALKING" ->
                    spriteSequence[walkingCycle].draw(gui, player.getPosition().getX(), player.getPosition().getY());
            case "RUNNING" ->
                    spriteSequence[sprintCyle].draw(gui, player.getPosition().getX(), player.getPosition().getY());
            case "ATTACKING" -> {
                spearDisplayer.draw(player, gui);
                spriteSequence[0].draw(gui, player.getPosition().getX(), player.getPosition().getY());
            }
            default -> spriteSequence[0].draw(gui, player.getPosition().getX(), player.getPosition().getY());
        }

    }

    public void setSpearDisplayer(SpearDisplayer spearDisplayer) {
        this.spearDisplayer = spearDisplayer;
    }

    public Map<String, Sprite[][]> getPlayerMap() {
        return playerMap;
    }
}
