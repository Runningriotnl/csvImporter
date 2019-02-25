package com.company;

import com.company.XelionObjects.PatchOperation;
import com.company.XelionObjects.PatchDocument;
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
    private String authToken = "xelion 701b0cfb0cfd2b8a7389e7f6bffecd2b8a7389e7f6bffec8605797b27d95c149033dd8cea8bc9c91e33f549efb76fa42a5ae5fd8dc1b0af08e4150bdcc87445f6dee4b20b6df3b2e4ebfe9b1247c2887610a18c4b67c744583e68f911dc8ddff3e30b14f109edec";
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

    public void patchPhoneLineExtension(String phoneLineOID, String extensionNumber) {
        gson = new Gson();
        HttpResponse<JsonNode> jsonResponse;
        String apiUrl = baseUrl + "/api/v1/master/phonelines/" + phoneLineOID;
        PatchDocument patchDocument = new PatchDocument();
        patchDocument.add(PatchOperation.add("/extensions/1"));
        patchDocument.add(PatchOperation.replace("/extensions/1/address", extensionNumber));
        try {
            logger.info(gson.toJson(patchDocument));
            jsonResponse = Unirest.patch(apiUrl).header("Authorization", authToken).body(gson.toJson(patchDocument)).asJson();
            logger.info("Status code: " + jsonResponse.getStatus() + " status text: " + jsonResponse.getStatusText());
        }
        catch (UnirestException e) {
            logger.error(e.toString());
        }
    }

    public String getPhoneLineOID(String userOID) {
        gson = new Gson();
        HttpResponse<JsonNode> jsonResponse;
        String phoneLineOID = "";
        String apiUrl = baseUrl + "/api/v1/master/users/" + userOID;
        try {
            jsonResponse = Unirest.get(apiUrl).header("Authorization", authToken).asJson();
            logger.info("Status code: " + jsonResponse.getStatus() + " status text: " + jsonResponse.getStatusText());
            Type objectType = new TypeToken<Data<Addressable>>() {}.getType();
            Data<Addressable> response = gson.fromJson(jsonResponse.getBody().toString(), objectType);
            phoneLineOID = response.getObject().getPrimaryLine().getOid();
        }
        catch (UnirestException e) {
            logger.error(e.toString());
        }
        return phoneLineOID;
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
