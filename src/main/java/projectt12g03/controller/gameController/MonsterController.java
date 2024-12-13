package projectt12g03.controller.gameController;

import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.game.elements.Monster;
import projectt12g03.model.game.room.Room;
import projectt12g03.model.game.room.RoomManager;

import java.io.IOException;
import java.util.Random;

public class MonsterController extends Controller<RoomManager> {
    public MonsterController(RoomManager model) { super(model); }

    @Override
    public void step(Game game, LanternaGui.userInput action, long time) throws IOException {
        Random rand = new Random();
        if(time % game.getFps() == 0) {
            for (Monster monster : getModel().getRoom().getMonsters()) {
                int direction = rand.nextInt(4);

                switch (direction) {
                    case 0:
                        if (monster.canMove(monster.moveUp())) monster.setPosition(monster.moveUp());
                        break;
                    case 1:
                        if (monster.canMove(monster.moveDown())) monster.setPosition(monster.moveDown());
                        break;
                    case 2:
                        if (monster.canMove(monster.moveRight())) monster.setPosition(monster.moveRight());
                        break;
                    case 3:
                        if (monster.canMove(monster.moveLeft())) monster.setPosition(monster.moveLeft());
                        break;
                }
            }
        }
    }
}