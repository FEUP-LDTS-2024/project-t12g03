package projectt12g03.view.gameDisplayer.hud.hudComponents;

import projectt12g03.gui.GUI;
import projectt12g03.view.Sprite;

import java.io.IOException;

public class EnergyDisplayer {
    private final int SPACING = 14;

    private final Sprite sprite;

    public EnergyDisplayer() throws IOException {
        sprite = new Sprite("sprites/hud/energy/energy.png");
    }

    public void draw(int energy, GUI gui) {
        for (int i = 0; i < energy / 200 ; i++) {
            sprite.draw(gui, 246 + (i * SPACING), 266);
        }
    }
}