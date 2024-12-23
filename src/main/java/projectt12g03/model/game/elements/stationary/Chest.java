package projectt12g03.model.game.elements.stationary;

import projectt12g03.model.game.elements.Element;

import java.awt.*;

public class Chest extends Element {
    private boolean unlockable = false;
    private boolean opened = false;
    private Potion potion;

    public Chest(int x, int y) {
        super(x, y);
        this.potion = Potion.createRandomPotion(x,y);
        setHitbox(new Rectangle(0,0,16,16));
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
