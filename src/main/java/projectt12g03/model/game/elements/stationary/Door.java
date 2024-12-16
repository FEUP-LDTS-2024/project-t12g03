package projectt12g03.model.game.elements.stationary;

import projectt12g03.model.game.elements.Element;

public class Door extends Element {
    private final int doorNumber;

    public Door(int x, int y, int doorNumber) {
        super(x, y,0,0,16,1);
        this.doorNumber = doorNumber;
    }

    public int getNumber() { return this.doorNumber; }

}
