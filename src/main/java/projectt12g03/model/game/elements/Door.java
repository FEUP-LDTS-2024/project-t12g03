package projectt12g03.model.game.elements;

public class Door extends Element {
    private int doorNumber;

    public Door(double x, double y, int doorNumber) {
        super(x, y);
        this.doorNumber = doorNumber;
    }

    public int getNumber() { return this.doorNumber; }

}
