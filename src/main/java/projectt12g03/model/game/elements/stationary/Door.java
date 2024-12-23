package projectt12g03.model.game.elements.stationary;

import projectt12g03.model.game.elements.Element;

import java.awt.*;

public class Door extends Element {
    private final char direction;
    private int doorNumber;

    public Door(int x, int y, char direction, int prevRoom) {
        super(x, y);
        this.direction = direction;
        setHitbox(getDoorHitbox());
        setDoorNumber(prevRoom);
    }

    public int getNumber() { return this.doorNumber; }

    public Rectangle getDoorHitbox() {
        return switch (direction) {
            case 'U' -> new Rectangle(0,0,16,1);
            case 'D' -> new Rectangle(0,15,16,1);
            case 'l' -> new Rectangle(0,0,1,16);    //L was taken for laser
            default -> new Rectangle(15,0,1,16);
        };
    }

    public void setDoorNumber(int prevRoom) {
        switch (direction) {
            case 'U':
                doorNumber = prevRoom + 3;
                break;
            case 'D':
                doorNumber = prevRoom - 3;
                break;
            case 'l':
                doorNumber = prevRoom - 1;
                break;
            case 'R':
                doorNumber = prevRoom + 1;
                break;
            default:
                doorNumber = 0;
                break;
        }
    }
}
