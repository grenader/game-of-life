package com.grenader.game.life;

public class GameLogic {


    boolean isAlive(boolean state, int numberOfLiveNeighbours) {
        if (state) {
            if (numberOfLiveNeighbours < 2) return false;
            if (numberOfLiveNeighbours > 3) return false;
            return true;
        }
        if (!state) {
            if (numberOfLiveNeighbours == 3) return true;
            return false;
        }
        return false;
    }

    int getLiveNeighbours(byte[][] world, int x, int y) {
        int res = 0;
        if (y > 0) {
            for (int ii = Math.max(x - 1, 0); ii <= x + 1 && ii <= 24; ii++)
                if (world[ii][y - 1] == 1) res++;
        }
        if (y < 24) {
            for (int ii = Math.max(x - 1, 0); ii <= x + 1 && ii <= 24; ii++)
                if (world[ii][y + 1] == 1) res++;
        }
        if (x > 0)
            if (world[x - 1][y] == 1) res++;
        if (x < 24)
            if (world[x + 1][y] == 1) res++;

        return res;
    }

}
