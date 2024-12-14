package projectt12g03.model.game.room;

import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.movable.Monster;
import projectt12g03.model.game.elements.movable.Player;
import projectt12g03.model.game.elements.stationary.Chest;
import projectt12g03.model.game.elements.stationary.Door;
import projectt12g03.model.game.elements.stationary.Key;
import projectt12g03.model.game.elements.stationary.Wall;

import java.util.List;

public class Room {
    private int width;
    private int height;

    private Player player;
    private Chest chest;
    private Key key;
    private List<Wall> walls;
    private List<Door> doors;
    private List<Monster> monsters;

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
                        chest.setPotion(null);
                        return false;
                    }
                }
            }
            return true;
        }

        return checkWallCollision(position) || checkDamage(position) || isAtTransition(position);
    }

    public boolean checkCollisionsForMonster(Vector position) {
        return checkWallCollision(position) || checkMonsterCollision(position) || checkPlayerCollision(position) ||
                isAtTransition(position) || checkKeyCollision(position) || checkChestCollision(position);
    }

    public boolean checkDamage(Vector position){
        boolean monsterCollision =  checkMonsterCollision(position);
        if (monsterCollision && player.getImmunity() == 0){
            player.reducedHealth();
            player.setImmunity(15);
        }
        return monsterCollision;
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

    public Player getPlayer() { return player; }

    public List<Wall> getWalls() { return walls; }

    public List<Door> getDoors() { return doors; }

    public Chest getChest() { return chest; }

    public Key getKey() { return key; }

    public List<Monster> getMonsters() { return monsters; }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public void setPlayer(Player player) { this.player = player; }

    public void setWalls(List<Wall> walls) { this.walls = walls; }

    public void setDoors(List<Door> doors) { this.doors = doors; }

    public void setChest(Chest chest) { this.chest = chest; }

    public void setKey(Key key) { this.key = key; }

    public void setMonsters(List<Monster> monsters) { this.monsters = monsters; }

    public void setWidth(int width) { this.width = width; }

    public void setHeight(int height) { this.height = height; }
}
