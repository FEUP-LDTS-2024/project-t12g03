package projectt12g03.model.elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import projectt12g03.Game;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.Vector;

public class Player extends Element implements Movable{
    public Player(double x, double y) {
        super(x, y);
    }

    public void moveUp(){
        Vector newposition = new Vector(getPosition().getX(), getPosition().getY() - 1 );
        if(canMove(newposition)){ setPosition(newposition);
            System.out.println("Moved up to: " + newposition.getX() + ", " + newposition.getY());}

    }

    public void moveDown(){
        Vector newposition = new Vector(getPosition().getX(), getPosition().getY() + 1 );
        if(canMove(newposition)){ setPosition(newposition);
            System.out.println("Moved down to: " + newposition);
            System.out.println("Moved down to: " + newposition.getX() + ", " + newposition.getY());
        }
    }

    public void moveLeft(){
        System.out.println("Left");
        Vector newposition = new Vector(getPosition().getX() - 1, getPosition().getY());
        if(canMove(newposition)){ setPosition(newposition);
            System.out.println("Moved left to: " + newposition.getX() + ", " + newposition.getY());}
    }

    public void moveRight(){
        System.out.println("Right");
        Vector newposition = new Vector(getPosition().getX() + 1, getPosition().getY());
        if(canMove(newposition)){ setPosition(newposition);
            System.out.println("Moved right to: " + newposition.getX() + ", " + newposition.getY());}
    }

    public boolean canMove(Vector position){
        return true; //temp while no other elements
    }
}
