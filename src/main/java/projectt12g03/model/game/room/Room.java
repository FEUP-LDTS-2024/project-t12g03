package projectt12g03.model.game.room;

import projectt12g03.model.Vector;

import projectt12g03.model.game.elements.stationary.Laser;
import projectt12g03.model.game.elements.movable.Monster;
import projectt12g03.model.game.elements.movable.Player;
import projectt12g03.model.game.elements.stationary.*;


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

    public boolean checkCollisionsForPlayer(Vector position) {
        if (checkKeyCollision(position)) {
            setKey(null);
            chest.foundKey();
            return false;
        }
        else if (checkChestCollision(position)) {
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

        return checkWallCollision(position) || checkMonsterDamage(position) || isAtTransition(position) || checkLaserCollision(position);
    }

    public boolean checkMonsterDamage(Vector position){
        boolean monsterCollision =  checkMonsterCollision(position);
        if (monsterCollision && player.getImmunity() == 0){
            player.reduceHealth();
            player.setImmunity();
            System.out.println("Damage taken : " + player.getHealth());
        }

        return monsterCollision;
    }

    public boolean checkCollisionsForMonster(Vector position) {
        return checkWallCollision(position) || checkMonsterCollision(position) || checkPlayerCollision(position) ||
                isAtTransition(position) || checkKeyCollision(position) || checkChestCollision(position);
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

    public boolean checkPlayerCollision(Vector position) {
        return position.equals(player.getPosition());
    }

    public boolean checkMonsterCollision(Vector position) {
        for (Monster monster : monsters) {
            if (monster.getPosition().equals(position))
                return true;
        }
        return false;
    }

    public boolean checkLaserCollision(Vector position) {
        for (Laser laser : lasers) {
            if (laser.getPosition().equals(position))
                return true;
        }
        return false;
    }

    public void checkRayDamage() {
        if (checkRayCollision(player.getPosition()) && player.getImmunity() == 0){
            player.reduceHealth();
            System.out.println("Damage taken: " + player.getHealth());
            player.setImmunity();
            return;
        }
        for (Monster monster : monsters){
            if (checkRayCollision(monster.getPosition())){
                monster.reduceHealth();
                if(monster.getHealth() == 0) monsters.remove(monster);
                return;
            }
        }

    }
    public boolean checkRayCollision(Vector position){
        for (Laser laser : lasers){
            if (laser.getActive() == 0) return false;
            switch (laser.getDirection()){
                case "LEFT":
                    if (position.equals(new Vector(laser.getPosition().getX() - getLaserRange(laser) - 1,laser.getPosition().getY()))) return true;
                    break;
                case "RIGHT":
                    if (position.equals(new Vector(laser.getPosition().getX() + getLaserRange(laser) + 1,laser.getPosition().getY()))) return true;
                    break;
            }
        }
        return false;
    }

    public boolean checkWallCollision(Vector position) {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position))
                return true;
        }
        return false;
    }

    public boolean checkKeyCollision(Vector position) {
        if (key == null) return false;

        return position.equals(key.getPosition());
    }

    public boolean checkChestCollision(Vector position) { return position.equals(chest.getPosition()); }

    public boolean isAtTransition(Vector position) {
        for (Door door : doors) {
            if (door.getPosition().equals(position))
                return true;
        }
        return false;
    }

    public int getDoorNumber(Vector position) {
        for (Door door : doors) {
            if (door.getPosition().equals(position))
                return door.getNumber();
        }
        return 0;
    }

    public List<Floor> getFloors() { return floors; }

    public int getLaserRange(Laser laser){
        int range = 0;
        double pos = laser.getPosition().getX();
        switch (laser.getDirection()){
            case "RIGHT":
                pos++;
                while (!checkCollisionsForMonster(new Vector(pos,laser.getPosition().getY()))){
                    range++;
                    pos++;
                }
                break;
            case "LEFT":
                pos--;
                while (!checkCollisionsForMonster(new Vector(pos,laser.getPosition().getY()))){
                    range++;
                    pos--;
                }
                break;
        }
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
