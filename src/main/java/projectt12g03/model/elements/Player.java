package projectt12g03.model.elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import projectt12g03.Game;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.Vector;

public class Player extends Element {
    private char design; //char while characters aren't more complex
    private TextColor color;

    public Player(double x, double y) {
        super(x, y);
        setDesign('P');
        setColor(new TextColor.RGB(85,201,81));
    }

    public Player(double x, double y, char design, TextColor color){ // For future use if needed
        super(x,y);
        setDesign(design);
        setColor(color);
    }

    public void move(Vector newposition) { setPosition(newposition); }

    public void setColor(TextColor color) { this.color = color; }

    public void setDesign(char design) { this.design = design; }

    public char getDesign() { return design; }

    public TextColor getColor() { return color; }
}

