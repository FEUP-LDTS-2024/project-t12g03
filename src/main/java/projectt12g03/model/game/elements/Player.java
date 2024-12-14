package projectt12g03.model.game.elements;

import projectt12g03.model.Vector;
import projectt12g03.model.game.room.Room;

import java.util.ArrayList;

public class Player extends Movable {
    private int WALK_SPEED = 1;
    private int HEALTH = 3;
    private int ATTACKDELAY = 6;
    private int IMMUNITY = 15;

    private String direction;
    private int immunity = 0;
    private int isAttacking = 0;

    public Player(double x, double y, Room room) {
        super(x, y, room);
        setVelocity(WALK_SPEED);
        setHealth(HEALTH);
    }

    public int getImmunity() { return immunity; }

    public void setImmunity(){ this.immunity = IMMUNITY; }

    public String getDirection(){return direction;}

    public void setDirection(String direction) { this.direction = direction; }

    public void reduceImmunity() { this.immunity--; }

    public int getIsAttacking() { return this.isAttacking; }

    public void reduceIsAttacking() { this.isAttacking--; }

    @Override
    public boolean canMove(Vector position) { return !room.checkCollisionsForPlayer(position) && isAttacking == 0; }

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
}

