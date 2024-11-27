package projectt12g03.model.game.room;

import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.Monster;
import projectt12g03.model.game.elements.Player;
import projectt12g03.model.game.elements.Wall;

import java.util.List;

public class Room {
    private final int width;
    private final int height;
    private final int roomCode;

    private Player player;
    private List<Wall> walls;
    private List<Monster> monsters;

    //hard coded for now for testing and implementing
    public Room(int width, int height, int roomCode) {
        this.width = width;
        this.height = height;
        this.roomCode = roomCode;
    }

    public boolean checkCollisions(Vector position) {
        return checkWallCollision(position) || checkMonsterCollision(position);

    }

    public boolean checkMonsterCollisions(Vector position) { // repetitive code, find a way to make it look better
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

    public Player getPlayer() { return player; }

    public List<Wall> getWalls() { return walls; }

    public List<Monster> getMonsters() { return monsters; }

    public void setPlayer(Player player) { this.player = player; }

    public void setWalls(List<Wall> walls) { this.walls = walls; }

    public void setMonsters(List<Monster> monsters) { this.monsters = monsters; }
}
