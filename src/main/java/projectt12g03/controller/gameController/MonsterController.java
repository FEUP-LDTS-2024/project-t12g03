package projectt12g03.controller.gameController;

import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.LanternaGui;
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

            List<String> directions = monster.getDirections();
            SecureRandom rand = new SecureRandom();

            if (directions.isEmpty()) {
                String randomDirection = getRandomDirection(rand);
                int repeatCount = randomDirection.equals("IDLE") ? 24 : 16;
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
        return switch (rand.nextInt(5)) {
            case 0 -> "UP";
            case 1 -> "DOWN";
            case 2 -> "RIGHT";
            case 3 -> "LEFT";
            default -> "IDLE";
        };
    }

    private void executeDirection(Monster monster, String direction) {  //change so it looks better
        switch (direction) {
            case "UP":
                if (monster.canMove(monster.moveUp())) monster.setPosition(monster.moveUp());
                break;
            case "DOWN":
                if (monster.canMove(monster.moveDown())) monster.setPosition(monster.moveDown());
                break;
            case "RIGHT":
                if (monster.canMove(monster.moveRight())) monster.setPosition(monster.moveRight());
                break;
            case "LEFT":
                if (monster.canMove(monster.moveLeft())) monster.setPosition(monster.moveLeft());
                break;
        }
        monster.setDirection(!direction.equals("IDLE") ? direction : monster.getDirection());
        monster.setState(!direction.equals("IDLE") ? "WALKING" : direction);
    }
}
