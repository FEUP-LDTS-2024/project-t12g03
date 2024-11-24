package projectt12g03.view.menuDisplayer;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.menu.Menu;
import projectt12g03.model.Vector;
import projectt12g03.view.ScreenDisplayer;

import java.io.IOException;
import java.util.List;

//This class needs to be deleted or generalized to an abstract function
//Only reason i am not making it an abstract function is sometimes we have different draws now (find solution later)
public class MenuDisplayer extends ScreenDisplayer<Menu> {

    public MenuDisplayer(Menu model) { super(model); }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.clear();
        gui.drawText(new Vector(18, 2),
                "GAME",
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
