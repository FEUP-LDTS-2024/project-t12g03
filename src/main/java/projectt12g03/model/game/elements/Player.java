package projectt12g03.model.game.elements;

import projectt12g03.model.Vector;
import projectt12g03.model.game.room.Room;

public class Player extends Element {
    private Room room;

    public Player(double x, double y, Room room) {
        super(x, y);

        this.room = room;
    }

    public void setRoom(Room room) { this.room = room; }

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

    public boolean canMove(Vector position) {
        return room.checkCollisions(position);
    }
}

