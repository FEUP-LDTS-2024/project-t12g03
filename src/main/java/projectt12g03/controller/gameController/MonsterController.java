package projectt12g03.controller.gameController;

import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.Path;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.movable.hostile.Monster;
import projectt12g03.model.game.room.RoomManager;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;

public class MonsterController extends Controller<RoomManager> {

    public MonsterController(RoomManager model) {
        super(model);
    }

    @Override
    public void step(Game game, LanternaGui.userInput action, long time) throws IOException {
        for (Monster monster : getModel().getRoom().getMonsters()) {

            String move = Path.findNextMoveToPlayer(monster);
            if (!move.equals("IDLE")){
                executeDirection(monster,move);
                return;
            }

            List<String> directions = monster.getDirections();
            SecureRandom rand = new SecureRandom();

            if (directions.isEmpty()) {
                String randomDirection = getRandomDirection(rand);
                int repeatCount = randomDirection.equals("IDLE") ? 32 : 16;
                for (int i = 0; i < repeatCount; i++) {
                    directions.add(randomDirection);
                }
            }

            String direction = directions.removeFirst();
            monster.setDirections(directions);
            executeDirection(monster, direction);
        }
    }

    private String getRandomDirection(SecureRandom rand) {
        switch (rand.nextInt(5)) {
            case 0: return "UP";
            case 1: return "DOWN";
            case 2: return "RIGHT";
            case 3: return "LEFT";
            default: return "IDLE";
        }
    }

    private void executeDirection(Monster monster, String direction) {
        switch (direction) {
            case "UP":
                if (monster.canMove(monster.moveUp())) monster.setPosition(monster.moveUp());
                monster.setDirection("UP");
                monster.setState("WALKING");
                break;
            case "DOWN":
                if (monster.canMove(monster.moveDown())) monster.setPosition(monster.moveDown());
                monster.setDirection("DOWN");
                monster.setState("WALKING");
                break;
            case "RIGHT":
                if (monster.canMove(monster.moveRight())) monster.setPosition(monster.moveRight());
                monster.setDirection("RIGHT");
                monster.setState("WALKING");
                break;
            case "LEFT":
                if (monster.canMove(monster.moveLeft())) monster.setPosition(monster.moveLeft());
                monster.setDirection("LEFT");
                monster.setState("WALKING");
                break;
            case "IDLE":
                monster.setState("IDLE");
                break;
        }
    }
}
