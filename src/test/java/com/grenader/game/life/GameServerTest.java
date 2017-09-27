package com.grenader.game.life;

import org.junit.Test;

/**
 * Created by ikanshyn on 2017-09-20.
 */
public class GameServerTest {

    GameServer server = new GameServer();

    @Test
    public void testPost() throws Exception {
        String postResult = "";
        for (int ii = 0; ii < 10; ii++)
            for (int jj = 0; jj < 10; jj++) {

                int x = (int) (Math.random() * 100 % 25);
                int y = (int) (Math.random() * 100 % 25);
                System.out.println("x = " + x);
                System.out.println("y = " + y);
                postResult = server.postCell(x, y, server.getRandomColor());
            }

        System.out.println("result = " + postResult);

//        assertEquals(200, response.getStatus());
//        assertThat(response.getEntity().toString(), startsWith("{\"responseHeader\":{\"status\":0,\"QTime"));
    }

    @Test
    public void testClearTheWorld() throws Exception {
        server.clearTheWorld();
    }

    @Test
    public void testInitiateTheWorld() throws Exception {
        server.initiateTheWorld();
    }

    @Test
    public void testMain() throws Exception {
        server.mainLoop(3);
    }

}