package projectt12g03.model;

import projectt12g03.model.game.elements.movable.hostile.Monster;
import projectt12g03.model.game.room.Room;

public class Path {
    private static final int[] DX = {0, 0, 1, -1};
    private static final int[] DY = {-1, 1, 0, 0};


    public static double distance(Vector position, Room room){
        return Math.sqrt(Math.pow(position.getX() - room.getPlayer().getPosition().getX(),2) + Math.pow(position.getY() - room.getPlayer().getPosition().getY(),2));
    }

    public static String transform(int minMove){
        return switch (minMove){
            case 0 -> "UP";
            case 1 -> "DOWN";
            case 2 -> "RIGHT";
            case 3 -> "LEFT";
            case 4 -> "IDLE";
            default -> throw new IllegalStateException("Unexpected value: " + minMove);
        };
    }

    public static String findNextMoveToPlayer(Monster monster){
        Room room = monster.getRoom();
        if(distance(monster.getPosition(),room) > 16*3) return "IDLE";
        double minScore = Integer.MAX_VALUE;
        int minMove = 4;

        for (int i = 0; i < 4; i++) {
            int newX = (int) monster.getPosition().getX() + DX[i];
            int newY = (int) monster.getPosition().getY() + DY[i];
            Vector neighbor = new Vector(newX, newY);
            double distance = distance(neighbor, room);
            if (distance < minScore && monster.canMove(neighbor)){
                minMove = i;
                minScore = distance;
            }
        }

        return transform(minMove);

    }
}
