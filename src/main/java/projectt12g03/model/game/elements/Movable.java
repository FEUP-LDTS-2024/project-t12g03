package projectt12g03.model.game.elements;

import projectt12g03.model.Vector;
import projectt12g03.model.game.room.Room;

import java.security.PublicKey;

public abstract class Movable extends Element {
    public Room room;

    public Movable(double x, double y, Room room) {
        super(x,y);
        this.room = room;
    }

    public Vector moveUp() {
        Vector newPosition = new Vector(getPosition().getX(), getPosition().getY() - 1);
        if (canMove(newPosition)) return newPosition;
        return new Vector(getPosition().getX(), getPosition().getY());
    }

    public Vector moveDown() {
        Vector newPosition = new Vector(getPosition().getX(), getPosition().getY() + 1);
        if (canMove(newPosition)) return newPosition;
        return new Vector(getPosition().getX(), getPosition().getY());
    }

    public Vector moveRight() {
        Vector newPosition = new Vector(getPosition().getX() + 1, getPosition().getY());
        if (canMove(newPosition)) return newPosition;
        return new Vector(getPosition().getX(), getPosition().getY());
    }

    public Vector moveLeft() {
        Vector newPosition = new Vector(getPosition().getX() - 1, getPosition().getY());
        if (canMove(newPosition)) return newPosition;
        return new Vector(getPosition().getX(), getPosition().getY());
    }

    public abstract boolean canMove(Vector position);
}
