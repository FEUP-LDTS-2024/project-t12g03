package projectt12g03.controller.gameController;

import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.game.elements.Monster;
import projectt12g03.model.game.room.Room;

import java.io.IOException;
import java.util.Random;

public class MonsterController extends Controller<Room> {
    public MonsterController(Room model) { super(model); }

    @Override
    public void step(Game game, LanternaGui.userInput action, long time) throws IOException {
        Random rand = new Random();
        if(time % game.getFps() == 0) {
            for (Monster monster : getModel().getMonsters()) {
                int direction = rand.nextInt(4);

                switch (direction) {
                    case 0:
                        monster.setPosition(monster.moveUp());
                        break;
                    case 1:
                        monster.setPosition(monster.moveDown());
                        ;
                        break;
                    case 2:
                        monster.setPosition(monster.moveRight());
                        break;
                    case 3:
                        monster.setPosition(monster.moveLeft());
                        break;
                }
            }
        }
    }
}