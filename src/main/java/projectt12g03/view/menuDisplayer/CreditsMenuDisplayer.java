package projectt12g03.view.menuDisplayer;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.Vector;
import projectt12g03.model.menu.CreditsMenu;
import projectt12g03.model.menu.MainMenu;
import projectt12g03.view.ScreenDisplayer;
import projectt12g03.view.Sprite;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreditsMenuDisplayer extends MenuDisplayer<CreditsMenu> {
    public CreditsMenuDisplayer(CreditsMenu model) throws IOException { super(model); }

    @Override
    protected Map<Integer, Sprite> createBackGroundMap() throws IOException {
        Map<Integer, Sprite> backGroundMap = new HashMap<>();
        backGroundMap.put(0, new Sprite("sprites/menu/creditsMenu/creditsmenu.png"));
        return backGroundMap;
    }
}
