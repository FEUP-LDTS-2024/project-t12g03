package projectt12g03.controller.gameController;

import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.LanternaGui;
import projectt12g03.controller.Path;
import projectt12g03.model.game.elements.movable.hostile.Monster;
import projectt12g03.model.game.room.RoomManager;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;

public class MonsterController extends Controller<RoomManager> {
    private final Path path;

    public MonsterController(RoomManager model, Path path) {
        super(model);
        this.path = path;
    }

    @Override
    public void step(Game game, LanternaGui.userInput action, long time) throws IOException {
        for (Monster monster : getModel().getRoom().getMonsters()) {

            List<String> directions = monster.getDirections();
            String direction = path.findNextMoveToPlayer(monster);

            if (!direction.equals("IDLE")){
               repeatStep(monster,direction,0);
            }else{
                SecureRandom rand = new SecureRandom();
                int numberOfDirection = rand.nextInt(5);
                direction = getRandomDirection(numberOfDirection);
                repeatStep(monster,direction,12);
            }

            direction = directions.removeFirst();
            monster.setDirections(directions);
            executeDirection(monster, direction);
        }
    }

    public String getRandomDirection(int rand) {
        return switch (rand) {
            case 0 -> "UP";
            case 1 -> "DOWN";
            case 2 -> "RIGHT";
            case 3 -> "LEFT";
            default -> "IDLE";
        };
    }

    public void executeDirection(Monster monster, String direction) {  //change so it looks better
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

    public void repeatStep(Monster monster, String direction, int typeOfMovOffset){
        List<String> directions = monster.getDirections();
        if (directions.isEmpty()) {
            int repeatCount = direction.equals("IDLE") ? 16 : 8 + typeOfMovOffset;
            for (int i = 0; i < repeatCount; i++) {
                directions.add(direction);
            }
            monster.setDirections(directions);
        }
    }
}
