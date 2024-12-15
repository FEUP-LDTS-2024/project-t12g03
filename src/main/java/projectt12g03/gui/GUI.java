package projectt12g03.gui;

import com.googlecode.lanterna.TextColor;
import projectt12g03.model.Vector;

import java.io.IOException;

public interface GUI {

    enum userInput {UP, RIGHT, DOWN, LEFT, NONE, PAUSE, SELECT, ATTACK};


    userInput getNextUserInput() throws IOException;

    void drawText(Vector position, String text, TextColor color);

    void drawPixel(double x, double y, TextColor color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;
}
