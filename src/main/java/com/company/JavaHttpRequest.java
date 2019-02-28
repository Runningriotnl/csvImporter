package com.company;

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
    private String apiUrl;
    private String authToken;
    private HttpClient httpClient = HttpClient.newBuilder().build();
    private HttpRequest request;
    private HttpResponse<String> response;

    public JavaHttpRequest(String apiUrl, String authToken) {
        this.apiUrl = apiUrl;
        this.authToken = authToken;
    }

    @Override
    public String createPostRequest(Addressable addressable) throws IOException, InterruptedException {
        request = HttpRequest.newBuilder().uri(URI.create(apiUrl)).header("Authorization", authToken).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(addressable))).build();
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        logger.info("Response status code: " + response.statusCode());

        logger.info("Response headers: " + response.headers());

        logger.info("Response body: " + response.body());

        String jsonResponse = gson.toJson(response.body());

        return jsonResponse;
    }
}
