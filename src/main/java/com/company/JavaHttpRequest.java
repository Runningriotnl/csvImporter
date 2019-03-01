package com.company;

import com.company.XelionObjects.PatchDocument;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JavaHttpRequest implements HttpRequestAssembler{
    private final Logger logger = LoggerFactory.getLogger(JavaHttpRequest.class);
    private Gson gson = new Gson();
    private String authToken;
    private HttpClient httpClient = HttpClient.newBuilder().build();
    private HttpRequest request;
    private HttpResponse<String> response;

    public JavaHttpRequest(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public String createPostRequest(String apiUrl, Addressable addressable) throws IOException, InterruptedException {
        request = HttpRequest.newBuilder().uri(URI.create(apiUrl))
            .header("Authorization", authToken)
            .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(addressable)))
            .build();
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        logger.info("Response status code: " + response.statusCode());
        logger.info("Response headers: " + response.headers());
        logger.info("Response body: " + response.body());
        String jsonResponse = response.body();
        logger.info("Jsonresponse string print: " + jsonResponse);
        return jsonResponse;
    }

    @Override
    public String createPatchRequest(String apiUrl, PatchDocument patchDocument) throws IOException, InterruptedException {
        request = HttpRequest.newBuilder().uri(URI.create(apiUrl))
            .header("Authorization", authToken)
            .method("PATCH", HttpRequest.BodyPublishers.ofString(gson.toJson(patchDocument)))
            .build();
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        logger.info("Response status code: " + response.statusCode());
        logger.info("Response headers: " + response.headers());
        logger.info("Response body: " + response.body());
        String jsonResponse = response.body();
        logger.info("Jsonresponse string print: " + jsonResponse);
        return jsonResponse;
    }

    @Override
    public String createGetRequest(String apiUrl, String value) throws IOException, InterruptedException {
        request = HttpRequest.newBuilder().uri(URI.create(apiUrl))
            .header("Authorization", authToken)
            .build();
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        logger.info("Response status code: " + response.statusCode());
        logger.info("Response headers: " + response.headers());
        logger.info("Response body: " + response.body());
        String jsonResponse = response.body();
        logger.info("Jsonresponse string print: " + jsonResponse);
        return jsonResponse;
    }
}
