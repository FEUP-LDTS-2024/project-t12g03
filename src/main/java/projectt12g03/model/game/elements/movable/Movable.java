package projectt12g03.model.game.elements.movable;

import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.Element;
import projectt12g03.model.game.room.Room;

public abstract class Movable extends Element {
    public Room room;
    private int velocity;
    private int health;
    private int speed_boost;
    private String direction;
    private String state;

    public Movable(int x, int y, Room room) {
        super(x,y,2,18,12,14);
        this.room = room;
    }

    public int getVelocity() { return velocity; }

    public void setVelocity(int v) {
        this.velocity = v;
    }

    public int getHealth() { return  health; }

    public String getDirection() { return direction; }

    public String getState() { return state; }

    public void setDirection(String direction) { this.direction = direction; }

    public void setState(String state) { this.state = state; }

    public void setHealth(int h){ this.health = h; }

    public void setSpeedBoost(int speedBoost) { this.speed_boost = speedBoost; }

    public void reduceHealth() { this.health--; }

    public void increaseHealth(){ this.health++; }

    public Vector moveUp() {
        return new Vector(getPosition().getX(), getPosition().getY() - (velocity * speed_boost));
    }

    public Vector moveDown() {
        return new Vector(getPosition().getX(), getPosition().getY() + (velocity * speed_boost));
    }

    public Vector moveRight() {
        return new Vector(getPosition().getX() + (velocity * speed_boost), getPosition().getY());
    }

    public Vector moveLeft() {
        return new Vector(getPosition().getX() - (velocity * speed_boost), getPosition().getY());
    }

    public abstract boolean canMove(Vector position);

    public Room getRoom() {
        return room;
    }
}
