package projectt12g03.types.elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import projectt12g03.gui.LanternaGui;
import projectt12g03.types.Vector;

public class Player extends Element {
    private final double velocity = 0.75;
    public Player(double x, double y) {
        super(x, y);
    }

    @Override
    public void draw(TextGraphics screen) {
        screen.setBackgroundColor(new TextColor.RGB(28, 28, 46));
        screen.fillRectangle(new TerminalPosition((int) getPosition().getX(), (int) getPosition().getY()), new TerminalSize(1, 1), ' ');
    }

    public void move(LanternaGui.userInput action) {
        switch (action) {
            case LEFT:
                setPosition(new Vector(getPosition().getX()-velocity, getPosition().getY()));
                break;
            case RIGHT:
                setPosition(new Vector(getPosition().getX()+velocity, getPosition().getY()));
                break;
            case UP:
                setPosition(new Vector(getPosition().getX(), getPosition().getY()-velocity));
                break;
            case DOWN:
                setPosition(new Vector(getPosition().getX(), getPosition().getY()+velocity));
                break;
            case QUIT:
                System.exit(0);
                break;
            default:
                break;
        }
    }
}
