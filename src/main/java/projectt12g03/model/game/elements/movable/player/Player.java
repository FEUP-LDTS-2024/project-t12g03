package projectt12g03.model.game.elements.movable.player;

import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.movable.Movable;
import projectt12g03.model.game.elements.stationary.Potion;
import projectt12g03.model.game.inventory.Inventory;
import projectt12g03.model.game.room.Room;

import java.awt.*;

public class Player extends Movable implements Weapon {
    private final Inventory inventory;
    private final int WALK_SPEED = 2;
    private final int HEALTH = 4;
    private final int ATTACKDELAY = 6;
    private final int IMMUNITY = 30;

    private Rectangle spear;
    private String lastEffect;
    private int immunity = 0;
    private int isAttacking = 0;
    private int energy = 800;
    private int effectDuration = 0;


    public Player(int x, int y, Room room) {
        super(x, y, room);
        setVelocity(WALK_SPEED);
        setHealth(HEALTH);
        setDirection("DOWN");
        setState("IDLE");
        inventory = new Inventory();
    }

    public void heal() {
        if (getHealth() < 4 && energy == 800) {
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
                case "immunity":
                    setImmunity(potion.getDuration());
                case "heal":
                    if (getHealth() < 4) {
                        increaseHealth();
                        lastEffect = "heal";
                    }
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

    public Rectangle getSpear() { return spear; }

    public void setImmunity(){ this.immunity = IMMUNITY; }

    public void setImmunity(int immunity) {this.immunity = immunity; };

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
        spear = setWeaponHitbox();
    }

    @Override
    public boolean canMove(Vector position) { return !room.checkCollisionsForPlayer(position) && isAttacking == 0; }

    @Override
    public Rectangle setWeaponHitbox() {
        return switch (getDirection()) {
            case "UP" -> new Rectangle(5, 0, 6, 18);
            case "DOWN" -> new Rectangle(5, 29, 6, 18);
            case "LEFT" -> new Rectangle(2, 22, 6, 18);
            case "RIGHT" -> new Rectangle(13, 20, 4, 18);
            default -> throw new IllegalArgumentException("Direction for Spear not Found");
        };
    }
}

