package projectt12g03.controller.gameController;

import projectt12g03.Game;
import projectt12g03.controller.Controller;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.movable.player.Player;
import projectt12g03.model.game.room.RoomManager;
import projectt12g03.model.menu.VictoryMenu;
import projectt12g03.model.menu.GameOverMenu;
import projectt12g03.state.menuState.VictoryMenuState;
import projectt12g03.state.menuState.GameOverMenuState;

import java.io.IOException;

public class PlayerController extends Controller<RoomManager> {
    private final GameOverMenuState gameOverMenuState;  //helps with tests
    private final int KEY_DELAY = 10;

    private int delay;

    public PlayerController(RoomManager model) throws IOException {
        super(model);
        this.gameOverMenuState = new GameOverMenuState(new GameOverMenu());
    }

    @Override
    public void step(Game game, LanternaGui.userInput action, long time) throws IOException {
        Player player = getModel().getPlayer();
        Vector nextPosition = null;

        updateCoolDowns(player);

        if (player.getIsAttacking() > 0) {  //player cant move when attacking
            getModel().getRoom().checkAttackCollision();
        }

        else {
            switch (action) {
                case UP:
                    nextPosition = player.moveUp();
                    player.setDirection("UP");
                    player.setState("WALKING");
                    break;
                case DOWN:
                    nextPosition = player.moveDown();
                    player.setDirection("DOWN");
                    player.setState("WALKING");
                    break;
                case LEFT:
                    nextPosition = player.moveLeft();
                    player.setDirection("LEFT");
                    player.setState("WALKING");
                    break;
                case RIGHT:
                    nextPosition = player.moveRight();
                    player.setDirection("RIGHT");
                    player.setState("WALKING");
                    break;
                case ATTACK:
                    player.setState("ATTACKING");
                    player.attack();
                    break;
                case SPRINT:
                    if (delay == 0) {
                        player.sprint();
                        delay = KEY_DELAY;
                    }
                    break;
                case HEAL:
                    player.heal();
                    break;
                default:
                    player.setState("IDLE");
                    break;
            }

            handlePlayerMovement(nextPosition, game);
        }
    }

    public void updateCoolDowns(Player player) {
        if (delay > 0)
            delay--;

        if (player.getIsAttacking() > 0) {
            player.reduceIsAttacking();
        }

        if (player.isSprinting()) {
            player.reduceEnergy();
        }

        if (player.getEnergy() < 800)
            player.increaseEnergy(2);

        if (player.getImmunity() > 0)
            player.reduceImmunity();

        player.updatePotionDuration();
    }

    public void handlePlayerMovement(Vector nextPosition, Game game) throws IOException {
        if (nextPosition == null) {
            return;
        }
        if (getModel().getPlayer().canMove(nextPosition)) {
            getModel().getPlayer().setPosition(nextPosition);
            return;
        }
        if (!getModel().getRoom().isAtTransition(getModel().getPlayer(), nextPosition)) {
            return;
        }
        int doorNumber = getModel().getRoom().getDoorNumber(nextPosition);
        if (doorNumber != 0) {
            getModel().loadRoom(doorNumber); // Transition to the next room
        }
        else {
            chooseScreen(game); // end on a win or on a loss
        }
    }

    public void chooseScreen(Game game) throws IOException {
        game.popState();

        if (getModel().getTotalMonsters() > getModel().getCurrentMonsters()) {
            gameOverMenuState.getModel().setType("LIVED_KILLED");
            game.setState(gameOverMenuState);
        }
        else {
            game.setState(new VictoryMenuState(new VictoryMenu()));
        }
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay() {
        this.delay = KEY_DELAY;
    }

    public GameOverMenuState getGameOverMenuState() {
        return gameOverMenuState;
    }
}
