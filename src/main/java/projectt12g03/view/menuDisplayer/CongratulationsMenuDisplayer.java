package projectt12g03.view.menuDisplayer;

import projectt12g03.gui.GUI;
import projectt12g03.model.menu.CongratulationsMenu;
import projectt12g03.view.ScreenDisplayer;

import java.io.IOException;

public class CongratulationsMenuDisplayer extends ScreenDisplayer<CongratulationsMenu> {
    public CongratulationsMenuDisplayer(CongratulationsMenu model) { super(model); }

    public void draw(GUI gui, long frameCount) throws IOException {
        gui.clear();
        gui.refresh();
    }
}
