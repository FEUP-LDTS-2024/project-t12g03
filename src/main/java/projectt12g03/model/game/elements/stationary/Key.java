package projectt12g03.model.game.elements.stationary;

import projectt12g03.model.game.elements.Element;

import java.awt.*;

public class Key extends Element {
    public Key(int x, int y) {
        super(x,y);
        setHitbox(new Rectangle(0,4,16,9));
    }
}
