package com.company;

import com.google.gson.Gson;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class UniRestHttpRequest implements HttpRequestAssembler{

    private final Logger logger = LoggerFactory.getLogger(UniRestHttpRequest.class);
    private Gson gson = new Gson();
    private String apiUrl;
    private String authToken;

    public UniRestHttpRequest(String apiUrl, String authToken) {
        this.apiUrl = apiUrl;
        this.authToken = authToken;
    }

    @Override
    public String createPostRequest(Addressable addressable) throws IOException, InterruptedException {
        com.mashape.unirest.http.HttpResponse<JsonNode> response;
        String jsonResponse;

        try{
            response = Unirest.post(apiUrl).header("Authorization", authToken).body(gson.toJson(addressable)).asJson();
            logger.info("Status code: " + response.getStatus() + " status text: " + response.getStatusText());
            jsonResponse = gson.toJson(response.getBody());
            return jsonResponse;
        }  catch (UnirestException e) {
            logger.error(e.toString());
        }

        return null;
    }
}
