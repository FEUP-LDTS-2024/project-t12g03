package projectt12g03.view.menuDisplayer;

import projectt12g03.gui.GUI;
import projectt12g03.model.menu.Menu;
import projectt12g03.view.ScreenDisplayer;
import projectt12g03.view.Sprite;

import java.io.IOException;
import java.util.Map;

public abstract class MenuDisplayer<T extends Menu> extends ScreenDisplayer<T> {
    private final Map<Integer, Sprite> backGroundMap;

    public MenuDisplayer(T model) throws IOException {
        super(model);
        this.backGroundMap = createBackGroundMap();
    }

    public void draw(GUI gui, long frameCount) throws IOException {
        gui.clear();
        int entry = getModel().getCurrentEntryNumber();
        Sprite image = backGroundMap.get(entry);
        image.draw(gui, 0, 0);
        gui.refresh();
    }

    protected abstract Map<Integer, Sprite> createBackGroundMap() throws IOException;
}
