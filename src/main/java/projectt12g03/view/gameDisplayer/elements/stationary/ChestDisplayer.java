package projectt12g03.view.gameDisplayer.elements.stationary;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.game.elements.stationary.Chest;

public class ChestDisplayer {
    TextColor.RGB invisibility = new TextColor.RGB(142, 135, 145);
    TextColor.RGB speed = new TextColor.RGB(0, 168, 230);
    TextColor.RGB range = new TextColor.RGB(255, 129, 25);
    TextColor.RGB immunity = new TextColor.RGB(212, 214, 156);

    public void draw(Chest chest, GUI gui) {
        if (!chest.isOpened())
            gui.drawText(chest.getPosition(), "C", new TextColor.RGB(138, 85, 66));
        else {
            if (chest.getPotion() != null) {
                String effect = chest.getPotion().getEffect();
                switch (effect) {
                    case "invisibility":
                        gui.drawText(chest.getPosition(), "I", invisibility);
                        break;
                    case "speed":
                        gui.drawText(chest.getPosition(), "S", speed);
                        break;
                    case "range":
                        gui.drawText(chest.getPosition(), "R", range);
                        break;
                    case "immunity":
                        gui.drawText(chest.getPosition(), "G", immunity);
                        break;

                }
            }
        }
    }
}
