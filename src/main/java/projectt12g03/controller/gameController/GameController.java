package projectt12g03.controller.gameController;

import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.GUI;
import projectt12g03.model.game.room.RoomManager;
import projectt12g03.model.menu.PauseMenu;
import projectt12g03.state.menuSate.PauseMenuState;
import projectt12g03.model.game.elements.Player;

import java.io.IOException;

public class GameController extends Controller<RoomManager> {
    private final PlayerController playerController;
    private final MonsterController monsterController;
    private final LaserController laserController;

    public GameController(RoomManager roomManager, PlayerController playerController, MonsterController monsterController, LaserController laserController) {
        super(roomManager);

        this.playerController = playerController;
        this.monsterController = monsterController;
        this.laserController = laserController;
    }

    @Override
    public void step(Game game, GUI.userInput action, long time) throws IOException {
        Player player = getModel().getRoom().getPlayer();
        getModel().getRoom().checkRayDamage();
        switch (action) {
            case PAUSE:
                game.setState(new PauseMenuState(new PauseMenu(getModel().getRoom())));
                break;
            default:
                monsterController.step(game, action, time);
                playerController.step(game, action, time);
                laserController.step(game,action,time);
                break;
        }
        if (player.getHealth() == 0) game.popState();
    }
}
