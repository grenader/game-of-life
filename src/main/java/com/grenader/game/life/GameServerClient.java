package com.grenader.game.life;

import org.apache.wink.client.*;

import javax.ws.rs.core.MediaType;

/**
 * Created by ikanshyn on 2017-09-20.
 */
public class GameServerClient {

    private String queryUrl = "http://localhost:5000";

    public String post(String body) throws Exception {

        System.out.println("Post. URL: " + queryUrl + " body: " + body);

        RestClient client = createWinkRestClient();
        // create the resource instance to interact with

        Resource resource = client.resource(queryUrl);
        String result = null;
        // perform a GET on the resource. The resource will be returned as plain text
        ClientResponse response;
        try {
            response = resource.contentType(MediaType.APPLICATION_JSON).accept("application/json").post(body);

            result = response.getEntity(String.class);

            System.out.println("postToSolr result = " + result);

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private RestClient createWinkRestClient() {
        ClientConfig config = new ClientConfig();
        //Wink implementation of the rest client instance
        return new RestClient(config);
    }

}
