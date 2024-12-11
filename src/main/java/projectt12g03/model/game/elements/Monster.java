package projectt12g03.model.game.elements;

import projectt12g03.model.Vector;
import projectt12g03.model.game.room.Room;

public class Monster extends Movable {
    private int WALK_SPEED = 1;

    public Monster(double x, double y, Room room) {
        super(x, y, room);
        setVelocity(WALK_SPEED);
    }

    @Override
    public boolean canMove(Vector position) {
        return !room.checkMonsterCollisions(position);
    }

}
