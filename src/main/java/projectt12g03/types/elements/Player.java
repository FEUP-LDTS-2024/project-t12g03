package projectt12g03.types.elements;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import projectt12g03.gui.LanternaGui;
import projectt12g03.types.Vector;

public class Player extends Element {
    public Player(double x, double y) {
        super(x, y);
    }

    @Override
    public void draw(TextGraphics screen) {
        screen.setCharacter((int) getPosition().getX(), (int) getPosition().getY(), new TextCharacter('█', TextColor.ANSI.YELLOW, TextColor.ANSI.DEFAULT));
        screen.setCharacter((int) getPosition().getX(), (int) (getPosition().getY()-1), new TextCharacter('█', TextColor.ANSI.YELLOW, TextColor.ANSI.DEFAULT));
        screen.setCharacter((int) (getPosition().getX()-1), (int) (getPosition().getY()+1), new TextCharacter('█', TextColor.ANSI.YELLOW, TextColor.ANSI.DEFAULT));
        screen.setCharacter((int) (getPosition().getX()+1), (int) (getPosition().getY()+1), new TextCharacter('█', TextColor.ANSI.YELLOW, TextColor.ANSI.DEFAULT));

    }

    public void move(LanternaGui.userInput action) {
        switch (action) {
            case LEFT:
                System.out.println("Left");
                setPosition(new Vector(getPosition().getX()-1, getPosition().getY()));
                break;
            case RIGHT:
                System.out.println("Right");
                setPosition(new Vector(getPosition().getX()+1, getPosition().getY()));
                break;
            case UP:
                System.out.println("Up");
                setPosition(new Vector(getPosition().getX(), getPosition().getY()-1));
                break;
            case DOWN:
                System.out.println("Down");
                setPosition(new Vector(getPosition().getX(), getPosition().getY()+1));
                break;
            case QUIT:
                System.exit(0);
                break;
            default:
                System.out.println("Running");
                break;
        }
    }
}
