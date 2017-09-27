package com.grenader.game.life;

import java.util.Random;

/**
 * Created by ikanshyn on 2017-09-20.
 */
public class GameServer {

    GameLogic logic = new GameLogic();
    GameServerClient client = new GameServerClient();

    byte[][] world = new byte[25][25];

    String postCell(int x, int y, String color) {
        try {
            return client.post("{\"cells\":[{\"x\":" + x + ",\"y\":" + y + ",\"color\":\"" + color + "\"}]}");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

    public String getRandomColor() {
        // create random object - reuse this as often as possible
        Random random = new Random();

        // create a big random number - maximum is ffffff (hex) = 16777215 (dez)
        int nextInt = random.nextInt(256 * 256 * 256);

        // format it as hexadecimal string (with hashtag and leading zeros)
        String colorCode = String.format("#%06x", nextInt);

        return colorCode;
    }

    public void clearTheWorld() {
        for (int ii = 0; ii < 25; ii++)
            for (int jj = 0; jj < 25; jj++) {
                postCell(ii, jj, "#FFFFFF");
                world[ii][jj] = 0;
            }
    }

    public void recalculateTheWorld() {
        for (int ii = 0; ii < 25; ii++)
            for (int jj = 0; jj < 25; jj++) {
                int numberOfLiveNeighbours = logic.getLiveNeighbours(world, ii, jj);
                boolean alive = logic.isAlive(world[ii][jj] == 1, numberOfLiveNeighbours);
                postCell(ii, jj, alive ? "#AA00CC" : "#FFFFFF");
                world[ii][jj] = (byte) (alive ? 1 : 0);
            }
    }

    public static void mainLoop(String[] args) {
        new GameServer().mainLoop(200);
    }

    public void mainLoop(int count) {
        clearTheWorld();
        initiateTheWorld();

        for (int ii = 0; ii < count; ii++)
            recalculateTheWorld();

    }

    public void initiateTheWorld() {
        for (int ii = 0; ii < 10; ii++)
            for (int jj = 0; jj < 10; jj++) {

                int x = (int) (Math.random() * 100 % 25);
                int y = (int) (Math.random() * 100 % 25);
                System.out.println("x = " + x);
                System.out.println("y = " + y);
                postCell(x, y, "#AA00AA");
                world[x][y] = 1; // alive
            }

    }
}