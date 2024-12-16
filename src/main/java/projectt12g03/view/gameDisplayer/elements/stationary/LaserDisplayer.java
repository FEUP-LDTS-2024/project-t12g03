package projectt12g03.view.gameDisplayer.elements.stationary;

import com.googlecode.lanterna.TextColor;
import projectt12g03.gui.GUI;
import projectt12g03.model.Sprite;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.stationary.Laser;

import java.io.IOException;
import java.util.List;

public class LaserDisplayer {
    private final Sprite[] sprite;

    public LaserDisplayer() throws IOException {
        sprite = new Sprite[] {new Sprite("sprites/elements/laser/laserwall_left.png"), new Sprite("sprites/elements/laser/laserwall_right.png"),
                                    new Sprite("sprites/elements/laser/laser_beam.png")};
    }

    public void draw(List<Laser> lasers, GUI gui) {
        for(Laser laser : lasers){
            switch (laser.getDirection()){
                case "RIGHT":
                    sprite[1].draw(gui, laser.getPosition().getX(), laser.getPosition().getY());
                    if (laser.getActive() > 0){
                        for (int i = 0; i <= laser.getRange();  i++){
                            sprite[2].draw(gui, laser.getPosition().getX() + laser.getLaserBeamHitbox().x + i, laser.getPosition().getY() + laser.getLaserBeamHitbox().y);
                        }
                    }
                    break;
                case "LEFT":
                    sprite[0].draw(gui, laser.getPosition().getX(), laser.getPosition().getY());
                    if (laser.getActive() > 0){
                        for (int i = 0; i <= laser.getRange();  i++){
                            sprite[2].draw(gui, laser.getPosition().getX() + laser.getLaserBeamHitbox().x - i, laser.getPosition().getY() + laser.getLaserBeamHitbox().y);
                        }
                    }
                    break;
            }
        }
    }
}
