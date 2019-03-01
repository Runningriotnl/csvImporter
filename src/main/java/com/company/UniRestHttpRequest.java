package com.company;

import com.company.XelionObjects.PatchDocument;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class UniRestHttpRequest implements HttpRequestAssembler{

    private final Logger logger = LoggerFactory.getLogger(UniRestHttpRequest.class);
    private Gson gson = new Gson();
    private String authToken;

    public UniRestHttpRequest(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public String createPostRequest(String apiUrl, Addressable addressable) throws IOException, InterruptedException {
        HttpResponse<String> response;
        String jsonResponse;
        try{
            response = Unirest.post(apiUrl).header("Authorization", authToken).body(gson.toJson(addressable)).asString();
            logger.info("Status code: " + response.getStatus() + " status text: " + response.getStatusText());
            jsonResponse = response.getBody();
            logger.info("Unirest body : " + jsonResponse);
            return jsonResponse;
        } catch (UnirestException e) {
            throw new HttpRequestAssemblerException(e);
        }
    }

    @Override
    public String createPatchRequest(String apiUrl, PatchDocument patchDocument) throws IOException, InterruptedException {
        HttpResponse<String> response;
        String jsonResponse;
        try{
            response = Unirest.patch(apiUrl).header("Authorization", authToken).body(gson.toJson(patchDocument)).asString();
            logger.info("Status code: " + response.getStatus() + " status text: " + response.getStatusText());
            jsonResponse = response.getBody();
            logger.info("Unirest body : " + jsonResponse);
            return jsonResponse;
        } catch (UnirestException e) {
            throw new HttpRequestAssemblerException(e);
        }
    }

    @Override
    public String createGetRequest(String apiUrl, String value) throws IOException, InterruptedException {
        HttpResponse<String> response;
        String jsonResponse;
        try{
            response = Unirest.get(apiUrl).header("Authorization", authToken).asString();
            logger.info("Status code: " + response.getStatus() + " status text: " + response.getStatusText());
            jsonResponse = response.getBody();
            logger.info("Unirest body : " + jsonResponse);
            return jsonResponse;
        } catch (UnirestException e) {
            throw new HttpRequestAssemblerException(e);
        }
    }
}
