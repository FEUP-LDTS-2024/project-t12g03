package projectt12g03.state;

import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.LanternaGui;
import projectt12g03.view.ScreenDisplayer;

import java.io.IOException;

public abstract class State<T> {
    private final T model;
    private final Controller<T> controller;
    protected final ScreenDisplayer<T> screenDisplayer;

    public State(T model) throws IOException {
        this.model = model;
        this.controller = getController();
        this.screenDisplayer = getScreenDisplayer();
    }

    protected abstract Controller<T> getController();
    protected abstract ScreenDisplayer<T> getScreenDisplayer() throws IOException;
    protected abstract boolean allowInputSpam();


    public T getModel() {
        return model;
    }


    public void step(Game game, LanternaGui gui, long time) throws IOException {
        game.setKeySpam(allowInputSpam());
        LanternaGui.userInput action = gui.getNextUserInput();
        controller.step(game, action, time);
        screenDisplayer.draw(gui);
    }

}
