package projectt12g03.model.room;

import projectt12g03.model.Vector;
import projectt12g03.model.elements.Player;

public class Room {
    private Player player;

    public Room(){
        player = new Player(10,5); //hard coded for now
    }

    public Player getPlayer() { return player; }
}
