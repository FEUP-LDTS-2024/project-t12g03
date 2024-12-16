package projectt12g03.model.game.room;

import projectt12g03.model.Vector;

import projectt12g03.model.game.elements.Element;
import projectt12g03.model.game.elements.stationary.Laser;
import projectt12g03.model.game.elements.movable.Monster;
import projectt12g03.model.game.elements.movable.Player;
import projectt12g03.model.game.elements.stationary.*;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private int width;
    private int height;

    private Player player;
    private Chest chest;
    private Key key;
    private List<Floor> floors;
    private List<Wall> walls;
    private List<Door> doors;
    private List<Monster> monsters;
    private List<Laser> lasers;


    public Room(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private boolean checkCollision(Element a, Element b, Vector position) {
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

    public boolean checkCollisionsForPlayer(Vector position) {
        if (checkKeyCollision(player, position)) {
            setKey(null);
            chest.foundKey();
            return false;
        } else if (checkChestCollision(player, position)) {
            if (chest.isUnlockable() && !chest.isOpened()) {
                chest.open();
            } else if (chest.isUnlockable()) {
                if (chest.getPotion() != null) {
                    if (chest.getPotion().getImmunity() == 0) {
                        if (player.getInventory().isNotFull()) {
                            player.getInventory().addPotion(chest.getPotion());
                            chest.setPotion(null);
                            return false;
                        }
                    }
                } else return false;
            }
            return true;
        }

        return checkWallCollision(player, position) || checkMonsterDamage(position) || isAtTransition(player, position) || checkLaserCollision(player, position);
    }

    private boolean checkMonsterDamage(Vector position) {
        boolean monsterCollision = checkMonsterCollision(player, position);
        if (monsterCollision && player.getImmunity() == 0) {
            player.reduceHealth();
            player.setImmunity();
        }

        return monsterCollision;
    }

    public boolean checkCollisionsForMonster(Monster monster, Vector position) {
        return checkWallCollision(monster, position) || checkMonsterCollision(monster, position) || isAtTransition(monster, position) ||
                checkKeyCollision(monster, position) || checkChestCollision(monster, position) || checkLaserCollision(monster, position) || checkPlayerCollision(monster, position);
    }

    public void checkAttackCollision(ArrayList<Vector> positions) {
        for (Vector position : positions) {
            for (Monster monster : monsters) {
                if (monster.getPosition().equals(position)) {
                    monster.reduceHealth();
                    if (monster.getHealth() == 0) monsters.remove(monster);
                    return;
                }
            }
        }
    }

    private boolean checkPlayerCollision(Element a, Vector position) {
        if(checkCollision(a, player, position)) {
            if(player.getImmunity() == 0) {
                player.reduceHealth();
                player.setImmunity();
            }
            return true;
        }
        return false;
    }

    private boolean checkMonsterCollision(Element a, Vector position) {
        for (Monster monster : monsters) {
            if (checkCollision(a, monster, position) && (monster != a))
                return true;
        }
        return false;
    }

    private boolean checkLaserCollision(Element a, Vector position) {
        for (Laser laser : lasers) {
            if (checkCollision(a, laser, position))
                return true;
        }
        return false;
    }

    private boolean checkWallCollision(Element a, Vector position) {
        for (Wall wall : walls) {
            if (checkCollision(a, wall, position))
                return true;
        }
        return false;
    }

    private boolean checkKeyCollision(Element a, Vector position) {
        if (key == null) return false;

        return checkCollision(a, key, position);
    }

    private boolean checkChestCollision(Element a, Vector position) {
        return checkCollision(a, chest, position);
    }

    private boolean checkCollisionsForLaser(Laser laser, int displacement) {
        Vector laserBeamPosition = new Vector (displacement, laser.getPosition().getY());
        if(checkCollisionForBeam(player, laser.getLaserBeamHitbox(), laserBeamPosition)) {
            if (player.getImmunity() == 0) {
                player.reduceHealth();
                player.setImmunity();
            }
            return true;
        }

        for (Wall wall : walls) {
            if(checkCollisionForBeam(wall, laser.getLaserBeamHitbox(), laserBeamPosition)) {
                return true;
            }
        }

        for (Monster monster : monsters) {
            if(checkCollisionForBeam(monster, laser.getLaserBeamHitbox(), laserBeamPosition)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCollisionForBeam(Element b, Rectangle laserBeamHitbox, Vector position) {
        int aHitboxX = position.getX() + laserBeamHitbox.x;
        int aHitboxY = position.getY() + laserBeamHitbox.y;
        int aHitboxWidth = laserBeamHitbox.width;
        int aHitboxHeight = laserBeamHitbox.height;

        int bHitboxX = b.getPosition().getX() + b.getHitbox().x;
        int bHitboxY = b.getPosition().getY() + b.getHitbox().y;
        int bHitboxWidth = b.getHitbox().width;
        int bHitboxHeight = b.getHitbox().height;

        boolean collisionX = aHitboxX < bHitboxX + bHitboxWidth && aHitboxX + aHitboxWidth > bHitboxX;
        boolean collisionY = aHitboxY < bHitboxY + bHitboxHeight && aHitboxY + aHitboxHeight > bHitboxY;

        return collisionX && collisionY;
    }

    public boolean isAtTransition(Element a, Vector position) {
        for (Door door : doors) {
            if (checkCollision(a, door, position))
                return true;
        }
        return false;
    }

    public int getDoorNumber(Vector position) {
        for (Door door : doors) {
            if (checkCollision(player, door, position))
                return door.getNumber();
        }
        return 0;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public int getLaserRange(Laser laser) {
        int range = 0;
        int pos = laser.getPosition().getX();
        switch (laser.getDirection()) {
            case "RIGHT":
                pos++;
                while (!checkCollisionsForLaser(laser, pos)) {
                    range++;
                    pos++;
                }
                break;
            case "LEFT":
                pos--;
                while (!checkCollisionsForLaser(laser, pos)) {
                    range++;
                    pos--;
                }
                break;
        }
        return range;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Door> getDoors() {
        return doors;
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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

    public List<Laser> getLasers() {
        return lasers;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}