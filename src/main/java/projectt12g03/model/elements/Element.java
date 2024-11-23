package projectt12g03.model.elements;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import projectt12g03.model.Vector;

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

}
