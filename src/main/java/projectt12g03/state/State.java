package projectt12g03.state;

import projectt12g03.Game;
import projectt12g03.gui.LanternaGui;

import java.io.IOException;

public abstract class State<T> {
    private final T model;

    public State(T model) throws IOException {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public void step(Game game, LanternaGui gui, long time) throws IOException {
        LanternaGui.userInput userInput = gui.getNextUserInput();
    }

}
