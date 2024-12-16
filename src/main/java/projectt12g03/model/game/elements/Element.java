package projectt12g03.model.game.elements;

import projectt12g03.model.Vector;

import java.awt.*;

public abstract class Element extends Rectangle {
    private Vector position;
    private Rectangle hitbox;

    public Element(int x, int y, int dx, int dy, int width, int height) {
        this.position = new Vector(x, y);
        hitbox = new Rectangle(dx, dy, width, height);
    }

    public Rectangle getHitbox() { return hitbox; }

    public Vector getPosition() {
        return this.position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

}
