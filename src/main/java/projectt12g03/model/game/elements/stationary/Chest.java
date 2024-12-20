package projectt12g03.model.game.elements.stationary;

import projectt12g03.model.game.elements.Element;
import projectt12g03.model.game.elements.stationary.potion.Potion;

public class Chest extends Element {
    private boolean unlockable = false;
    private boolean opened = false;
    private Potion potion;

    public Chest(int x, int y) {
        super(x, y,0,0,16,16);
        this.potion = Potion.createRandomPotion(x,y);
    }

    public boolean isUnlockable() { return unlockable; }
    public boolean isOpened() { return opened; }

    public void foundKey() { unlockable = true; }
    public void open() { opened = true; }

    public Potion getPotion() {
        return potion;
    }

    public void setPotion(Potion potion) { this.potion = potion; }
}
