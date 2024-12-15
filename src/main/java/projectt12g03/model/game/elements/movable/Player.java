package projectt12g03.model.game.elements.movable;

import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.stationary.Potion;
import projectt12g03.model.game.inventory.Inventory;
import projectt12g03.model.game.room.Room;

import java.util.ArrayList;

public class Player extends Movable {
    private final Inventory inventory;
    private int WALK_SPEED = 2;
    private final int HEALTH = 4;
    private final int ATTACKDELAY = 6;
    private final int IMMUNITY = 15;

    private String lastEffect;
    private int immunity = 0;
    private int isAttacking = 0;
    private int energy = 800;
    private int effectDuration = 0;

    public Player(double x, double y, Room room) {
        super(x, y, room);
        setVelocity(WALK_SPEED);
        setHealth(HEALTH);
        setDirection("DOWN");
        setState("IDLE");
        inventory = new Inventory();
    }

    public void heal() {
        if (getHealth() < 4) {
            energy = 0;
            int newHealth = getHealth() + 1;
            setHealth(newHealth);
        }
    }

    public void consume(Potion potion) {
        if (effectDuration == 0) {
            effectDuration = potion.getDuration();
            switch (potion.getEffect()) {
                case "speed":
                    setVelocity(WALK_SPEED*3);
                    lastEffect = "speed";
                    break;
                case "heal":
                    increaseHealth();
                    lastEffect = "heal";
                    break;
                default:
                    lastEffect = "HERE SO IT DOESNT STOP RUNNING"; //remove after all effects added
            }
        }
    }

    public void removeEffect() {
        switch (lastEffect) {
            case "speed":
                setVelocity(WALK_SPEED);
                break;
        }
    }

    public void reduceIsAttacking() { this.isAttacking--; }

    public void reduceImmunity() { this.immunity--; }

    public void reduceEffectDuration() {
        this.effectDuration--;
        System.out.println(effectDuration);
        if (effectDuration == 0) {
            removeEffect();
        }
    }

    public void increaseEnergy() { this.energy++;}

    public int getEffectDuration() { return effectDuration;}

    public int getImmunity() { return immunity; }

    public int getIsAttacking() { return isAttacking; }

    public Inventory getInventory() { return inventory; }

    public int getEnergy() { return energy; }

    public void setImmunity(){ this.immunity = IMMUNITY; }

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

    public void attack(){
        if(isAttacking != 0) return;
        isAttacking = ATTACKDELAY;
        ArrayList<Vector> positions = new ArrayList<>();
        switch (getDirection()){
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

