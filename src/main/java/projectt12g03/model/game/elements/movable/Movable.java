package projectt12g03.model.game.elements.movable;

import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.Element;
import projectt12g03.model.game.room.Room;

public abstract class Movable extends Element {
    public Room room;
    private int velocity;
    private int health;

    public Movable(double x, double y, Room room) {
        super(x,y);
        this.room = room;
    }

    public int getVelocity() { return velocity; }

    public void setVelocity(int v) {
        this.velocity = v;
    }

    public int getHealth(){ return  health; }

    public void setHealth(int h){ this.health = h; }

    public void reducedHealth(){ this.health--; }

    public void increaseHealth(){ this.health++; }

    public Vector moveUp() {
        return new Vector(getPosition().getX(), getPosition().getY() - velocity);
    }

    public Vector moveDown() {
        return new Vector(getPosition().getX(), getPosition().getY() + velocity);
    }

    public Vector moveRight() {
        return new Vector(getPosition().getX() + velocity, getPosition().getY());
    }

    public Vector moveLeft() {
        return new Vector(getPosition().getX() - velocity, getPosition().getY());
    }

    public abstract boolean canMove(Vector position);
}
