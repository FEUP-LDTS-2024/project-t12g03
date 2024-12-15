package projectt12g03.view.menuDisplayer;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.Vector;
import projectt12g03.model.menu.CreditsMenu;
import projectt12g03.view.ScreenDisplayer;

import java.io.IOException;
import java.util.List;

public class CreditsMenuDisplayer extends ScreenDisplayer<CreditsMenu> {
    public CreditsMenuDisplayer(CreditsMenu model) { super(model); }

    public void draw(GUI gui, long frameCount) throws IOException {
        gui.clear();
        gui.drawText(new Vector(16, 2),
                "CREDITS",
                new TextColor.RGB(24, 112, 96));
        drawNames(gui);
        drawEntries( gui, getModel().getEntries());
        gui.refresh();
    }

    public void drawNames(GUI gui) {
        gui.drawText(new Vector(2, 7), "filipe lopes : ", new TextColor.RGB(255, 255, 255) );
        gui.drawText(new Vector(20, 7), "UP202306564", new TextColor.RGB(9, 59, 96));
        gui.drawText(new Vector(2, 10), "francisco tomaz : ", new TextColor.RGB(255, 255, 255));
        gui.drawText(new Vector(20, 10), "UP202309456", new TextColor.RGB(9, 59, 96));
        gui.drawText(new Vector(2, 13), "joao quental : ", new TextColor.RGB(255, 255, 255));
        gui.drawText(new Vector(20, 13), "UP202307755", new TextColor.RGB(9, 59, 96));
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
