package projectt12g03.model.game.elements.stationary;

import projectt12g03.model.game.elements.Element;

import java.awt.*;

public class Floor  extends Element {
    private final char type;

    public Floor(int x, int y, char type) {
        super(x, y);
        this.type = type;
        setHitbox(new Rectangle(0,0,16,16));
    }

    public char getType() {
        return type;
    }
}
