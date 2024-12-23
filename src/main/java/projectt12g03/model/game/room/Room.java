package projectt12g03.model.game.room;

import projectt12g03.model.Vector;

import projectt12g03.model.game.elements.Element;
import projectt12g03.model.game.elements.movable.Movable;
import projectt12g03.model.game.elements.stationary.Laser;
import projectt12g03.model.game.elements.movable.hostile.Monster;
import projectt12g03.model.game.elements.movable.player.Player;
import projectt12g03.model.game.elements.stationary.*;


import java.awt.*;
import java.util.List;

public class Room {
    private final int roomCode;

    private Player player;
    private Chest chest;
    private Key key;
    private List<Floor> floors;
    private List<Wall> walls;
    private List<Door> doors;
    private List<Monster> monsters;
    private List<Laser> lasers;


    public Room(int roomCode) {
        this.roomCode = roomCode;
    }

    boolean checkCollisionForElements(Movable a, Element b, Vector position) {
        int aHitboxX = position.getX() + a.getHitbox().x;
        int aHitboxY = position.getY() + a.getHitbox().y;
        int aHitboxWidth = a.getHitbox().width;
        int aHitboxHeight = a.getHitbox().height;

        int bHitboxX = b.getPosition().getX() + b.getHitbox().x;
        int bHitboxY = b.getPosition().getY() + b.getHitbox().y;
        int bHitboxWidth = b.getHitbox().width;
        int bHitboxHeight = b.getHitbox().height;

        boolean collisionX = aHitboxX < bHitboxX + bHitboxWidth && aHitboxX + aHitboxWidth > bHitboxX;
        boolean collisionY = aHitboxY < bHitboxY + bHitboxHeight && aHitboxY + aHitboxHeight > bHitboxY;

        return collisionX && collisionY;
    }

    boolean checkCollisionForDamage(Element b, Rectangle hitbox, Vector position) { //needed because damaging elements have 2 hitboxs
        int aHitboxX = position.getX() + hitbox.x;
        int aHitboxY = position.getY() + hitbox.y;
        int aHitboxWidth = hitbox.width;
        int aHitboxHeight = hitbox.height;

        int bHitboxX = b.getPosition().getX() + b.getHitbox().x;
        int bHitboxY = b.getPosition().getY() + b.getHitbox().y;
        int bHitboxWidth = b.getHitbox().width;
        int bHitboxHeight = b.getHitbox().height;

        boolean collisionX = aHitboxX < bHitboxX + bHitboxWidth && aHitboxX + aHitboxWidth > bHitboxX;
        boolean collisionY = aHitboxY < bHitboxY + bHitboxHeight && aHitboxY + aHitboxHeight > bHitboxY;

        return collisionX && collisionY;
    }

    public boolean checkCollisionsForPlayer(Vector position) {
        if (checkCollisionWithKey(player, position)) {
            setKey(null);
            chest.foundKey();
            return false;
        }

        else if (checkCollisionWithChest(player, position)) {
            if (chest.isUnlockable() && !chest.isOpened()) {
                chest.open();
            }

            else if (chest.isOpened()) {
                if (chest.getPotion() != null && chest.getPotion().getImmunity() == 0 && player.getInventory().isNotFull()) {
                    player.getInventory().addPotion(chest.getPotion());
                    chest.setPotion(null);
                }
            }

            else return true;
        }

        return checkCollisionWithWall(player, position) || checkCollisionWithLaserWall(player, position) || checkDamageToPlayer(position) || isAtTransition(player, position);
    }

    public boolean checkCollisionsForMonster(Monster monster, Vector position) {
        return checkCollisionWithWall(monster, position) || checkCollisionWithMonster(monster, position) || isAtTransition(monster, position) ||
                checkCollisionWithKey(monster, position) || checkCollisionWithChest(monster, position) || checkCollisionWithLaserWall(monster, position) || checkCollisionWithPlayer(monster, position);
    }

    boolean checkDamageToPlayer(Vector position) {
        boolean monsterCollision = checkCollisionWithMonster(player, position);

        if (monsterCollision && player.getImmunity() == 0) {
            player.reduceHealth();
            player.setImmunity();
        }

        return monsterCollision;
    }

    boolean checkCollisionWithPlayer(Movable a, Vector position) {
        if(checkCollisionForElements(a, player, position)) {
            if(player.getImmunity() == 0) {
                player.reduceHealth();
                player.setImmunity();
            }
            return true;
        }
        return false;
    }

    public void checkDamageToMonster() {
        for (Monster monster : monsters) {
            if(checkCollisionForDamage(monster, player.getSpear(), player.getPosition())) {
                monster.reduceHealth();
                if (monster.getHealth() == 0) {
                    monsters.remove(monster);
                }
                break;
            }
        }
    }

    boolean checkCollisionWithMonster(Movable a, Vector position) {
        for (Monster monster : monsters) {
            if (checkCollisionForElements(a, monster, position) && (monster != a)) {
                return true;
            }
        }
        return false;
    }

    boolean checkCollisionWithLaserWall(Movable a, Vector position) {
        for (Laser laser : lasers) {
            if (checkCollisionForElements(a, laser, position))
                return true;
        }
        return false;
    }

    boolean checkCollisionWithWall(Movable a, Vector position) {
        for (Wall wall : walls) {
            if (checkCollisionForElements(a, wall, position))
                return true;
        }
        return false;
    }

    boolean checkCollisionWithKey(Movable a, Vector position) {
        if (key == null) return false;

        return checkCollisionForElements(a, key, position);
    }

    boolean checkCollisionWithChest(Movable a, Vector position) {
        return checkCollisionForElements(a, chest, position);
    }

    boolean checkCollisionsForLaserBeam(Laser laser, int displacement) {
        Vector laserBeamPosition = new Vector (displacement, laser.getPosition().getY());
        if(checkCollisionForDamage(player, laser.getLaserBeamHitbox(), laserBeamPosition)) {
            if (player.getImmunity() == 0) {
                player.reduceHealth();
                player.setImmunity();
            }
            return true;
        }

        for (Wall wall : walls) {
            if(checkCollisionForDamage(wall, laser.getLaserBeamHitbox(), laserBeamPosition)) {
                return true;
            }
        }

        for (Monster monster : monsters) {
            if(checkCollisionForDamage(monster, laser.getLaserBeamHitbox(), laserBeamPosition)) {
                return true;
            }
        }

        return false;
    }

    public boolean isAtTransition(Movable a, Vector position) {
        for (Door door : doors) {
            if (checkCollisionForElements(a, door, position))
                return true;
        }
        return false;
    }

    public int getDoorNumber(Vector position) {
        for (Door door : doors) {
            if (checkCollisionForElements(player, door, position))
                return door.getNumber();
        }
        return -1;
    }

    public int getLaserRange(Laser laser) {
        int range = 0;
        int pos = laser.getPosition().getX();
        int direction = laser.isPointingLeft() ? -1 : 1;
        pos += (4*direction);
        while (!checkCollisionsForLaserBeam(laser, pos)) {
            range++;
            pos += (4*direction);
        }
        return range;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public Chest getChest() {
        return chest;
    }

    public Key getKey() {
        return key;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public List<Laser> getLasers() {
        return lasers;
    }

    public int getRoomCode() {
        return roomCode;
    }

    public void setPlayer(Player player) {
        this.player = player;
        if (player != null) {
            player.setRoom(this);
        }
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public void setDoors(List<Door> doors) {
        this.doors = doors;
    }

    public void setChest(Chest chest) {
        this.chest = chest;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public void setLasers(List<Laser> lasers) {
        this.lasers = lasers;
    }
}