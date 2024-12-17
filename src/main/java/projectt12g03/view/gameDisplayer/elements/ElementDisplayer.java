package projectt12g03.view.gameDisplayer.elements;

import projectt12g03.gui.GUI;

public interface ElementDisplayer<T> {
    void draw(T model, GUI gui, long frameCount);
}
