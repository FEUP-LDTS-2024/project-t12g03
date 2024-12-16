package projectt12g03.model.game.elements.movable.hostile;

import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.movable.Movable;
import projectt12g03.model.game.room.Room;

import java.util.ArrayList;
import java.util.List;

public class Monster extends Movable {
    private int WALK_SPEED = 1;
    private int HEALTH = 1;
    private List<String> directions;

    public Monster(int x, int y, Room room) {
        super(x, y, room);
        setVelocity(WALK_SPEED);
        setHealth(HEALTH);
        setDirection("DOWN");
        directions = new ArrayList<>();;
        setState("IDLE");
    }

    public void setDirections(List<String> directions) { this.directions = directions; }

    public List<String> getDirections() { return directions; }

    @Override
    public boolean canMove(Vector position) {
        return !room.checkCollisionsForMonster(this, position);
    }
}
