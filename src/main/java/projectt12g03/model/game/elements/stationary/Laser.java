package projectt12g03.model.game.elements.stationary;

import projectt12g03.model.game.elements.Element;
import projectt12g03.model.game.room.Room;

import java.awt.*;
import java.util.Objects;

public class Laser extends Element {
    private final int ACTIVE_TIME = 15;
    private final int REST_TIME = 45;
    private final boolean isPoitingLeft;
    private final Room room;

    private Rectangle laserBeamHitbox;
    private String mode;
    private int active;
    private int resting;

    public Laser(int x, int y, Room room, boolean isPointingLeft) {
        super(x, y);
        this.isPoitingLeft = isPointingLeft;
        setActive(0);
        setResting(REST_TIME);
        setMode("RESTING");
        setLaserBeamHitbox();
        setHitbox(new Rectangle(0, 0,16, 16));
        this.room = room;
    }

    public boolean isPointingLeft() { return isPoitingLeft; }

    private void setLaserBeamHitbox() {
        if (isPointingLeft()) laserBeamHitbox = new Rectangle(-4, 5, 4, 6);
        else laserBeamHitbox = new Rectangle(16, 5, 4, 6);
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getResting() {
        return resting;
    }

    public void setResting(int resting) {
        this.resting = resting;
    }

    public void reduceResting(){
        this.resting--;
    }

    public void reduceActive() {
        this.active--;
    }

    public void changeMode() {
        if(Objects.equals(mode, "ACTIVE")) {
            setResting(REST_TIME);
            setMode("RESTING");
        }
        else if(Objects.equals(mode, "RESTING")) {
            setActive(ACTIVE_TIME);
            setMode("ACTIVE");
        }
    }

    public int getRange(){
        return room.getLaserRange(this);
    }

    public Rectangle getLaserBeamHitbox() {
        return laserBeamHitbox;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
