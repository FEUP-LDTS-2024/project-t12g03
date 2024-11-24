package projectt12g03.view.menuDisplayer;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.Vector;
import projectt12g03.model.menu.ControlsMenu;
import projectt12g03.view.ScreenDisplayer;

import java.io.IOException;
import java.util.List;

public class ControlsMenuDisplayer extends ScreenDisplayer<ControlsMenu> {


    public ControlsMenuDisplayer(ControlsMenu model) { super(model); }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.clear();
        gui.drawText(new Vector(16, 2),
                "CONTROLS",
                new TextColor.RGB(24, 112, 96));
        drawControls(gui);  //controls arent entries so we need to draw separately
        drawEntries(gui, getModel().getEntries());
        gui.refresh();
    }

    //probably a bit hard coded might had something different in the future (map?)
    private void drawControls(GUI gui) {
        gui.drawText(new Vector(3, 8), "WASD / ARROW KEYS - ", new TextColor.RGB(255, 255, 255));
        gui.drawText(new Vector(23, 8), "MOVE PLAYER", new TextColor.RGB(9, 59, 96));
        gui.drawText(new Vector(15, 11), "SPACE - ", new TextColor.RGB(255, 255, 255));
        gui.drawText(new Vector(23, 11), "ATTACK", new TextColor.RGB(9, 59, 96));
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
