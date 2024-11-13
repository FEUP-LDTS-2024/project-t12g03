package projectt12g03.types.elements;

import com.googlecode.lanterna.graphics.TextGraphics;
import projectt12g03.types.Vector;

public abstract class Element {
    private Vector position;

    public Element(double x, double y) {
        this.position = new Vector(x, y);
    }

    public Vector getPosition() {
        return this.position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public abstract void draw(TextGraphics screen);
}
