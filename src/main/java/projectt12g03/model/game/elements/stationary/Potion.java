package projectt12g03.model.game.elements.stationary;

import projectt12g03.model.game.elements.Element;

import java.util.Random;

public class Potion {
    private String effect;
    private int duration;
    private int immunity = 15;

    public Potion() {
        createEffect();
    }

    public int getDuration() { return duration; }

    public int getImmunity() { return immunity; }

    public void reduceImmunity(){ immunity--; }

    private void createEffect() {
        Random random = new Random();
        int someEffect = random.nextInt(4);

        switch (someEffect) {
            case 0:
                effect = "invisibility";
                duration = 300;
                break;
            case 1:
                effect = "speed";
                duration = 150;
                break;
            case 2:
                effect = "heal";
                duration = 0;
                break;
            case 3:
                effect = "immunity";
                duration = 300;
                break;
        }
    }

    public String getEffect() { return effect; }
}
