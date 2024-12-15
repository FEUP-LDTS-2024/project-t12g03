package projectt12g03.model.game.elements.movable;

import projectt12g03.model.Vector;
import projectt12g03.model.game.inventory.Inventory;
import projectt12g03.model.game.room.Room;

import java.util.ArrayList;

public class Player extends Movable {
    private int WALK_SPEED = 1;
    private int HEALTH = 3;
    private int ATTACKDELAY = 6;
    private int IMMUNITY = 15;

    private Inventory inventory;
    private String direction;
    private int immunity = 0;
    private int isAttacking = 0;

    public Player(double x, double y, Room room) {
        super(x, y, room);
        setVelocity(WALK_SPEED);
        setHealth(HEALTH);
        inventory = new Inventory();
    }

    public void reduceIsAttacking() { this.isAttacking--; }

    public void reduceImmunity() { this.immunity--; }

    public int getImmunity() { return immunity; }

    public int getIsAttacking() { return isAttacking; }

    public String getDirection() { return direction; }

    public Inventory getInventory() { return inventory; }

    public void setImmunity(){ this.immunity = IMMUNITY; }

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

    public void attack(){
        if(isAttacking != 0) return;
        isAttacking = ATTACKDELAY;
        ArrayList<Vector> positions = new ArrayList<>();
        switch (direction){
            case "LEFT":
                positions.add(new Vector(getPosition().getX() - 1,getPosition().getY()));
                positions.add(new Vector(getPosition().getX() - 2,getPosition().getY()));
                room.checkAttackCollision(positions);
                break;
            case "RIGHT":
                positions.add(new Vector(getPosition().getX() + 1,getPosition().getY()));
                positions.add(new Vector(getPosition().getX() + 2,getPosition().getY()));
                room.checkAttackCollision(positions);
                break;
            case "UP":
                positions.add(new Vector(getPosition().getX(),getPosition().getY() - 1));
                positions.add(new Vector(getPosition().getX(),getPosition().getY() - 2));
                room.checkAttackCollision(positions);
                break;
            case "DOWN":
                positions.add(new Vector(getPosition().getX(),getPosition().getY() + 1));
                positions.add(new Vector(getPosition().getX(),getPosition().getY() + 2));
                room.checkAttackCollision(positions);
                break;
        }
    }

    @Override
    public boolean canMove(Vector position) { return !room.checkCollisionsForPlayer(position) && isAttacking == 0; }
}

