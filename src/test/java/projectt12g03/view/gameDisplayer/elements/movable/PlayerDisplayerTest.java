package projectt12g03.view.gameDisplayer.elements.movable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.movable.hostile.Monster;
import projectt12g03.model.game.elements.movable.player.Player;
import projectt12g03.view.Sprite;
import projectt12g03.view.gameDisplayer.elements.weapon.SpearDisplayer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class PlayerDisplayerTest {
    private Player player;
    private PlayerDisplayer playerDisplayer;
    private SpearDisplayer spearDisplayer;
    private LanternaGui gui;
    private Sprite idleSprite;
    private Sprite attackingSprite;
    private Sprite walkingSprite1;
    private Sprite walkingSprite2;
    private Sprite runningSprite1;
    private Sprite runningSprite2;

    @BeforeEach
    void setup() throws Exception {
        player = mock(Player.class);
        spearDisplayer = mock(SpearDisplayer.class);
        gui = mock(LanternaGui.class);

        when(player.getPosition()).thenReturn(new Vector(0, 0));

        playerDisplayer = spy(new PlayerDisplayer());

        playerDisplayer.setSpearDisplayer(spearDisplayer);

        idleSprite = mock(Sprite.class);
        attackingSprite = mock(Sprite.class);
        walkingSprite1 = mock(Sprite.class);
        walkingSprite2 = mock(Sprite.class);
        runningSprite1 = mock(Sprite.class);
        runningSprite2 = mock(Sprite.class);

        playerDisplayer.getPlayerMap().put("IDLE", new Sprite[][]{
                {idleSprite},
                {idleSprite},
                {idleSprite},
                {idleSprite}
        });

        playerDisplayer.getPlayerMap().put("WALKING", new Sprite[][]{
                {walkingSprite1, walkingSprite2},
                {walkingSprite1, walkingSprite2},
                {walkingSprite1, walkingSprite2},
                {walkingSprite1, walkingSprite2}
        });

        playerDisplayer.getPlayerMap().put("ATTACKING", new Sprite[][]{
                {attackingSprite},
                {attackingSprite},
                {attackingSprite},
                {attackingSprite}
        });

        playerDisplayer.getPlayerMap().put("RUNNING", new Sprite[][]{
                {runningSprite1, runningSprite2},
                {runningSprite1, runningSprite2},
                {runningSprite1, runningSprite2},
                {runningSprite1, runningSprite2}
        });
    }

    @Test
    void testDrawIdleUp() {
        when(player.getState()).thenReturn("IDLE");
        when(player.getDirection()).thenReturn("UP");

        playerDisplayer.draw(player, gui, 0);

        verify(idleSprite, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testDrawIdleDown() {
        when(player.getState()).thenReturn("IDLE");
        when(player.getDirection()).thenReturn("DOWN");

        playerDisplayer.draw(player, gui, 0);

        verify(idleSprite, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testDrawIdleLeft() {
        when(player.getState()).thenReturn("IDLE");
        when(player.getDirection()).thenReturn("LEFT");

        playerDisplayer.draw(player, gui, 0);

        verify(idleSprite, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testDrawIdleRight() {
        when(player.getState()).thenReturn("IDLE");
        when(player.getDirection()).thenReturn("RIGHT");

        playerDisplayer.draw(player, gui, 0);

        verify(idleSprite, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testDrawWalkingUp() {
        when(player.getState()).thenReturn("WALKING");
        when(player.getDirection()).thenReturn("UP");

        long frameCount = 0;

        when(player.getPosition()).thenReturn(new Vector(0, 0));

        playerDisplayer.draw(player, gui, frameCount);

        verify(walkingSprite1, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testDrawWalkingUpCycle() {
        when(player.getState()).thenReturn("WALKING");
        when(player.getDirection()).thenReturn("UP");

        long frameCount = 3;

        when(player.getPosition()).thenReturn(new Vector(0, 0));

        playerDisplayer.draw(player, gui, frameCount);

        verify(walkingSprite2, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testDrawWalkingDown() {
        when(player.getState()).thenReturn("WALKING");
        when(player.getDirection()).thenReturn("DOWN");

        long frameCount = 0;

        when(player.getPosition()).thenReturn(new Vector(0, 0));

        playerDisplayer.draw(player, gui, frameCount);

        verify(walkingSprite1, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testDrawWalkingLeft() {
        when(player.getState()).thenReturn("WALKING");
        when(player.getDirection()).thenReturn("LEFT");

        long frameCount = 0;

        when(player.getPosition()).thenReturn(new Vector(0, 0));

        playerDisplayer.draw(player, gui, frameCount);

        verify(walkingSprite1, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testDrawWalkingRight() {
        when(player.getState()).thenReturn("WALKING");
        when(player.getDirection()).thenReturn("RIGHT");

        long frameCount = 0;

        when(player.getPosition()).thenReturn(new Vector(0, 0));

        playerDisplayer.draw(player, gui, frameCount);

        verify(walkingSprite1, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testDrawRunningUp() {
        when(player.getState()).thenReturn("RUNNING");
        when(player.getDirection()).thenReturn("UP");

        long frameCount = 0;

        when(player.getPosition()).thenReturn(new Vector(0, 0));

        playerDisplayer.draw(player, gui, frameCount);

        verify(runningSprite1, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testDrawtestDrawRunningUpCycle() {
        when(player.getState()).thenReturn("RUNNING");
        when(player.getDirection()).thenReturn("UP");

        long frameCount = 1;

        when(player.getPosition()).thenReturn(new Vector(0, 0));

        playerDisplayer.draw(player, gui, frameCount);

        verify(runningSprite2, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testDrawtestDrawRunningUpDown() {
        when(player.getState()).thenReturn("RUNNING");
        when(player.getDirection()).thenReturn("DOWN");

        long frameCount = 0;

        when(player.getPosition()).thenReturn(new Vector(0, 0));

        playerDisplayer.draw(player, gui, frameCount);

        verify(runningSprite1, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testDrawtestDrawRunningUpLeft() {
        when(player.getState()).thenReturn("RUNNING");
        when(player.getDirection()).thenReturn("LEFT");

        long frameCount = 0;

        when(player.getPosition()).thenReturn(new Vector(0, 0));

        playerDisplayer.draw(player, gui, frameCount);

        verify(runningSprite1, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testDrawtestDrawRunningUpRight() {
        when(player.getState()).thenReturn("RUNNING");
        when(player.getDirection()).thenReturn("RIGHT");

        long frameCount = 0;

        when(player.getPosition()).thenReturn(new Vector(0, 0));

        playerDisplayer.draw(player, gui, frameCount);

        verify(runningSprite1, times(1)).draw(eq(gui), eq(0), eq(0));
    }


    @Test
    void testDrawAttackUp() {
        when(player.getState()).thenReturn("ATTACKING");
        when(player.getDirection()).thenReturn("UP");

        playerDisplayer.draw(player, gui, 0);

        verify(spearDisplayer, times(1)).draw(player, gui);
        verify(attackingSprite, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testDrawAttackDown() {
        when(player.getState()).thenReturn("ATTACKING");
        when(player.getDirection()).thenReturn("DOWN");

        playerDisplayer.draw(player, gui, 0);

        verify(attackingSprite, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testDrawAttackLeft() {
        when(player.getState()).thenReturn("ATTACKING");
        when(player.getDirection()).thenReturn("LEFT");

        playerDisplayer.draw(player, gui, 0);

        verify(attackingSprite, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testDrawAttackRight() {
        when(player.getState()).thenReturn("ATTACKING");
        when(player.getDirection()).thenReturn("RIGHT");

        playerDisplayer.draw(player, gui, 0);

        verify(attackingSprite, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testGetSpriteSequenceWithInvalidDirection() throws IOException {
        MonsterDisplayer monsterDisplayer = new MonsterDisplayer();
        Sprite[][] sprites = new Sprite[4][2];

        assertThrows(RuntimeException.class, () -> monsterDisplayer.getSpriteSequence("INVALID_DIRECTION", sprites));
    }

}