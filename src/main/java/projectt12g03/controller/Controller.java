package projectt12g03.controller;

import projectt12g03.Game;
import projectt12g03.gui.LanternaGui;

import java.io.IOException;

public abstract class Controller<T> {
    private T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    //public void setModel(T model) { this.model = model; } maybe we will need it in the future

    public abstract void step(Game game, LanternaGui.userInput action, long time) throws IOException;
}
