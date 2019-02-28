package com.company;

import com.company.organization.Organization;
import com.google.gson.Gson;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class javaHttpRequestTest {

    private final Logger logger = LoggerFactory.getLogger(com.company.JavaHttpRequest.class);
    private String apiUrl = "http://10.78.40.157/api/v1/master/users";
    private String authToken = "xelion 16110ed10ed729a17583281cdcff0729a17583281cdcff0fb5f95d4f1f7144bcf155635aee21f21e00b73290c86065b3cf86714ca3b40e798364e11f137f23e3e8fd1c0c5c0b690df01f7b13974dc795927e86b4b9285413b502bbbaa008c12b93c0bde34d10cef";
    private HttpClient httpClient = HttpClient.newBuilder().build();
    private HttpRequest request;
    private HttpResponse<String> response;
    private Gson gson = new Gson();

    @Test
    public void getTest() {
        request = HttpRequest.newBuilder().uri(URI.create(apiUrl)).header("Authorization", authToken).build();

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("Response status code: " + response.statusCode());

        logger.info("Response headers: " + response.headers());

        logger.info("Response body: " + response.body());

        //return response;
    }

    @Test
    public void postTest() {

        apiUrl = "http://10.78.40.157/api/v1/master/addressables";
        Organization org = new Organization("TestJavRequest");
        request = HttpRequest.newBuilder().uri(URI.create(apiUrl)).header("Authorization", authToken).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(org))).build();
        logger.info(gson.toJson(org));
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("Response status code: " + response.statusCode());

        logger.info("Response headers: " + response.headers());

        logger.info("Response body: " + response.body());
    }

    @Test
    public void testNewClass() {
        apiUrl = "http://10.78.40.157/api/v1/master/addressables";
        Organization org = new Organization("TestJavRequest");
        JavaHttpRequest javaHttpRequest = new JavaHttpRequest(apiUrl, authToken);
        try {
            String jsonResponse = javaHttpRequest.createPostRequest(org);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNewUniClass() {
        apiUrl = "http://10.78.40.157/api/v1/master/addressables";
        Organization org = new Organization("TestJavRequest");
        UniRestHttpRequest uniRestHttpRequest = new UniRestHttpRequest(apiUrl, authToken);
        try {
            String jsonResponse = uniRestHttpRequest.createPostRequest(org);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}



