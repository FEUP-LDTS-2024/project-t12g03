package projectt12g03.model.game.elements;

import projectt12g03.model.Vector;
import projectt12g03.model.game.room.Room;

public class Player extends Movable {
    private int WALK_SPEED = 1;

    public Player(double x, double y, Room room) { super(x, y, room); }

    @Override
    public boolean canMove(Vector position) { return !room.checkCollisions(position); }
}

