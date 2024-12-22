package projectt12g03.view.gameDisplayer.elements.movable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.gui.LanternaGui;
import projectt12g03.model.Vector;
import projectt12g03.model.game.elements.movable.hostile.Monster;
import projectt12g03.view.gameDisplayer.elements.movable.MonsterDisplayer;
import projectt12g03.view.Sprite;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MonsterDisplayerTest {
    private Monster monster;
    private MonsterDisplayer monsterDisplayer;
    private LanternaGui gui;
    private Sprite spriteMock;
    private Sprite spriteMock2;

    @BeforeEach
    void setup() throws Exception {
        monster = mock(Monster.class);
        gui = mock(LanternaGui.class);
        when(monster.getPosition()).thenReturn(new Vector(0, 0));
        monsterDisplayer = spy(new MonsterDisplayer());
        spriteMock = mock(Sprite.class);
        spriteMock2 = mock(Sprite.class);

        monsterDisplayer.getMonsterMap().put("IDLE", new Sprite[][]{
                {spriteMock},
                {spriteMock},
                {spriteMock},
                {spriteMock}
        });

        monsterDisplayer.getMonsterMap().put("WALKING", new Sprite[][]{
                {spriteMock, spriteMock2},
                {spriteMock, spriteMock2},
                {spriteMock, spriteMock2},
                {spriteMock, spriteMock2}
        });
    }

    @Test
    void testDrawIdleUp() {
        when(monster.getState()).thenReturn("IDLE");
        when(monster.getDirection()).thenReturn("UP");

        monsterDisplayer.draw(monster, gui, 0);

        verify(spriteMock, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testDrawIdleDown() {
        when(monster.getState()).thenReturn("IDLE");
        when(monster.getDirection()).thenReturn("DOWN");

        monsterDisplayer.draw(monster, gui, 0);

        verify(spriteMock, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testDrawIdleLeft() {
        when(monster.getState()).thenReturn("IDLE");
        when(monster.getDirection()).thenReturn("LEFT");

        monsterDisplayer.draw(monster, gui, 0);

        verify(spriteMock, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testDrawIdleRight() {
        when(monster.getState()).thenReturn("IDLE");
        when(monster.getDirection()).thenReturn("RIGHT");

        monsterDisplayer.draw(monster, gui, 0);

        verify(spriteMock, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testDrawWalkingUp() {
        when(monster.getState()).thenReturn("WALKING");
        when(monster.getDirection()).thenReturn("UP");

        long frameCount = 4;

        when(monster.getPosition()).thenReturn(new Vector(0, 0));

        monsterDisplayer.draw(monster, gui, frameCount);

        verify(spriteMock, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testDrawWalkingUpCycle() {
        when(monster.getState()).thenReturn("WALKING");
        when(monster.getDirection()).thenReturn("UP");

        long frameCount = 5;

        when(monster.getPosition()).thenReturn(new Vector(0, 0));

        monsterDisplayer.draw(monster, gui, frameCount);

        verify(spriteMock2, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testDrawWalkingDown() {
        when(monster.getState()).thenReturn("WALKING");
        when(monster.getDirection()).thenReturn("DOWN");

        long frameCount = 4;

        when(monster.getPosition()).thenReturn(new Vector(0, 0));

        monsterDisplayer.draw(monster, gui, frameCount);

        verify(spriteMock, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testDrawWalkingLeft() {
        when(monster.getState()).thenReturn("WALKING");
        when(monster.getDirection()).thenReturn("LEFT");

        long frameCount = 4;

        when(monster.getPosition()).thenReturn(new Vector(0, 0));

        monsterDisplayer.draw(monster, gui, frameCount);

        verify(spriteMock, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testDrawWalkingRight() {
        when(monster.getState()).thenReturn("WALKING");
        when(monster.getDirection()).thenReturn("RIGHT");

        long frameCount = 4;

        when(monster.getPosition()).thenReturn(new Vector(0, 0));

        monsterDisplayer.draw(monster, gui, frameCount);

        verify(spriteMock, times(1)).draw(eq(gui), eq(0), eq(0));
    }

    @Test
    void testGetSpriteSequenceWithInvalidDirection() throws IOException {
        MonsterDisplayer monsterDisplayer = new MonsterDisplayer();
        Sprite[][] sprites = new Sprite[4][2];

        assertThrows(RuntimeException.class, () -> monsterDisplayer.getSpriteSequence("INVALID_DIRECTION", sprites));
    }
}