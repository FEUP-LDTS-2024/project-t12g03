package projectt12g03.model.game.elements.movable;

import projectt12g03.model.Vector;
import projectt12g03.model.game.room.Room;

public class Player extends Movable {
    private int WALK_SPEED = 1;
    private int HEALTH = 3;

    private int immunity = 0;
    private int energy;

    public Player(double x, double y, Room room) {
        super(x, y, room);
        setVelocity(WALK_SPEED);
        setHealth(HEALTH);
    }

    public int getImmunity() { return immunity; }

    public void setImmunity(int i){ this.immunity = i; }

    public void reduceImmunity(){ this.immunity--; }

    @Override
    public boolean canMove(Vector position) { return !room.checkCollisionsForPlayer(position); }

}

