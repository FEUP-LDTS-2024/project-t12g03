package projectt12g03.view.menu;

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
    public void draw(GUI gui) throws IOException {
        gui.clear();
        gui.drawText(new Vector(16, 3),
                "PAUSE MENU",
                new TextColor.RGB(24, 112, 96));
        drawEntries( gui, getModel().getEntries());
        gui.refresh();
    }

    private void drawEntries(GUI gui, List<String> entries) {
        for (String entry : entries) {
            Vector position = getModel().getEntryPosition(entry);
            gui.drawText(
                    position,
                    entry,
                    getModel().getCurrentEntry().equals(entry) ?
                            new TextColor.RGB(24, 112, 96) :
                            new TextColor.RGB(9, 59, 50)
            );
        }
    }

}
