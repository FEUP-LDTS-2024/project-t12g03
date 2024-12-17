package projectt12g03.model.game.elements.stationary;

import projectt12g03.model.game.elements.Element;

public class Floor  extends Element {
    private final char type;

    public Floor(int x, int y, char type) {
        super(x, y,0,0,16,16);
        this.type = type;
    }

    public char getType() { return type; }
}
