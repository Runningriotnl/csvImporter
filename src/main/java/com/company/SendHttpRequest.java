package com.company;

import com.company.user.UserParserException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public class SendHttpRequest {
    private String baseUrl;
    private String authToken = "xelion 699aa41aa41c83666dfd3763a43f4c83666dfd3763a43f48a92b427e4a83c513f8895b265a9edc3a234750debd2208465d5f1304990b2dc5127240e21d045c40ad1acfdea0a37e52bf3db09f38b18f0f185c65559062ef5870d0b3479c567b39a009ec07a8459d6";
    private Gson gson;
    private Logger logger = LoggerFactory.getLogger(SendHttpRequest.class);

    public SendHttpRequest(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void postAddressableToServer(List<? extends Addressable> objectList) {
        gson = new Gson();
        HttpResponse<JsonNode> jsonResponse;
        String apiUrl = baseUrl + "/api/v1/master/addressables";

        logger.info("Apiurl is: " + apiUrl);

        for(Addressable addressable: objectList) {
            try {
                logger.info(gson.toJson(addressable));
                jsonResponse = Unirest.post(apiUrl).header("Authorization", authToken).body(gson.toJson(addressable)).asJson();
                logger.info("Status code: " + jsonResponse.getStatus() + " status text: " + jsonResponse.getStatusText());
                Type objectType = new TypeToken<Data<Addressable>>() {}.getType();
                logger.info("Raw body response: " + jsonResponse.getBody().toString());
                Data<Addressable> response = gson.fromJson(jsonResponse.getBody().toString(), objectType);
                logger.info(response.getObject().toString());
                addressable.setOid(response.getObject().getOid());

            } catch (UnirestException e) {
                logger.error(e.toString());
            }
        }
    }

    public void postUserToServer(List<? extends Addressable> objectList) {
        gson = new Gson();
        HttpResponse<JsonNode> jsonResponse;
        String apiUrl = baseUrl + "/api/v1/master/users";

        logger.info("Apiurl is: " + apiUrl);

        for(Addressable addressable: objectList) {
            try {
                logger.info(gson.toJson(addressable));
                jsonResponse = Unirest.post(apiUrl).header("Authorization", authToken).body(gson.toJson(addressable)).asJson();
                logger.info("Status code: " + jsonResponse.getStatus() + " status text: " + jsonResponse.getStatusText());
                Type objectType = new TypeToken<Data<Addressable>>() {}.getType();
                logger.info("Raw body response: " + jsonResponse.getBody().toString());
                Data<Addressable> response = gson.fromJson(jsonResponse.getBody().toString(), objectType);
                Optional<Addressable> responseBody = Optional.ofNullable(response.getObject());
                if(responseBody.isPresent()){
                    logger.info(responseBody.toString());
                    addressable.setOid(response.getObject().getOid());
                } else {
                    throw new UserParserException("Could not create user.");
                }

            } catch (UnirestException e) {
                logger.error(e.toString());
            }
        }
    }

    public String getOrganizationOID(String orgName) {
        gson = new Gson();
        HttpResponse<JsonNode> jsonResponse;
        String organizationOID = "";
        String apiUrl = baseUrl + "/api/v1/master/addressables?name=" + orgName;
        try {
            jsonResponse = Unirest.get(apiUrl).header("Authorization", authToken).asJson();
            logger.info("Status code: " + jsonResponse.getStatus() + " status text: " + jsonResponse.getStatusText());
            Type listType = new TypeToken<DataList<Addressable>>() {}.getType();
            DataList<Addressable> response = gson.fromJson(jsonResponse.getBody().toString(), listType);
            List<Addressable> addressableList = response.getData();
            if(addressableList.size() > 0){
                organizationOID = addressableList.get(0).getOid();
            }

        } catch (UnirestException e) {
            logger.error(e.toString());
        }

        return organizationOID;

    }

}
