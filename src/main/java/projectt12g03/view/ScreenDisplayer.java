package projectt12g03.view;

import projectt12g03.gui.GUI;

import java.io.IOException;

public abstract class ScreenDisplayer<T> {
    private final T model;

    public ScreenDisplayer(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void draw(GUI gui, long frameCount) throws IOException;
}
