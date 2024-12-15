package projectt12g03.model.game.elements.movable;

import projectt12g03.model.Vector;
import projectt12g03.model.game.room.Room;

public class Player extends Movable {
    private int WALK_SPEED = 2;
    private int HEALTH = 3;

    private int immunity = 0;
    private int energy;

    public Player(double x, double y, Room room) {
        super(x, y, room);
        setVelocity(WALK_SPEED);
        setHealth(HEALTH);
        setDirection("DOWN");
        setState("IDLE");
    }

    public void reduceImmunity(){ this.immunity--; }

    public int getImmunity() { return immunity; }

    public void setImmunity(int i){ this.immunity = i; }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Vector calculateNewPosition() {
        return switch (getDirection()) {
            case "UP" -> new Vector(getPosition().getX(), room.getHeight() - 2);
            case "DOWN" -> new Vector(getPosition().getX(), 1);
            case "LEFT" -> new Vector(room.getWidth() - 2, getPosition().getY());
            case "RIGHT" -> new Vector(1, getPosition().getY());
            default -> throw new IllegalArgumentException("Direction for Transition not Found");
        };
    }


    @Override
    public boolean canMove(Vector position) { return !room.checkCollisionsForPlayer(position); }
}

