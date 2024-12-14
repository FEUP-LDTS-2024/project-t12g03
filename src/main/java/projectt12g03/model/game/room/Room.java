package projectt12g03.model.game.room;

import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.Laser;
import projectt12g03.model.game.elements.Monster;
import projectt12g03.model.game.elements.Player;
import projectt12g03.model.game.elements.Wall;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Room {
    private final int width;
    private final int height;
    private final int roomCode;

    private Player player;
    private List<Wall> walls;
    private Map<Integer, List<Wall>> doors;
    private List<Monster> monsters;
    private List<Laser> lasers;
    private RoomManager roomManager;

    //hard coded for now for testing and implementing
    public Room(int width, int height, int roomCode) {
        this.width = width;
        this.height = height;
        this.roomCode = roomCode;
    }

    public boolean checkCollisionsForPlayer(Vector position) {
        return checkWallCollision(position) || checkDamage(position) || isAtTransition(position);
    }

    public boolean checkDamage(Vector position){
        boolean monsterCollision =  checkMonsterCollision(position);
        if (monsterCollision && player.getImmunity() == 0){
            player.reduceHealth();
            player.setImmunity(15);
        }
        return monsterCollision;
    }

    public void checkAttackCollision(ArrayList<Vector> positions){
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

    public boolean checkCollisionsForMonster(Vector position) { // repetitive code, find a way to make it look better
        return checkWallCollision(position) || checkMonsterCollision(position) || checkPlayerCollision(position);
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
        for (Map.Entry<Integer, List<Wall>> entry : doors.entrySet()) {
            List<Wall> doors = entry.getValue();
            for(Wall door : doors) {
                if(position.equals(door.getPosition())) return true;
            }
        }
        return false;
    }

    public int getDoor(Vector position) {
        for (Map.Entry<Integer, List<Wall>> entry : doors.entrySet()) {
            Integer num = entry.getKey();
            List<Wall> doors = entry.getValue();
            for(Wall door : doors) {
                if(position.equals(door.getPosition())) return num;
            }
        }
        return 0;
    }

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

    public Map<Integer, List<Wall>> getDoors() { return doors; }    //make a class for it

    public List<Monster> getMonsters() { return monsters; }

    public void setPlayer(Player player) { this.player = player; }

    public void setWalls(List<Wall> walls) { this.walls = walls; }

    public void setDoors(Map<Integer, List<Wall>> doors) { this.doors = doors; }

    public void setMonsters(List<Monster> monsters) { this.monsters = monsters; }

    public  void setLasers(List<Laser> lasers) { this.lasers = lasers; }

    public List<Laser> getLasers() { return lasers; }
}
