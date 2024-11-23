package projectt12g03.view;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.elements.Player;

import java.io.IOException;

public class PlayerDisplayer extends ScreenDisplayer<Player> {

    public PlayerDisplayer(Player model) { super(model); }

    @Override
    public void draw(GUI gui) throws IOException {
        gui.drawPlayer(getModel().getPosition(), 'P', new TextColor.RGB(85,201,81));
    }
}
