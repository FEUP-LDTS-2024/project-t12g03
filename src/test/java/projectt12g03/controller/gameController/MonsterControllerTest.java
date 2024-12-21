package projectt12g03.controller.gameController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.Game;
import projectt12g03.controller.Path;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.movable.hostile.Monster;
import projectt12g03.model.game.elements.movable.player.Player;
import projectt12g03.model.game.room.Room;
import projectt12g03.model.game.room.RoomManager;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MonsterControllerTest {

    private Game game;
    private Room room;
    private Monster monster;
    private MonsterController monsterController;
    private Path path;

    @BeforeEach
    public void setUp() {
        RoomManager roomManager = mock(RoomManager.class);
        game = mock(Game.class);
        monster = mock(Monster.class);
        Player player = mock(Player.class);
        room = mock(Room.class);
        path = mock(Path.class);

        when(roomManager.getRoom()).thenReturn(room);
        when(roomManager.getRoom().getMonsters()).thenReturn(List.of(monster));
        when(room.getPlayer()).thenReturn(player);
        when(monster.getDirections()).thenReturn(new ArrayList<>());
        when(monster.getRoom()).thenReturn(room);
        when(monster.canMove(any(Vector.class))).thenReturn(true);

        monsterController = new MonsterController(roomManager, path);

        when(player.getPosition()).thenReturn(new Vector(1, 1));
        when(monster.getPosition()).thenReturn(new Vector(200, 50));
    }

    @Test
    public void stepWithNoMonster() {
        when(room.getMonsters()).thenReturn(new ArrayList<>());

        verify(path, times(0)).findNextMoveToPlayer(monster);
        verify(monster, times(0)).setDirections(anyList());
        verify(monster, times(0)).setDirection(any(String.class));
        verify(monster, times(0)).setState(any(String.class));
    }

    @Test
    public void stepWithIdle() throws Exception {
        when(path.findNextMoveToPlayer(monster)).thenReturn("IDLE");

        monsterController.step(game, LanternaGui.userInput.NONE, 0);

        verify(path, times(1)).findNextMoveToPlayer(monster);
        // not testing the same as the other ones because of randomness and mockito not being able to mock final classes (secure random)
    }

    @Test
    public void stepUp() throws Exception {
        when(path.findNextMoveToPlayer(monster)).thenReturn("UP");

        monsterController.step(game, LanternaGui.userInput.NONE, 0);

        verify(path, times(1)).findNextMoveToPlayer(monster);
        verify(monster, times(2)).setDirections(anyList());
        verify(monster, times(1)).setDirection("UP");
        verify(monster, times(1)).setState("WALKING");
    }

    @Test
    public void stepDown() throws Exception {
        when(path.findNextMoveToPlayer(monster)).thenReturn("DOWN");

        monsterController.step(game, LanternaGui.userInput.NONE, 0);

        verify(path, times(1)).findNextMoveToPlayer(monster);
        verify(monster, times(2)).setDirections(anyList());
        verify(monster, times(1)).setDirection("DOWN");
        verify(monster, times(1)).setState("WALKING");
    }

    @Test
    public void stepLeft() throws Exception {
        when(path.findNextMoveToPlayer(monster)).thenReturn("LEFT");

        monsterController.step(game, LanternaGui.userInput.NONE, 0);

        verify(path, times(1)).findNextMoveToPlayer(monster);
        verify(monster, times(2)).setDirections(anyList());
        verify(monster, times(1)).setDirection("LEFT");
        verify(monster, times(1)).setState("WALKING");
    }

    @Test
    public void stepRight() throws Exception {
        when(path.findNextMoveToPlayer(monster)).thenReturn("RIGHT");

        monsterController.step(game, LanternaGui.userInput.NONE, 0);

        verify(path, times(1)).findNextMoveToPlayer(monster);
        verify(monster, times(2)).setDirections(anyList());
        verify(monster, times(1)).setDirection("RIGHT");
        verify(monster, times(1)).setState("WALKING");
    }

    @Test
    public void randomDirectionIsUp() {
        String direction = monsterController.getRandomDirection(0);

        assertEquals("UP", direction);
    }

    @Test
    public void randomDirectionIsDown() {
        String direction = monsterController.getRandomDirection(1);

        assertEquals("DOWN", direction);
    }

    @Test
    public void randomDirectionIsRight() {
        String direction = monsterController.getRandomDirection(2);

        assertEquals("RIGHT", direction);
    }

    @Test
    public void randomDirectionIsLeft() {
        String direction = monsterController.getRandomDirection(3);

        assertEquals("LEFT", direction);
    }

    @Test
    public void randomDirectionIsIdle() {
        String direction = monsterController.getRandomDirection(4);

        assertEquals("IDLE", direction);
    }

    @Test
    public void executeCanMoveUp() {
        Vector mockVector = mock(Vector.class);
        when(monster.moveUp()).thenReturn(mockVector);
        when(monster.canMove(mockVector)).thenReturn(true);

        monsterController.executeDirection(monster, "UP");

        verify(monster, times(2)).moveUp();
        verify(monster, times(1)).canMove(mockVector);
        verify(monster, times(1)).setPosition(mockVector);
        verify(monster, times(1)).setDirection("UP");
        verify(monster, times(1)).setState("WALKING");
    }

    @Test
    public void executeCanNotMoveUp() {
        Vector mockVector = mock(Vector.class);
        when(monster.moveUp()).thenReturn(mockVector);
        when(monster.canMove(mockVector)).thenReturn(false);

        monsterController.executeDirection(monster, "UP");

        verify(monster, times(1)).moveUp();
        verify(monster, times(1)).canMove(mockVector);
        verify(monster, times(0)).setPosition(mockVector);
        verify(monster, times(1)).setDirection("UP");
        verify(monster, times(1)).setState("WALKING");
    }

    @Test
    public void executeCanMoveDown() {
        Vector mockVector = mock(Vector.class);
        when(monster.moveDown()).thenReturn(mockVector);
        when(monster.canMove(mockVector)).thenReturn(true);

        monsterController.executeDirection(monster, "DOWN");

        verify(monster, times(2)).moveDown();
        verify(monster, times(1)).canMove(mockVector);
        verify(monster, times(1)).setPosition(mockVector);
        verify(monster, times(1)).setDirection("DOWN");
        verify(monster, times(1)).setState("WALKING");
    }

    @Test
    public void executeCanNotMoveDown() {
        Vector mockVector = mock(Vector.class);
        when(monster.moveDown()).thenReturn(mockVector);
        when(monster.canMove(mockVector)).thenReturn(false);

        monsterController.executeDirection(monster, "DOWN");

        verify(monster, times(1)).moveDown();
        verify(monster, times(1)).canMove(mockVector);
        verify(monster, times(0)).setPosition(mockVector);
        verify(monster, times(1)).setDirection("DOWN");
        verify(monster, times(1)).setState("WALKING");
    }

    @Test
    public void executeCanMoveRight() {
        Vector mockVector = mock(Vector.class);
        when(monster.moveRight()).thenReturn(mockVector);
        when(monster.canMove(mockVector)).thenReturn(true);

        monsterController.executeDirection(monster, "RIGHT");

        verify(monster, times(2)).moveRight();
        verify(monster, times(1)).canMove(mockVector);
        verify(monster, times(1)).setPosition(mockVector);
        verify(monster, times(1)).setDirection("RIGHT");
        verify(monster, times(1)).setState("WALKING");
    }

    @Test
    public void executeCanNotMoveRight() {
        Vector mockVector = mock(Vector.class);
        when(monster.moveRight()).thenReturn(mockVector);
        when(monster.canMove(mockVector)).thenReturn(false);

        monsterController.executeDirection(monster, "RIGHT");

        verify(monster, times(1)).moveRight();
        verify(monster, times(1)).canMove(mockVector);
        verify(monster, times(0)).setPosition(mockVector);
        verify(monster, times(1)).setDirection("RIGHT");
        verify(monster, times(1)).setState("WALKING");
    }

    @Test
    public void executeCanMoveLeft() {
        Vector mockVector = mock(Vector.class);
        when(monster.moveLeft()).thenReturn(mockVector);
        when(monster.canMove(mockVector)).thenReturn(true);

        monsterController.executeDirection(monster, "LEFT");

        verify(monster, times(2)).moveLeft();
        verify(monster, times(1)).canMove(mockVector);
        verify(monster, times(1)).setPosition(mockVector);
        verify(monster, times(1)).setDirection("LEFT");
        verify(monster, times(1)).setState("WALKING");
    }

    @Test
    public void executeCanNotMoveLeft() {
        Vector mockVector = mock(Vector.class);
        when(monster.moveLeft()).thenReturn(mockVector);
        when(monster.canMove(mockVector)).thenReturn(false);

        monsterController.executeDirection(monster, "LEFT");

        verify(monster, times(1)).moveLeft();
        verify(monster, times(1)).canMove(mockVector);
        verify(monster, times(0)).setPosition(mockVector);
        verify(monster, times(1)).setDirection("LEFT");
        verify(monster, times(1)).setState("WALKING");
    }

    @Test
    public void executeIdle() {
        Vector mockVector = mock(Vector.class);
        when(monster.getDirection()).thenReturn("IDLE");

        monsterController.executeDirection(monster, "IDLE");

        verify(monster, times(0)).canMove(mockVector);
        verify(monster, times(0)).setPosition(mockVector);
        verify(monster, times(1)).setDirection("IDLE");
        verify(monster, times(1)).setState("IDLE");
    }

    @Test
    public void repeatStepWithDirectionNotEmpty() {
        when(monster.getDirections()).thenReturn(List.of("IDLE","IDLE","IDLE"));

        monsterController.repeatStep(monster, "IDLE", 0);

        verify(monster, times(0)).setDirections(anyList());
    }

    @Test
    public void repeatStepWithDirectionEmptyAndIdle() {
        monsterController.repeatStep(monster, "IDLE", 0);

        verify(monster, times(1)).setDirections(anyList());
    }

    @Test
    public void repeatStepWithDirectionEmptyAndOther() {
        monsterController.repeatStep(monster, "UP", 0);

        verify(monster, times(1)).setDirections(anyList());
    }
}