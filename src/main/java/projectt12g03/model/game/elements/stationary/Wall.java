package projectt12g03.model.game.elements.stationary;

import projectt12g03.model.game.elements.Element;

import java.awt.*;

public class Wall extends Element {

    public Wall(int x, int y) {
        super(x, y);
        setHitbox(new Rectangle(0, 0, 16, 16));
    }

}
