package projectt12g03.view;

import com.googlecode.lanterna.TextColor;
import org.w3c.dom.Text;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.elements.Wall;

import java.io.IOException;

public class WallDisplayer extends ScreenDisplayer<Wall> {

    public WallDisplayer(Wall model) { super(model); }

    @Override
    public void draw(LanternaGui gui) throws IOException {
        gui.drawCharacter(getModel().getPosition(),'#', new TextColor.RGB(255,255,255));
    }
}
