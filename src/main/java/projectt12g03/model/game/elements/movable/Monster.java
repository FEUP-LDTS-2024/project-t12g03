package projectt12g03.model.game.elements.movable;

import projectt12g03.model.Vector;
import projectt12g03.model.game.room.Room;

import java.util.Objects;

public class Monster extends Movable {
    private int WALK_SPEED = 1;
    private int HEALTH = 1;

    public Monster(int x, int y, Room room) {
        super(x, y, room);
        setVelocity(WALK_SPEED);
        setHealth(HEALTH);
        setDirection("DOWN");
        setState("IDLE");
    }

    @Override
    public boolean canMove(Vector position) {
        return !room.checkCollisionsForMonster(this, position);
    }

}
