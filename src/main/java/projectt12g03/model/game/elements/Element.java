package projectt12g03.model.game.elements;

import projectt12g03.model.Vector;

import java.awt.*;

public abstract class Element extends Rectangle {
    private Vector position;
    private Rectangle hitbox;

    public Element(int x, int y) {
        this.position = new Vector(x, y);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public Vector getPosition() {
        return this.position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }
}
