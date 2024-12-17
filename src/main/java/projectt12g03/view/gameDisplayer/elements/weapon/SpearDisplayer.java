package projectt12g03.view.gameDisplayer.elements.weapon;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.Vector;
import projectt12g03.view.Sprite;
import projectt12g03.model.game.elements.movable.player.Player;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SpearDisplayer {
    private final Map<String,Sprite> spearMap;

    public SpearDisplayer() throws IOException {
        spearMap = new HashMap<>();

        spearMap.put("UP", new Sprite("sprites/elements/spear/spear_up.png"));
        spearMap.put("DOWN", new Sprite("sprites/elements/spear/spear_down.png"));
        spearMap.put("LEFT", new Sprite("sprites/elements/spear/spear_left.png"));
        spearMap.put("RIGHT", new Sprite("sprites/elements/spear/spear_right.png"));
    }


    public void draw(Player player, GUI gui) {
        int[] displacements = getDisplacements(player.getDirection());
        spearMap.get(player.getDirection()).draw(gui, player.getPosition().getX() + displacements[0], player.getPosition().getY() + displacements[1]);
    }

    private int[] getDisplacements(String direction) {
        return switch (direction) {
            case "UP" -> new int[]{0,2};
            case "DOWN" -> new int[]{0,32};
            case "LEFT" -> new int[]{-15,16};
            case "RIGHT" -> new int[]{15,16};
            default -> new int[]{};
        };
    }
}
