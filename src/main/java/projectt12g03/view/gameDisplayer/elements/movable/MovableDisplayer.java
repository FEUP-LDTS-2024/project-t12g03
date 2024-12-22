package projectt12g03.view.gameDisplayer.elements.movable;

import projectt12g03.view.Sprite;

public abstract class MovableDisplayer {
    public Sprite[] getSpriteSequence(String direction, Sprite[][] sprites) {
        return switch (direction) {
            case "UP" -> sprites[0];
            case "DOWN" -> sprites[1];
            case "LEFT" -> sprites[2];
            case "RIGHT" -> sprites[3];
            default -> throw new RuntimeException("Direction Not Found");
        };
    }
}
