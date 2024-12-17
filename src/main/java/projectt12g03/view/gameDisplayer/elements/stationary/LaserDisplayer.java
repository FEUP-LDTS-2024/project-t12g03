package projectt12g03.view.gameDisplayer.elements.stationary;

import projectt12g03.gui.GUI;
import projectt12g03.view.Sprite;
import projectt12g03.model.game.elements.stationary.Laser;
import projectt12g03.view.gameDisplayer.elements.ElementDisplayer;

import java.io.IOException;

public class LaserDisplayer implements ElementDisplayer<Laser> {
    private final Sprite[] sprite;

    public LaserDisplayer() throws IOException {
        sprite = new Sprite[] {new Sprite("sprites/elements/laser/laserwall_left.png"), new Sprite("sprites/elements/laser/laserwall_right.png"),
                                    new Sprite("sprites/elements/laser/laser_beam.png")};
    }

    @Override
    public void draw(Laser laser, GUI gui, long frameCount) {
        int direction = laser.isPointingLeft() ? -1 : 1;
        sprite[laser.isPointingLeft() ? 0 : 1].draw(gui, laser.getPosition().getX(), laser.getPosition().getY());
        if (laser.getActive() > 0){
            for (int i = 0; i <= laser.getRange();  i++){
                sprite[2].draw(gui, laser.getPosition().getX() + laser.getLaserBeamHitbox().x + (i * 4 * direction), laser.getPosition().getY() + laser.getLaserBeamHitbox().y);
            }
        }
    }
}
