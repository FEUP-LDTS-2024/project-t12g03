package projectt12g03.gui;

import com.googlecode.lanterna.TextColor;

import java.io.IOException;

public interface GUI {

    enum userInput {UP, RIGHT, DOWN, LEFT, NONE, PAUSE, SELECT, ATTACK, HEAL, SPRINT, ONE, TWO, THREE, FOUR}

    userInput getNextUserInput() throws IOException;

    void drawPixel(double x, double y, TextColor color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;
}
