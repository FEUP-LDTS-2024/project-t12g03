package projectt12g03.view;

import projectt12g03.gui.GUI;
import projectt12g03.gui.LanternaGui;

import java.io.IOException;

public abstract class ScreenDisplayer<T> {
    private final T model;

    public ScreenDisplayer(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void draw(GUI gui) throws IOException;
}
