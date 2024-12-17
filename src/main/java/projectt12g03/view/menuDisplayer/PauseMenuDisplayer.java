package projectt12g03.view.menuDisplayer;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.Vector;
import projectt12g03.model.menu.PauseMenu;
import projectt12g03.view.ScreenDisplayer;

import java.io.IOException;
import java.util.List;

public class PauseMenuDisplayer extends ScreenDisplayer<PauseMenu> {

    public PauseMenuDisplayer(PauseMenu model) { super(model); }

    @Override
    public void draw(GUI gui, long frameCount) throws IOException {
        gui.clear();
        gui.refresh();
    }
}
