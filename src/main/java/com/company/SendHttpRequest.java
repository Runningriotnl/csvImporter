package com.company;

import com.company.XelionObjects.PatchOperation;
import com.company.XelionObjects.PatchDocument;
import com.company.user.UserParserException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public class SendHttpRequest {
    private String baseUrl;
    private Gson gson;
    private Logger logger = LoggerFactory.getLogger(SendHttpRequest.class);
    private HttpRequestAssembler httpHandler;

    public SendHttpRequest(String baseUrl, HttpRequestAssembler httpHandler) {
        this.baseUrl = baseUrl;
        this.httpHandler = httpHandler;
    }

    public void postAddressableToServer(List<? extends Addressable> objectList) {
        gson = new Gson();
        String stringResponse;
        String apiUrl = baseUrl + "/api/v1/master/addressables";
        logger.info("Apiurl is: " + apiUrl);

        for(Addressable addressable: objectList) {
            try {
                logger.info(gson.toJson(addressable));
                stringResponse = httpHandler.createPostRequest(apiUrl, addressable);
                Type objectType = new TypeToken<Data<Addressable>>() {}.getType();
                logger.info("Stringresponse = " + stringResponse);
                Data<Addressable> response = gson.fromJson(stringResponse, objectType);
                logger.info(response.getObject().toString());
                addressable.setOid(response.getObject().getOid());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void postUserToServer(List<? extends Addressable> objectList) {
        gson = new Gson();
        String stringResponse;
        String apiUrl = baseUrl + "/api/v1/master/users";
        logger.info("Apiurl is: " + apiUrl);

        for(Addressable addressable: objectList) {
            try {
                logger.info(gson.toJson(addressable));
                stringResponse = httpHandler.createPostRequest(apiUrl, addressable);
                Type objectType = new TypeToken<Data<Addressable>>() {}.getType();
                Data<Addressable> response = gson.fromJson(stringResponse, objectType);
                Optional<Addressable> responseBody = Optional.ofNullable(response.getObject());
                if(responseBody.isPresent()){
                    logger.info(responseBody.toString());
                    addressable.setOid(response.getObject().getOid());
                } else {
                    throw new UserParserException("Could not create user.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void patchPhoneLineExtension(String phoneLineOID, String extensionNumber) {
        gson = new Gson();
        String stringResponse;
        String apiUrl = baseUrl + "/api/v1/master/phonelines/" + phoneLineOID;
        PatchDocument patchDocument = new PatchDocument();
        patchDocument.add(PatchOperation.add("/extensions/1"));
        patchDocument.add(PatchOperation.replace("/extensions/1/address", extensionNumber));
        try {
            logger.info(gson.toJson(patchDocument));
            stringResponse = httpHandler.createPatchRequest(apiUrl, patchDocument);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPhoneLineOID(String userOID) {
        gson = new Gson();
        String stringResponse;
        String phoneLineOID = "";
        String apiUrl = baseUrl + "/api/v1/master/users/" + userOID;
        try {
            stringResponse = httpHandler.createGetRequest(apiUrl, userOID);
            Type objectType = new TypeToken<Data<Addressable>>() {}.getType();
            Data<Addressable> response = gson.fromJson(stringResponse, objectType);
            phoneLineOID = response.getObject().getPrimaryLine().getOid();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phoneLineOID;
    }

//    public String getOrganizationOID(String orgName) {
//        gson = new Gson();
//        HttpResponse<JsonNode> jsonResponse;
//        String organizationOID = "";
//        String apiUrl = baseUrl + "/api/v1/master/addressables?name=" + orgName;
//        try {
//            jsonResponse = Unirest.get(apiUrl).header("Authorization", authToken).asJson();
//            logger.info("Status code: " + jsonResponse.getStatus() + " status text: " + jsonResponse.getStatusText());
//            Type listType = new TypeToken<DataList<Addressable>>() {}.getType();
//            DataList<Addressable> response = gson.fromJson(jsonResponse.getBody().toString(), listType);
//            List<Addressable> addressableList = response.getData();
//            if(addressableList.size() > 0){
//                organizationOID = addressableList.get(0).getOid();
//            }
//
//        } catch (UnirestException e) {
//            logger.error(e.toString());
//        }
//
//        return organizationOID;
//
//    }

}
