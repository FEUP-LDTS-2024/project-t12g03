package projectt12g03.model.game.elements;

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
