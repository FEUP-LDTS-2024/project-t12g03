package projectt12g03.model.elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import projectt12g03.Game;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.Vector;

public class Player extends Element implements Movable {
    public Player(double x, double y) {
        super(x, y);
    }

    public void moveUp() {
        Vector newposition = new Vector(getPosition().getX(), getPosition().getY() - 1);
        if (canMove(newposition)) {
            setPosition(newposition);
        }

    }

    public void moveDown() {
        Vector newposition = new Vector(getPosition().getX(), getPosition().getY() + 1);
        if (canMove(newposition)) {
            setPosition(newposition);
        }
    }

    public void moveLeft() {
        Vector newposition = new Vector(getPosition().getX() - 1, getPosition().getY());
        if (canMove(newposition)) {
            setPosition(newposition);
        }
    }

    public void moveRight() {
        Vector newposition = new Vector(getPosition().getX() + 1, getPosition().getY());
        if (canMove(newposition)) {
            setPosition(newposition);
        }
    }

    public boolean canMove(Vector position) {
        return true; //temp while no other elements
    }
}
