package projectt12g03.model.game.elements.stationary;

import projectt12g03.model.game.elements.Element;

public class Door extends Element {
    private final int doorNumber;

    public Door(int x, int y, int dx, int dy, int width, int height, int doorNumber) {
        super(x, y,dx,dy,width,height);
        this.doorNumber = doorNumber;
    }

    public int getNumber() { return this.doorNumber; }

}
