package projectt12g03.model.game.room;

import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.Door;
import projectt12g03.model.game.elements.Monster;
import projectt12g03.model.game.elements.Player;
import projectt12g03.model.game.elements.Wall;

import java.util.List;

public class Room {
    private final int width;
    private final int height;

    private Player player;
    private List<Wall> walls;
    private List<Door> doors;
    private List<Monster> monsters;

    public Room(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean checkCollisionsForPlayer(Vector position) {
        return checkWallCollision(position) || checkDamage(position) || isAtTransition(position);
    }

    public boolean checkCollisionsForMonster(Vector position) {
        return checkWallCollision(position) || checkMonsterCollision(position) || checkPlayerCollision(position) || isAtTransition(position);
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

    public boolean isAtTransition(Vector position) {
        for (Door door : doors) {
            if (door.getPosition().equals(position))
                return true;
        }
        return false;
    }

    public int getDoor(Vector position) {
        for (Door door : doors) {
            if (door.getPosition().equals(position))
                return door.getNumber();
        }
        return 0;
    }

    public Player getPlayer() { return player; }

    public List<Wall> getWalls() { return walls; }

    public List<Door> getDoors() { return doors; }

    public List<Monster> getMonsters() { return monsters; }

    public void setPlayer(Player player) { this.player = player; }

    public void setWalls(List<Wall> walls) { this.walls = walls; }

    public void setDoors(List<Door> doors) { this.doors = doors; }

    public void setMonsters(List<Monster> monsters) { this.monsters = monsters; }
}
