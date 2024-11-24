package projectt12g03.view.game.elements;

import projectt12g03.gui.GUI;

import java.util.List;

//Definitly going to change to an interface but cant figure it out right now how to right now
public abstract class ElementDisplayer<T> {
    void draw(T model, GUI gui) {};
    void draw(List<T> model, GUI gui) {};
}
