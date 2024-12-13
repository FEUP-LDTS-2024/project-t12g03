package projectt12g03.model.game.elements;

import projectt12g03.model.Vector;
import projectt12g03.model.game.room.Room;

public abstract class Movable extends Element {
    public Room room;
    private int velocity;
    private int health;

    public Movable(double x, double y, Room room) {
        super(x,y);
        this.room = room;
    }

    public int getVelocity() { return velocity;}

    public void setVelocity(int v) {
        this.velocity = v;
    }

    public void setHealth(int h){ this.health = h;}

    public int getHealth(){ return  health;}

    public void reduceHealth(){ this.health--;}

    public void increaseHealth(){ this.health++;}

    public Vector moveUp() {
        Vector newPosition = new Vector(getPosition().getX(), getPosition().getY() - velocity);
        if (canMove(newPosition)) return newPosition;
        return new Vector(getPosition().getX(), getPosition().getY());
    }

    public Vector moveDown() {
        Vector newPosition = new Vector(getPosition().getX(), getPosition().getY() + velocity);
        if (canMove(newPosition)) return newPosition;
        return new Vector(getPosition().getX(), getPosition().getY());
    }

    public Vector moveRight() {
        Vector newPosition = new Vector(getPosition().getX() + velocity, getPosition().getY());
        if (canMove(newPosition)) return newPosition;
        return new Vector(getPosition().getX(), getPosition().getY());
    }

    public Vector moveLeft() {
        Vector newPosition = new Vector(getPosition().getX() - velocity, getPosition().getY());
        if (canMove(newPosition)) return newPosition;
        return new Vector(getPosition().getX(), getPosition().getY());
    }

    public abstract boolean canMove(Vector position);
}
