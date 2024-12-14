package projectt12g03.model.game.elements.movable;

import projectt12g03.model.Vector;
import projectt12g03.model.game.room.Room;

public class Player extends Movable {
    private int WALK_SPEED = 5;
    private int HEALTH = 3;


    private String direction;
    private int immunity = 0;
    private int energy;

    public Player(double x, double y, Room room) {
        super(x, y, room);
        setVelocity(WALK_SPEED);
        setHealth(HEALTH);
    }

    public void reduceImmunity(){ this.immunity--; }

    public int getImmunity() { return immunity; }

    public void setImmunity(int i){ this.immunity = i; }

    public void setDirection(String direction) { this.direction = direction; }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Vector calculateNewPosition() {
        switch (direction) {
            case "UP":
                return new Vector(getPosition().getX(), room.getHeight() - 2);
            case "DOWN":
                return new Vector(getPosition().getX(), 1);
            case "LEFT":
                return new Vector(room.getWidth() - 2, getPosition().getY());
            case "RIGHT":
                return new Vector(1, getPosition().getY());
        }
        throw new IllegalArgumentException("Direction for Transition not Found");
    }


    @Override
    public boolean canMove(Vector position) { return !room.checkCollisionsForPlayer(position); }
}

