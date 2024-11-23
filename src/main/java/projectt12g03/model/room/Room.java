package projectt12g03.model.room;

import projectt12g03.Game;
import projectt12g03.model.Vector;
import projectt12g03.model.elements.Player;
import projectt12g03.model.elements.Wall;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private Player player;
    private List<Wall> walls;

    //hard coded for now for testing and implementing
    public Room(){
        player = new Player(10,5);
        walls = createWalls();
    }

    private List<Wall> createWalls() { //Refactor later, helper in developing collision
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < Game.terminalWidth; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, Game.terminalHeight - 1));
        }
        for (int r = 1; r < Game.terminalHeight - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(Game.terminalWidth - 1, r));
        }
        return walls;
    }

    public boolean checkWallCollision(Vector position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position))
                return false;
        return true;
    }

    public Player getPlayer() { return player; }

    public List<Wall> getWalls() { return walls; }

    public void setPlayer(Player player) { this.player = player; }

    public void setWalls(List<Wall> walls) { this.walls = walls; }
}
