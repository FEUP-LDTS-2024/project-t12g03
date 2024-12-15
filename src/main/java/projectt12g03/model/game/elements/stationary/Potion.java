package projectt12g03.model.game.elements.stationary;

import projectt12g03.model.game.elements.Element;

import java.util.Random;

public class Potion {
    private String effect;
    private int immunity = 15;

    public Potion() {
        createEffect();
    }

    public int getImmunity() { return immunity; }

    public void reduceImmunity(){ immunity--; }

    private void createEffect() {
        Random random = new Random();
        int someEffect = random.nextInt(4);

        switch (someEffect) {
            case 0:
                effect = "invisibility";
                break;
            case 1:
                effect = "speed";
                break;
            case 2:
                effect = "range";
                break;
            case 3:
                effect = "immunity";
                break;
        }
    }

    public String getEffect() { return effect; }
}
