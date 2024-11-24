package projectt12g03.view.game.elements;

import projectt12g03.gui.GUI;
import projectt12g03.model.game.elements.Wall;
import projectt12g03.view.ScreenDisplayer;

import java.io.IOException;

public class WallDisplayer extends ScreenDisplayer<Wall> {

    public WallDisplayer(Wall model) { super(model); }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.drawWall(getModel().getPosition());
    }
}
