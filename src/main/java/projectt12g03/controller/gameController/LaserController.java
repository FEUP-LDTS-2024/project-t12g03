package projectt12g03.controller.gameController;

import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.GUI;
import projectt12g03.model.game.elements.Laser;
import projectt12g03.model.game.room.RoomManager;

import java.io.IOException;

public class LaserController extends Controller<RoomManager> {
    public LaserController(RoomManager model) {
        super(model);
    }

    @Override
    public void step(Game game, GUI.userInput action, long time) throws IOException {
        for(Laser laser : getModel().getRoom().getLasers()){
            if (laser.getActive() > 0){
                laser.reduceActive();
                if (laser.getActive() == 0) laser.changeMode("RESTING");
            }
            else if (laser.getResting() > 0){
                laser.reduceResting();
                if (laser.getResting() == 0) laser.changeMode("ACTIVE");
            }

        }
    }
}
