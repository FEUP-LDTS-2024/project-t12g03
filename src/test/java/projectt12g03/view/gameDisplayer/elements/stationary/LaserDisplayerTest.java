package projectt12g03.view.gameDisplayer.elements.stationary;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projectt12g03.model.Vector;
import projectt12g03.view.Sprite;
import projectt12g03.model.game.elements.stationary.Laser;
import projectt12g03.view.gameDisplayer.elements.stationary.LaserDisplayer;
import projectt12g03.gui.GUI;

import java.awt.*;
import java.io.IOException;

class LaserDisplayerTest {
    private LaserDisplayer laserDisplayer;
    private Laser laser;
    private Sprite mockedSpriteLeft;
    private Sprite mockedSpriteRight;
    private Sprite mockedSpriteBeam;
    private GUI gui;

    @BeforeEach
    void setup() throws IOException {
        mockedSpriteLeft = mock(Sprite.class);
        mockedSpriteRight = mock(Sprite.class);
        mockedSpriteBeam = mock(Sprite.class);

        laser = mock(Laser.class);

        gui = mock(GUI.class);

        laserDisplayer = spy(new LaserDisplayer());

        laserDisplayer.setSprites(new Sprite[]{mockedSpriteLeft, mockedSpriteRight, mockedSpriteBeam});
    }

    @Test
    void testDrawLaserLeft() {
        when(laser.getLaserBeamHitbox()).thenReturn(new Rectangle(0,0,2,2));
        when(laser.isPointingLeft()).thenReturn(true);
        when(laser.getPosition()).thenReturn(new Vector(0, 0));
        when(laser.getActive()).thenReturn(1);
        when(laser.getRange()).thenReturn(2);

        laserDisplayer.draw(laser, gui, 0);

        verify(mockedSpriteLeft, times(1)).draw(gui, 0, 0);

        //checking laser beam at the same time (for mutation killing)
        verify(mockedSpriteBeam, times(1)).draw(eq(gui), eq(0), eq(0));
        verify(mockedSpriteBeam, times(1)).draw(eq(gui), eq(-4), eq(0));
        verify(mockedSpriteBeam, times(1)).draw(eq(gui), eq(-8), eq(0));
    }

    @Test
    void testDrawLaserRight() {
        when(laser.getLaserBeamHitbox()).thenReturn(new Rectangle(0,0,2,2));
        when(laser.isPointingLeft()).thenReturn(false);
        when(laser.getPosition()).thenReturn(new Vector(0, 0));
        when(laser.getActive()).thenReturn(1);
        when(laser.getRange()).thenReturn(2);

        laserDisplayer.draw(laser, gui, 0);

        verify(mockedSpriteRight, times(1)).draw(gui, 0, 0);

        verify(mockedSpriteBeam, times(3)).draw(eq(gui), anyInt(), anyInt());
    }

    @Test
    void testNotDrawLaser() {
        when(laser.getLaserBeamHitbox()).thenReturn(new Rectangle(0,0,2,2));
        when(laser.isPointingLeft()).thenReturn(false);
        when(laser.getPosition()).thenReturn(new Vector(0, 0));
        when(laser.getActive()).thenReturn(0);

        laserDisplayer.draw(laser, gui, 0);

        verify(mockedSpriteRight, times(1)).draw(gui, 0, 0);

        verify(mockedSpriteBeam, times(0)).draw(eq(gui), anyInt(), anyInt());
    }
}