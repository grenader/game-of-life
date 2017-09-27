package com.grenader.game.life;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameLogicTest {

GameLogic logic = new GameLogic();
    private byte[][] world;

    @Before
    public void setUp() throws Exception {
        world = new byte[25][25];
    }

    @Test
    public void testGetLiveNeigbours_emptyWorld() throws Exception {
        int number = logic.getLiveNeighbours(world,3, 3);
        assertEquals(0, number);
    }

    @Test
    public void testGetLiveNeighbours_two_cells() throws Exception {
        world[2][2] = 1;
        world[4][4] = 1;

        int number = logic.getLiveNeighbours(world,3, 3);
        assertEquals(2, number);
    }

    @Test
    public void testGetLiveNeighbours_corner() throws Exception {
        world[0][1] = 1;
        world[1][0] = 1;

        int number = logic.getLiveNeighbours(world,0, 0);
        assertEquals(2, number);
    }

    @Test
    public void testGetLiveNeighbours_corner_full() throws Exception {
        world[0][1] = 1;
        world[1][0] = 1;
        world[1][1] = 1;

        int number = logic.getLiveNeighbours(world,0, 0);
        assertEquals(3, number);
    }

    @Test
    public void testGetLiveNeighbours_bottom_corner_full() throws Exception {
        world[24][23] = 1;
        world[23][24] = 1;
        world[23][23] = 1;

        int number = logic.getLiveNeighbours(world,24, 24);
        assertEquals(3, number);
    }

    @Test
    public void testGetLiveNeighbours_middle_full() throws Exception {
        world[9][9] = 1;
        world[9][10] = 1;
        world[9][11] = 1;
        world[11][9] = 1;
        world[11][10] = 1;
        world[11][11] = 1;

        world[10][9] = 1;
        world[10][11] = 1;

        int number = logic.getLiveNeighbours(world,10, 10);
        assertEquals(8, number);
    }

    @Test
    public void testIsAlive_liveCell() {
        assertFalse(logic.isAlive(true, 0));
        assertFalse(logic.isAlive(true, 1));

        assertTrue(logic.isAlive(true, 2));
        assertTrue(logic.isAlive(true, 3));

        assertFalse(logic.isAlive(true, 4));
        assertFalse(logic.isAlive(true, 5));
        assertFalse(logic.isAlive(true, 6));
        assertFalse(logic.isAlive(true, 7));
        assertFalse(logic.isAlive(true, 8));
    }

    @Test
    public void testIsAlive_deadCell() {
        assertFalse(logic.isAlive(false, 0));
        assertFalse(logic.isAlive(false, 1));
        assertFalse(logic.isAlive(false, 2));

        assertTrue(logic.isAlive(false, 3));

        assertFalse(logic.isAlive(false, 4));
        assertFalse(logic.isAlive(false, 5));
        assertFalse(logic.isAlive(false, 6));
        assertFalse(logic.isAlive(false, 7));
        assertFalse(logic.isAlive(false, 8));
    }

}