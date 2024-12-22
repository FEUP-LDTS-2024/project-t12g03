package projectt12g03.view.gameDisplayer.hud.hudComponents;

import projectt12g03.gui.GUI;
import projectt12g03.view.Sprite;

import java.io.IOException;

public class HealthDisplayer {
    private final int SPACING = 16;

    private final Sprite sprite;

    public HealthDisplayer() throws IOException {
        sprite = new Sprite("sprites/hud/health/heart1.png");
    }

    public void draw(int health, GUI gui) {
        for (int i = 0; i < health; i++) {
            Sprite image = getSprite();
            image.draw(gui, 19 + (i * SPACING), 266);
        }
    }

    public Sprite getSprite() {
        return sprite;
    }
}
