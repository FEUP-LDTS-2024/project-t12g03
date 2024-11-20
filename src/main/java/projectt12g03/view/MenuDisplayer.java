package projectt12g03.view;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.Menu;
import projectt12g03.model.Vector;

import java.io.IOException;
import java.util.List;

public class MenuDisplayer extends ScreenDisplayer<Menu> {

    public MenuDisplayer(Menu model) { super(model); }

    @Override
    public void draw(LanternaGui gui) throws IOException {
        gui.clear();
        gui.drawText(new Vector(getModel().getxAlignment(), 5), "Menu", new TextColor.RGB(24, 112, 96));
        drawEntries(gui, getModel().getEntries());
        gui.refresh();
    }

    private void drawEntries(LanternaGui gui, List<String> entries) {
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
