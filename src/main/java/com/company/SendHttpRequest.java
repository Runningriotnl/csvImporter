package com.company;

import com.company.XelionObjects.Operation;
import com.company.user.User;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SendHttpRequest {
    private String baseUrl;
    private String authToken = "xelion 1a08d7d8d7def0ac8835abecc0892ef0ac8835abecc08924d664ba06ba4247bb06a8415c58bdd1975025f9f7ff4096404c5c2b5a1c7e8050e2f225d99965c2d7330ae721cf0ceaad6b468a62834f4d48a0e0150fce6cead224712b8c7668f3eabaacf38ac36ff24";
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
        Operation operationAdd = new Operation();
        Operation operationReplace = new Operation(extensionNumber);
        List<Operation> operations = new ArrayList<>();
        operations.add(operationAdd);
        operations.add(operationReplace);
        try {
            logger.info("The gson build json thing: " + gson.toJson(operationAdd));
            jsonResponse = Unirest.patch(apiUrl).header("Authorization", authToken).body(gson.toJson(operationAdd)).asJson();
            logger.info("The gson build json thing: " + gson.toJson(operationReplace));
            jsonResponse = Unirest.patch(apiUrl).header("Authorization", authToken).body(gson.toJson(operationReplace)).asJson();
            logger.info("Status code: " + jsonResponse.getStatus() + " status text: " + jsonResponse.getStatusText());
            Type objectType = new TypeToken<Data<Addressable>>() {}.getType();
            logger.info("Raw body response: " + jsonResponse.getBody().toString());
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
