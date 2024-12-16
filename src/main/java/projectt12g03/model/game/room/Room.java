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

    public boolean checkCollision(Rectangle a, Rectangle b) {
        return a.intersects(b);
    }

    public boolean checkCollisionsForPlayer(Rectangle hitbox) {
        if (checkKeyCollision(hitbox)) {
            setKey(null);
            chest.foundKey();
            return false;
        }
        else if (checkChestCollision(hitbox)) {
            if (chest.isUnlockable() && !chest.isOpened()) {
                chest.open();
            }
            else if (chest.isUnlockable()) {
                if (chest.getPotion() != null) {
                    if (chest.getPotion().getImmunity() == 0) {
                        if (player.getInventory().isNotFull()) {
                            player.getInventory().addPotion(chest.getPotion());
                            chest.setPotion(null);
                            return false;
                        }
                    }
                }
                else return false;
            }
            return true;
        }

        return checkWallCollision(hitbox) || checkMonsterDamage(hitbox) || isAtTransition(hitbox) || checkLaserCollision(hitbox);
    }

    public boolean checkMonsterDamage(Rectangle hitbox){
        boolean monsterCollision =  checkMonsterCollision(player, hitbox);
        if (monsterCollision && player.getImmunity() == 0){
            player.reduceHealth();
            player.setImmunity();
        }

        return monsterCollision;
    }

    public boolean checkCollisionsForMonster(Monster monster, Rectangle hitbox) {
        if (checkPlayerCollision(hitbox) && player.getImmunity() == 0) {
            player.reduceHealth();
            player.setImmunity();
        }

        return checkWallCollision(hitbox) || checkMonsterCollision(monster, hitbox) || isAtTransition(hitbox) ||
                checkKeyCollision(hitbox) || checkChestCollision(hitbox) || checkLaserCollision(hitbox);
    }

    public void checkAttackCollision(ArrayList<Vector> positions) {
        for (Vector position : positions){
            for (Monster monster : monsters){
                if (monster.getPosition().equals(position)){
                    monster.reduceHealth();
                    if(monster.getHealth() == 0) monsters.remove(monster);
                    return;
                }
            }
        }
    }

    public boolean checkPlayerCollision(Rectangle hitbox) {
        return checkCollision(player.getHitbox(), hitbox);
    }

    public boolean checkMonsterCollision(Element a, Rectangle hitbox) {
        for (Monster monster : monsters) {
            if(checkCollision(monster.getHitbox(), hitbox) && (monster != a))
                return true;
        }
        return false;
    }

    public boolean checkLaserCollision(Rectangle hitbox) {
        for (Laser laser : lasers) {
            if(checkCollision(laser.getHitbox(), hitbox))
                return true;
        }
        return false;
    }

    public void checkRayDamage() {
        if (checkRayCollision(player) && player.getImmunity() == 0) {
            player.reduceHealth();
            player.setImmunity();
            return;
        }

        for (Monster monster : monsters) {
            if (checkRayCollision(monster)) {
                monster.reduceHealth();
                if (monster.getHealth() == 0) {
                    monsters.remove(monster);
                }
                return;
            }
        }
    }

    public boolean checkRayCollision(Element a) {
        for (Laser laser : lasers) {
            if (laser.getActive() > 0) {
                switch (laser.getDirection()) {
                    case "LEFT":
                        int startX = laser.getPosition().getX() - getLaserRange(laser) - 1;
                        int startY = laser.getPosition().getY();
                        if (checkCollision(laser.getLaserBeamHitbox(), a.getHitbox())) {
                            return true;
                        }
                        break;
                    case "RIGHT":
                        int endX = laser.getPosition().getX() + getLaserRange(laser) + 1;
                        int endY = laser.getPosition().getY();
                        if (checkCollision(laser.getLaserBeamHitbox(), a.getHitbox())) {
                            return true;
                        }
                        break;
                }
            }
        }
        return false;
    }

    public boolean checkWallCollision(Rectangle hitbox) {
        for (Wall wall : walls) {
            if(checkCollision(wall.getHitbox(), hitbox))
                return true;
        }
        return false;
    }

    public boolean checkKeyCollision(Rectangle hitbox) {
        if (key == null) return false;

        return checkCollision(key.getHitbox(), hitbox);
    }

    public boolean checkChestCollision(Rectangle hitbox) { return checkCollision(chest.getHitbox(), hitbox); }


    public boolean isAtTransition(Rectangle hitbox) {
        for (Door door : doors) {
            if(checkCollision(door.getHitbox(), hitbox))
                return true;
        }
        return false;
    }

    public int getDoorNumber(Rectangle hitbox) {
        for (Door door : doors) {
            if(checkCollision(door.getHitbox(), hitbox))
                return door.getNumber();
        }
        return 0;
    }

    public List<Floor> getFloors() { return floors; }

    public int getLaserRange(Laser laser){
        int range = 0;
        int pos = laser.getPosition().getX();
        int yPos = laser.getPosition().getY() + (int)laser.getLaserBeamHitbox().getY();

        switch (laser.getDirection()) {
            case "LEFT":
                pos--;
                while (true/*!checkCollisionsForLaser(laser, new Vector(pos, yPos))*/) {
                    range++;
                    pos--;
                    if (range > 100) break;
                }
                break;
            case "RIGHT":
                pos++;
                range++;
                while (true/*!checkCollisionsForLaser(laser, new Vector(pos, yPos))*/) {
                    range++;
                    pos++;
                    if (range > 100) break;
                }
                break;
        }
        laser.setRange(range);
        return range;
    }


    public Player getPlayer() { return player; }

    public List<Wall> getWalls() { return walls; }

    public List<Door> getDoors() { return doors; }

    public Chest getChest() { return chest; }

    public Key getKey() { return key; }

    public List<Monster> getMonsters() { return monsters; }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public void setPlayer(Player player) {
        this.player = player;
        if (player != null) {
            player.setRoom(this);
        }
    }

    public void setFloors(List<Floor> floors) { this.floors = floors; }

    public void setWalls(List<Wall> walls) { this.walls = walls; }

    public void setDoors(List<Door> doors) { this.doors = doors; }

    public void setChest(Chest chest) { this.chest = chest; }

    public void setKey(Key key) { this.key = key; }

    public void setMonsters(List<Monster> monsters) { this.monsters = monsters; }

    public  void setLasers(List<Laser> lasers) { this.lasers = lasers; }

    public List<Laser> getLasers() { return lasers; }

    public void setWidth(int width) { this.width = width; }

    public void setHeight(int height) { this.height = height; }

}
