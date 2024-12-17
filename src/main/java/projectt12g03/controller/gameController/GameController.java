package projectt12g03.controller.gameController;

import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.GUI;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.game.elements.Element;
import projectt12g03.model.game.elements.stationary.Chest;
import projectt12g03.model.game.room.RoomManager;
import projectt12g03.model.menu.GameOverMenu;
import projectt12g03.model.menu.Menu;
import projectt12g03.model.menu.PauseMenu;
import projectt12g03.state.menuSate.GameOverMenuState;
import projectt12g03.state.menuSate.PauseMenuState;
import projectt12g03.model.game.elements.movable.player.Player;
import projectt12g03.view.gameDisplayer.elements.ElementDisplayer;

import java.io.IOException;
import java.util.List;

import static projectt12g03.gui.GUI.userInput.PAUSE;

public class GameController extends Controller<RoomManager> {
    private final PlayerController playerController;
    private final MonsterController monsterController;
    private final LaserController laserController;
    private final HudController hudController;

    public GameController(RoomManager roomManager, PlayerController playerController, MonsterController monsterController, LaserController laserController, HudController hudController) {
        super(roomManager);

        this.playerController = playerController;
        this.monsterController = monsterController;
        this.laserController = laserController;
        this.hudController = hudController;
    }

    @Override
    public void step(Game game, GUI.userInput action, long time) throws IOException {

        Player player = getModel().getPlayer();
        Chest chest = getModel().getRoom().getChest();

        if (chest.getPotion() != null && chest.isOpened()) {
            if (chest.getPotion().getImmunity() > 0) chest.getPotion().reduceImmunity();
        }

        if (action == PAUSE)
            game.setState(new PauseMenuState(new PauseMenu()));
        else {
            monsterController.step(game, action, time);
            playerController.step(game, action, time);
            laserController.step(game,action,time);

            hudController.step(game, action, time);
        }

        if (player.getHealth() == 0) {
            game.popState();
            game.setState(new GameOverMenuState(new GameOverMenu()));
        }
    }
}
