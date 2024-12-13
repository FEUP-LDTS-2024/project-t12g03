package projectt12g03.model.game.elements;

public class Chest extends Element {
    private boolean unlockable = false;

    public Chest(double x, double y) {
        super(x, y);
    }

    public void foundKey() { this.unlockable = true; }

}
