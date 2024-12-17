package projectt12g03.model.game.elements.stationary;

import com.googlecode.lanterna.TerminalRectangle;
import projectt12g03.model.game.elements.Element;
import projectt12g03.model.game.room.Room;

import java.awt.*;
import java.util.Objects;

public class Laser extends Element {
    private final int ACTIVE_TIME = 15;
    private final int REST_TIME = 45;

    private boolean isPoitingLeft;
    private Rectangle laserBeamHitbox;
    private String direction;
    private int active;
    private int resting;
    private Room room;

    public Laser(int x, int y,String direction, Room room, boolean isPoitingLeft) {
        super(x, y,0, 0,16, 16);
        setDirection(direction);
        setActive(0);
        setResting(REST_TIME);
        setLaserBeamHitbox();
        this.room = room;
        this.isPoitingLeft = isPoitingLeft;
    }

    public boolean isPointingLeft() { return isPoitingLeft; }

    private void setLaserBeamHitbox() {
        if (direction.equals("LEFT")) laserBeamHitbox = new Rectangle(-1, 5, 1, 6);
        else laserBeamHitbox = new Rectangle(16, 5, 1, 6);
    }

    public String getDirection() {return direction;}

    public void setDirection(String direction) { this.direction = direction;}

    public int getActive() { return active;}

    public void setActive(int active) { this.active = active;}

    public int getResting() { return resting;}

    public void setResting(int resting) { this.resting = resting;}

    public void reduceResting(){this.resting--;}

    public void reduceActive(){this.active--;}

    public void changeMode(String name) {
        if(Objects.equals(name, "ACTIVE")) setActive(ACTIVE_TIME);
        else if(Objects.equals(name, "RESTING")) setResting(REST_TIME);
    }

    public int getRange(){
        return room.getLaserRange(this);
    }

    public Rectangle getLaserBeamHitbox() {
        return laserBeamHitbox;
    }
}
