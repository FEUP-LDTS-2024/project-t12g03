package projectt12g03.view.menuDisplayer;

import projectt12g03.gui.GUI;
import projectt12g03.model.menu.GameOverMenu;
import projectt12g03.view.ScreenDisplayer;

import java.io.IOException;

public class GameOverMenuDisplayer extends ScreenDisplayer<GameOverMenu> {
    public GameOverMenuDisplayer(GameOverMenu model) { super(model); }

    public void draw(GUI gui, long frameCount) throws IOException {
        gui.clear();
        gui.refresh();
    }
}
