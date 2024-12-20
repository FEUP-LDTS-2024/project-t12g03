package projectt12g03.model.game.elements.movable.player;

import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.movable.Movable;
import projectt12g03.model.game.elements.stationary.potion.Potion;
import projectt12g03.model.game.inventory.Inventory;
import projectt12g03.model.game.room.Room;

import java.awt.*;

public class Player extends Movable implements Weapon {
    private final Inventory inventory;
    private final int WALK_SPEED = 3;
    private final int SPRINT_SPEED = 5;
    private final int HEALTH = 4;
    private final int ATTACKDELAY = 6;
    private final int IMMUNITY = 15;

    private Rectangle spear;
    private boolean isSprinting;
    private int energy;
    private int immunity;
    private int isAttacking;
    private int effectDuration;
    private Potion activePotion;

    public Player(int x, int y, Room room) {
        super(x, y, room);
        setVelocity(WALK_SPEED);
        setHealth(HEALTH);
        setDirection("DOWN");
        setState("IDLE");
        setSpeedBoost(1);
        inventory = new Inventory();
        this.isSprinting = false;
        this.isAttacking = 0;
        this.effectDuration = 0;
        this.immunity = 0;
        this.energy = 800;
    }

    public void walk() {
        isSprinting = false;
        setState("WALKING");
        setVelocity(WALK_SPEED);
    }

    public void sprint() {
        if (isSprinting) {
            walk();
        }
        else if (energy > 6) {
            isSprinting = true;
            setVelocity(SPRINT_SPEED);
            setState("RUNNING");
        }
    }

    public boolean isSprinting() {
        return isSprinting;
    }

    public void heal() {
        if (getHealth() < 4 && energy >= 800) {
            energy = 0;
            int newHealth = getHealth() + 1;
            setHealth(newHealth);
        }
    }

    public void consume(Potion potion) {
        potion.applyEffect(this);
        activePotion = potion;
    }

    public void updatePotionDuration() {
        if (effectDuration > 0) {
            effectDuration--;
        }

        else if (effectDuration == 0 && activePotion != null) {
            activePotion.removeEffect(this);
            activePotion = null;
        }

    }

    public void reduceEnergy() {
        this.energy -= 6;
        if (getEnergy() < 5)
            walk();
    }

    public void reduceIsAttacking() {
        this.isAttacking--;
    }

    public void reduceImmunity() {
        this.immunity--;
    }

    public void increaseEnergy(int value) {
        if (getEnergy() + value <= 800) {
            this.energy += value;
        }
        else {
            this.energy = 800;
        }
    }

    public int getEffectDuration() {
        return effectDuration;
    }

    public int getImmunity() {
        return immunity;
    }

    public int getIsAttacking() {
        return isAttacking;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getEnergy() {
        return energy;
    }

    public Rectangle getSpear() {
        return spear;
    }

    public void setImmunity() {
        this.immunity = IMMUNITY;
    }

    public void setImmunity(int immunity) {
        this.immunity = immunity;
    };

    public void setEffectDuration(int duration) {
        this.effectDuration = duration;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Vector calculateNewPosition() {
        return switch (getDirection()) {
            case "UP" -> new Vector(getPosition().getX(), 223);
            case "DOWN" -> new Vector(getPosition().getX(), -15);
            case "LEFT" -> new Vector(304, getPosition().getY());
            case "RIGHT" -> new Vector(0, getPosition().getY());
            default -> throw new IllegalArgumentException("Direction for Transition not Found");
        };
    }

    public void attack(){
        if(isAttacking != 0) {
            return;
        }

        isAttacking = ATTACKDELAY;
        spear = setWeaponHitbox();
    }

    @Override
    public boolean canMove(Vector position) {
        return !room.checkCollisionsForPlayer(position);
    }

    @Override
    public Rectangle setWeaponHitbox() {
        return switch (getDirection()) {
            case "UP" -> new Rectangle(5, 2, 6, 18);
            case "DOWN" -> new Rectangle(5, 30, 6, 18);
            case "LEFT" -> new Rectangle(-14, 20, 18, 6);
            case "RIGHT" -> new Rectangle(12, 20, 18, 6);
            default -> throw new IllegalArgumentException("Direction for Spear not Found");
        };
    }
}

